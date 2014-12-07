from django.test import LiveServerTestCase
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from django.test import TestCase
from curator.models import Museum, Art, Artist, Collection

class MuseumTest(LiveServerTestCase):
    fixtures = ['admin_user.json']


    def setUp(self):
        self.browser = webdriver.Firefox()
        self.browser.implicitly_wait(3)

    def tearDown(self):
        self.browser.quit()

    def test_can_create_new_museum_via_admin_site(self):
        # Gertrude opens her web browser, and goes to the admin page
        self.browser.get(self.live_server_url + '/admin/')

        # She sees the familiar 'Django administration' heading
        body = self.browser.find_element_by_tag_name('body')
        self.assertIn('Django administration', body.text)

        username_field = self.browser.find_element_by_name('username')
        username_field.send_keys('admin')

        password_field = self.browser.find_element_by_name('password')
        password_field.send_keys('admin')
        password_field.send_keys(Keys.RETURN)

        body = self.browser.find_element_by_tag_name('body')
        self.assertIn('Site administration', body.text)

        museum_links = self.browser.find_elements_by_link_text('Museums')

        self.assertEquals(len(museum_links), 1)

        museum_links[0].click()

        body = self.browser.find_element_by_tag_name('body')
        self.assertIn('0 museums', body.text)

        # She sees a link to 'add' a new poll, so she clicks it
        new_museum_link = self.browser.find_element_by_link_text('Add museum')
        new_museum_link.click()

        body = self.browser.find_element_by_tag_name('body')
        self.assertIn('Name:', body.text)
        self.assertIn('City', body.text)
        self.assertIn('State',body.text)
                 
class MuseumModelTest(TestCase):
    
    def test_creating_a_new_objects_and_saving_it_to_the_database(self):
        # start by creating a new Poll object with its "question" set
        m = Museum()
        m.name = "Museum Test"
        m.streetAddress = "123 Easy St"
        m.city = "Boston"
        m.state = "MA"
        m.zipCode = "12345"
        m.openingHours_M = "9-5"
        m.openingHours_T = "9-5"
        m.openingHours_W = "9-5"
        m.openingHours_R = "9-5"
        m.openingHours_F = "9-5"
        m.openingHours_ST = "9-5"
        m.openingHours_SN = "9-5"

        m.events = "None"
        #m.image
        m.website = "www.mfa.org"
        m.description = "museum description"
        m.parking = "Yes"
        m.ticket_prices = "$25.00"
        m.visitor_info = "vistor information"
        m.membership = "yes"


        # check we can save it to the database
        m.save()

        # now check we can find it in the database again
        all_museums_in_database = Museum.objects.all()
        self.assertEquals(len(all_museums_in_database), 1)
        only_museum_in_database = all_museums_in_database[0]
        self.assertEquals(only_museum_in_database, m)

        # and check that it's saved its two attributes: question and pub_date
        self.assertEquals(only_museum_in_database.name, "Museum Test")
        self.assertEquals(only_museum_in_database.city, "Boston")

        # making a test collection
        c = Collection()
        c.title = "Collection 1"
        c.description = "test collection"
        c.museum = m
        c.save()

        # making a test artist
        a = Artist()
        a.name = "Mark"
        a.nationality = "Dutch"
        a.biography = "lorem ipsum prada"
        a.dateOfBirth = "2014-12-02"
        a.image = "image/test.jpg"
        a.save()

        # making a test Art object
        art = Art()
        art.title ="Art #1"
        art.artist = a;
        art.creationDate = "2014-12-02"
        art.image = "image.jpg"
        art.audio = "audio.mp3"
        art.description = "lorem ipsum pravda"
        art.museum = m
        art.collection = c
        art.save()