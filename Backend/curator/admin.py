from django.contrib import admin
from curator.models import Museum, Art, Artist, Exhibitions,Collection
from django.core.files import File
from django.core.files.base import ContentFile
import json
import urllib2
import qrcode
from cStringIO import StringIO

class MuseumAdmin(admin.ModelAdmin):
    # exclude = ('latitude', 'longitude', 'author',)
    readonly_fields = ['latitude', 'longitude', 'owner']
    def save_model(self, request, obj, form, change):

        # if the lat/long are 0, then they have not be initialized
        # so we use the following little script to get the GPS coords

        address = obj.streetAddress.replace(" ","+")
        api_string = 'https://maps.googleapis.com/maps/api/geocode/json?address='+address
        address_json = json.load(urllib2.urlopen(api_string))
        obj.latitude = address_json['results'][0]['geometry']['location']['lat']
        obj.longitude = address_json['results'][0]['geometry']['location']['lng']
        obj.owner = request.user
        obj.save()

    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(MuseumAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Museum, MuseumAdmin)



class ArtAdmin(admin.ModelAdmin):
    list_display = ('title','artist','collection','qr_code_fn')
    search_fields = ['artist']
    readonly_fields = ['owner','qr_code']

    def save_model(self, request, obj, form, change):
        obj.owner = request.user
        obj.save()

        #QR code logic
        image = qrcode.make("/curator/art/" + str(obj.pk))
        image_buffer = StringIO()
        image.save(image_buffer)
        image_buffer.seek(0)

        #Here we use django file storage system to save the image.
        file_name = 'artQR_%s.jpg' % obj.id
        file_object = File(image_buffer, file_name)
        content_file = ContentFile(file_object.read())
        obj.qr_code.save(file_name, content_file, save=True)





    def formfield_for_foreignkey(self, db_field, request, **kwargs):
        # limit choices of art to only your art
        if db_field.name == 'museum':
            if not request.user.is_superuser:
                kwargs["queryset"] = Museum.objects.filter(owner=request.user)
        # limit choices of art to only your art
        if db_field.name == 'artist':
            if not request.user.is_superuser:
                kwargs["queryset"] = Artist.objects.filter(owner=request.user)
        return super(ArtAdmin, self).formfield_for_foreignkey(db_field, request, **kwargs)

    # def formfield_for_foreignkey(self, db_field, request, **kwargs):
    #     # limit choices of art to only your art
    #     if db_field.name == 'artist':
    #         if not request.user.is_superuser:
    #             kwargs["queryset"] = Artist.objects.filter(owner=request.user)
    #     return super(ArtAdmin, self).formfield_for_foreignkey(db_field, request, **kwargs)


    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(ArtAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Art, ArtAdmin)


class ArtistAdmin(admin.ModelAdmin):
    readonly_fields = ['owner',]
    def save_model(self, request, obj, form, change):
        obj.owner = request.user
        obj.save()

    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(ArtistAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Artist, ArtistAdmin)



class ExhibitionsAdmin(admin.ModelAdmin):
    readonly_fields = ['owner',]
    def save_model(self, request, obj, form, change):
        obj.owner = request.user
        obj.save()

    def formfield_for_foreignkey(self, db_field, request, **kwargs):
        # limit choices of art to only your art
        if db_field.name == 'museum':
            if not request.user.is_superuser:
                kwargs["queryset"] = Museum.objects.filter(owner=request.user)
        return super(ExhibitionsAdmin, self).formfield_for_foreignkey(db_field, request, **kwargs)

    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(ExhibitionsAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Exhibitions, ExhibitionsAdmin)


class CollectionsAdmin(admin.ModelAdmin):
    readonly_fields = ['owner',]
    def save_model(self, request, obj, form, change):
        obj.owner = request.user
        obj.save()

    def formfield_for_foreignkey(self, db_field, request, **kwargs):
        # limit choices of art to only your art
        if db_field.name == 'museum':
            if not request.user.is_superuser:
                kwargs["queryset"] = Museum.objects.filter(owner=request.user)
        return super(CollectionsAdmin, self).formfield_for_foreignkey(db_field, request, **kwargs)

    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(CollectionsAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Collection, CollectionsAdmin)
