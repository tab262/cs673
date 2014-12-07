__author__ = 'gaddis'

from django.forms import widgets
from rest_framework import serializers
from curator.models import Museum, Art, Artist,User, Exhibitions, Collection

class MuseumSerializer(serializers.ModelSerializer):

    class Meta:
        model = Museum
        fields = ['name', 'streetAddress','city','state','zipCode','latitude',
                  'longitude', 'openingHours_M', 'openingHours_T', 'openingHours_W',
                  'openingHours_R', 'openingHours_F', 'openingHours_ST', 'openingHours_SN',
                  'events', 'image', 'parking', 'ticket_prices', 'visitor_info',
                  'membership', 'website',
                  'description']



class ArtistSerializer(serializers.ModelSerializer):
    class Meta:
        model = Artist
        fields = ['name','nationality','biography','dateOfBirth','dateOfDeath','image','owner',]


class ArtSerializer(serializers.ModelSerializer):
    #museum = MuseumSerializer(many=False)
    artist = ArtistSerializer(many=False)
    class Meta:
        model = Art
        fields = ['id','title','collection','creationDate','image','audio','description','museum','artist']




class ExhibitionsSerializer(serializers.ModelSerializer):
    print "inner ex ser"
    art_objects = ArtSerializer(many=True)

    class Meta:
        model = Exhibitions
        fields = ['id','title','startDate','endDate','description','art_objects','museum']



class CollectionsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Collection
        fields = ['title','id']
