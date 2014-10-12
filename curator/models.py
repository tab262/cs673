from django.db import models
from django.contrib.auth.models import Permission, User
# Create your models here.


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
    image = models.CharField(max_length=200)
    website = models.URLField()
    description = models.CharField(max_length=800)


    parking = models.CharField(max_length=200,default=None)
    ticket_prices = models.CharField(max_length=200,default=None)
    visitor_info = models.CharField(max_length=200,default=None)
    membership = models.CharField(max_length=200,default=None)


    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.name


class Artist (models.Model):
    name = models.CharField(max_length=200)
    nationality = models.CharField(max_length=200)
    biography = models.CharField(max_length=200)
    dateOfBirth = models.DateTimeField('DoB')
    dateOfDeath = models.DateTimeField('DoD')
    image = models.CharField(max_length=200)
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.name


class Art (models.Model):
    title = models.CharField(max_length=200)
    artType = models.CharField(max_length=200)
    artist = models.ForeignKey(Artist)
    creationDate = models.DateTimeField('creation date')
    image = models.CharField(max_length=200)
    audio = models.CharField(max_length=200)
    description = models.TextField()
    museum = models.ForeignKey(Museum)
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.title


class Tours (models.Model):
    dateTime = models.DateTimeField('DoD')
    features = models.CharField(max_length=200)
    museum = models.ForeignKey(Museum)
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.features


class Exhibitions (models.Model):
    dateTime = models.DateTimeField('DoD')
    features = models.CharField(max_length=200)
    museum = models.ForeignKey(Museum)
    owner = models.ForeignKey(User, null=True, blank=True)

    def __unicode__(self):
        return self.features
