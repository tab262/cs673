__author__ = 'gaddis'

from django.forms import widgets
from rest_framework import serializers
from curator.models import Museum


class MuseumSerializer(serializers.Serializer):
    pk = serializers.Field()
    name = serializers.CharField(required=True,max_length=200)
    location = serializers.CharField(max_length=200)
    hours = serializers.CharField(max_length=200)


    def restore_object(self, attrs, instance=None):
        if instance:
            instance.name = attrs.get('name',instance.name)
            instance.location = attrs.get('location',instance.location)
            instance.hours = attrs.get('hours',instance.hours)

        return Museum(**attrs)
