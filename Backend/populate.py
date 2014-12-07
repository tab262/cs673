__author__ = 'gaddis'
import os,sys
from curator.models import Museum, Artist


def populate():

    m = Museum.objects.get_or_create(name="MoMA",streetAddress="11 W 53rd St.",
                                 city="New York", state="NY",zipCode="10019",latitude=43.222,
                                 longitude=23.2222,openingHours_M="9-5",openingHours_T="9-5",
                                 openingHours_W="9-5",openingHours_R="9-5",openingHours_F="9-5",
                                 openingHours_ST="9-9",openingHours_SN="9-3",
                                 events='None',image='None')



def populateArtists():
    owner = Artist.objects.get(pk=1)
    args = {"name" : "Franz Ferdinand" ,"nationality": "German", "biography": "Loves to Sculpt", "dateOfBirth": "1945-09-12T07:00:00Z", "dateOfDeath": "1999-09-12T07:00:00Z", "image": "None", "owner": owner.owner}
    a = Artist(**args)
    a.save()

def main(argv):
    populateArtists()
    return



if __name__ == "__main__":
    main(sys.argv)