from django.db import models
from django.contrib.auth.models import Permission, User
# Create your models here.


#manage access to Museum Model
class MuseumManager(models.Manager):
    
    def get_queryset(self):
        
        #need to modify this once active column is added
        #only active Museums are displayed in the query results.
        return super(MuseumManager, self).get_queryset().filter()


class Museum (models.Model):
    name = models.CharField(max_length=200)
    streetAddress = models.CharField(max_length=200)
    city = models.CharField(max_length=200)
    state = models.CharField(max_length=2)
    zipCode = models.CharField(max_length=5)
    latitude = models.DecimalField(max_digits=30,decimal_places=15,default=0.0)
    longitude = models.DecimalField(max_digits=30,decimal_places=15,default=0.0)

    openingHours_M = models.CharField(max_length=200)
    openingHours_T = models.CharField(max_length=200)
    openingHours_W = models.CharField(max_length=200)
    openingHours_R = models.CharField(max_length=200)
    openingHours_F = models.CharField(max_length=200)
    openingHours_ST = models.CharField(max_length=200)
    openingHours_SN = models.CharField(max_length=200)
    events = models.CharField(max_length=200)
    image = models.ImageField(upload_to="museum_images", blank=True)#models.CharField(max_length=200)
    website = models.URLField()
    description = models.TextField()


    parking = models.CharField(max_length=200,default=None)
    ticket_prices = models.CharField(max_length=200,default=None)
    visitor_info = models.CharField(max_length=200,default=None)
    membership = models.CharField(max_length=200,default=None)
    
    # Need to uncomment as currently does not make migration for this new column
    #active=models.BooleanField(default=False)
    

    owner = models.ForeignKey(User, null=True, blank=True)
    
    # assign manager to this model.
    objects=MuseumManager()

    def __unicode__(self):
        return self.name


class Artist (models.Model):
    name = models.CharField(max_length=200,null="True", blank="True")
    nationality = models.CharField(max_length=200,null="True", blank="True")
    biography = models.CharField(max_length=200,null="True", blank="True")
    dateOfBirth = models.DateField('DoB',null="True", blank="True")
    dateOfDeath = models.DateField('DoD', null="True", blank="True")
    image = models.ImageField(upload_to="artist_images", blank=True)
    owner = models.ForeignKey(User, null=True, blank=True)



    def __unicode__(self):
        return self.name

class Collection (models.Model):
    title = models.CharField(max_length=200)
    description = models.CharField(max_length=2000)
    museum = models.ForeignKey(Museum)
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.title


class Art (models.Model):
    title = models.CharField(max_length=200)
    artist = models.ForeignKey(Artist)
    creationDate = models.DateField('creation date')
    image = models.ImageField(upload_to="art_images", blank=True) #models.CharField(max_length=20000)
    audio = models.FileField(upload_to="audio_files", blank=True)
    description = models.TextField()
    museum = models.ForeignKey(Museum)
    owner = models.ForeignKey(User, null=True, blank=True)
    #image2 = models.ImageField(upload_to="upload_image_to", blank=True)
    qr_code = models.ImageField(null=True,upload_to="qr_codes", blank=True)
    collection = models.ForeignKey(Collection)

    def __unicode__(self):
        return self.title

    def qr_code_fn(self):
        return '<img src="%s", height="150",width="150"/>' %  self.qr_code.url

    qr_code_fn.allow_tags = True




class Exhibitions (models.Model):
    title = models.CharField(max_length=200)
    startDate = models.DateField('Exhibition Starts')
    endDate = models.DateField('Exhibition Ends')
    art_objects = models.ManyToManyField(Art)
    description = models.CharField(max_length=2000)
    museum = models.ForeignKey(Museum)
    owner = models.ForeignKey(User, null=True, blank=True)
    #image = models.URLField(null=True,default=None)



