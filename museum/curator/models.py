from django.db import models
from django.contrib.auth.models import User



class Museum(models.Model):
    name = models.CharField(max_length=200, null=True)
    # location = models.ForeignKey(Location, null=True)
    address = models.CharField(max_length=300, null=True)
    latitude = models.DecimalField(max_digits=40, decimal_places=30, null=True, blank=True)
    longitude = models.DecimalField(max_digits=40, decimal_places=30, null=True, blank=True)
    hours = models.TextField(null=True)
    website = models.URLField(null=True, blank=True)
    tour_times = models.TextField(null=True, blank=True)
    collections = models.TextField(null=True, blank=True)
    owner = models.ForeignKey(User, null=True, blank=True)


    def __unicode__(self):
        return self.name



class Artist(models.Model):
    museum = models.ForeignKey(Museum)
    title = models.CharField(max_length=100,default="untitled")
    biography = models.TextField()
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.title


class Art(models.Model):
    museum = models.ForeignKey(Museum)
    title = models.CharField(max_length=100,default="untitled")
    artist = models.ForeignKey(Artist)
    year_created = models.CharField(max_length=100)
    movement = models.CharField(max_length=100)
    description = models.TextField()
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.title
