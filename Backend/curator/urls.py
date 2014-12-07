from django.conf.urls import patterns, url
urlpatterns = patterns('curator.views',
    url(r'^curator/$', 'museum_list'),
    url(r'^curator/(?P<pk>[0-9]+)/$', 'museum_detail'),
    url(r'^curator/art/(?P<pk>[0-9]+)/$', 'art_detail'),
    url(r'^curator/artist/(?P<pk>[0-9]+)/$', 'artist_detail'),
    url(r'^curator/(?P<pk>[0-9]+)/exhibitions/$','exhibitions_detail'),
    url(r'^curator/(?P<pk>[0-9]+)/collection/$', 'collection_list'),
    url(r'^curator/(?P<pk>[0-9]+)/collection/(?P<cid>[0-9]+)/$', 'collection_detail'),
    url(r'^curator/locations/$','locations_list')
)