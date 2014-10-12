__author__ = 'gaddis'

from django.forms import widgets
from rest_framework import serializers
from curator.models import Museum

class MuseumSerializer(serializers.Serializer):
    pk = serializers.Field()
    name = serializers.CharField(required=True,max_length=200)
    streetAddress = serializers.CharField(required=True,max_length=200)
    city = serializers.CharField(required=True,max_length=200)
    state = serializers.CharField(required=True,max_length=2)
    zipCode = serializers.CharField(required=True,max_length=5)
    latitude = serializers.DecimalField(max_digits=8,decimal_places=2,default=0.0)
    longitude = serializers.DecimalField(max_digits=8,decimal_places=2,default=0.0)

    openingHours_M = serializers.CharField(max_length=200)
    openingHours_T = serializers.CharField(max_length=200)
    openingHours_W = serializers.CharField(max_length=200)
    openingHours_R = serializers.CharField(max_length=200)
    openingHours_F = serializers.CharField(max_length=200)
    openingHours_ST = serializers.CharField(max_length=200)
    openingHours_SN = serializers.CharField(max_length=200)
    events = serializers.CharField(max_length=200)
    image = serializers.CharField(max_length=200)

    parking = serializers.CharField(max_length=200)
    ticket_prices = serializers.CharField(max_length=200)
    visitor_info = serializers.CharField(max_length=200)
    membership = serializers.CharField(max_length=200)


    website = serializers.URLField()

    description = serializers.CharField(max_length=800, default=None)

    def restore_object(self,attrs,instance=None):
        if instance:
            instance.name = attrs.get('name',instance.name)
            instance.streetAddress = attrs.get('streetAddress',instance.streetAddress)
            instance.city = attrs.get('city',instance.city)
            instance.state = attrs.get('state',instance.state)
            instance.zipCode = attrs.get('zipCode',instance.zipCode)
            instance.latitude = attrs.get('latitude',instance.latitude)
            instance.longitude = attrs.get('longitude',instance.longitude)
            instance.openingHours_M = attrs.get('openingHours_M',instance.openingHours_M)
            instance.openingHours_T = attrs.get('openingHours_T',instance.openingHours_T)
            instance.openingHours_W = attrs.get('openingHours_W',instance.openingHours_W)
            instance.openingHours_R = attrs.get('openingHours_R',instance.openingHours_R)
            instance.openingHours_F = attrs.get('openingHours_F',instance.openingHours_F)
            instance.openingHours_ST = attrs.get('openingHours_ST',instance.openingHours_ST)
            instance.openingHours_SN = attrs.get('openingHours_SN',instance.openingHours_SN)
            instance.events = attrs.get('events',instance.events)
            instance.image = attrs.get('image',instance.image)
            instance.website = attrs.get('website',instance.website)
            instance.description = attrs.get('description', instance.description)
            instance.parking = attrs.get('parking',instance.parking)
            instance.ticket_prices = attrs.get("ticket_prices", instance.ticket_prices)
            instance.visitor_info = attrs.get("visitor_info", instance.visitor_info)
            instance.membership = attrs.get("membership", instance.membership)

        return Museum(**attrs)