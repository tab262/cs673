from django.contrib import admin
from curator.models import Museum, Art



class MuseumAdmin(admin.ModelAdmin):
    # exclude = ('latitude', 'longitude', 'author',)
    readonly_fields = ['latitude', 'longitude', 'owner']
    def save_model(self, request, obj, form, change):
        obj.owner = request.user
        obj.latitude = 70.0101011
        obj.save()

    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(MuseumAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Museum, MuseumAdmin)



class ArtAdmin(admin.ModelAdmin):

    readonly_fields = ['owner',]
    def save_model(self, request, obj, form, change):
        obj.owner = request.user
        obj.save()

    def formfield_for_foreignkey(self, db_field, request, **kwargs):
        # limit choices of art to only your art
        if db_field.name == 'museum':
            if not request.user.is_superuser:
                kwargs["queryset"] = Museum.objects.filter(owner=request.user)
        return super(ArtAdmin, self).formfield_for_foreignkey(db_field, request, **kwargs)

    def queryset(self, request):
        # limit museums to those that belong to request's user
        qs = super(ArtAdmin, self).queryset(request)
        if request.user.is_superuser:
            return qs
        return qs.filter(owner=request.user)

admin.site.register(Art, ArtAdmin)
