from django.db import models

# Create your models here.
class Museum(models.Model):
    name = models.CharField(max_length=200,default="Test Museum")
    location = models.CharField(max_length=200)
    hours = models.CharField(max_length=200)
    # TODO:
    # Add attributes: latlong, tours, spotlight, collections, image
    # image can use base64 encoding
    
    def __unicode__(self):
        return self.name



class Art(models.Model):
    museum = models.ForeignKey(Museum)
    title = models.CharField(max_length=100,default="untitled")
    artist = models.CharField(max_length=100)
    time = models.CharField(max_length=100)
    movement = models.CharField(max_length=100)
    description = models.TextField()

    def __unicode__(self):
        return self.title
