from django.db import models

# Create your models here.


class Museum (models.Model):
    name = models.CharField(max_length=200)
    streetAddress = models.CharField(max_length=200)
    geoCooAdd = models.CharField(max_length=200)
    openingHours_M = models.CharField(max_length=200)
    openingHours_T = models.CharField(max_length=200)
    openingHours_W = models.CharField(max_length=200)
    openingHours_R = models.CharField(max_length=200)
    openingHours_F = models.CharField(max_length=200)
    openingHours_ST = models.CharField(max_length=200)
    openingHours_SN = models.CharField(max_length=200)
    events = models.CharField(max_length=200)
    image = models.CharField(max_length=200)

    def __unicode__(self):
        return self.name


class Artist (models.Model):
    name = models.CharField(max_length=200)
    nationality = models.CharField(max_length=200)
    biography = models.CharField(max_length=200)
    dateOfBirth = models.DateTimeField('DoB')
    dateOfDeath = models.DateTimeField('DoD')
    image = models.CharField(max_length=200)

    def __unicode__(self):
        return self.name


class Art (models.Model):
    title = models.CharField(max_length=200)
    artType = models.CharField(max_length=200)
    artist = models.ForeignKey(Artist)
    creationDate = models.DateTimeField('creation date')
    image = models.CharField(max_length=200)
    audio = models.CharField(max_length=200)
    discreption = models.CharField(max_length=200)
    museum = models.ForeignKey(Museum)

    def __unicode__(self):
        return self.title


class Tours (models.Model):
    dateTime = models.DateTimeField('DoD')
    features = models.CharField(max_length=200)
    museum = models.ForeignKey(Museum)

    def __unicode__(self):
        return self.features


class Exhibitions (models.Model):
    dateTime = models.DateTimeField('DoD')
    features = models.CharField(max_length=200)
    museum = models.ForeignKey(Museum)

    def __unicode__(self):
        return self.features

