__author__ = 'gaddis'

from django.conf.urls import patterns, url

urlpatterns = patterns('curator.views',
    url(r'^curator/$', 'museum_list'),
    url(r'^curator/(?P<pk>[0-9]+)/$', 'museum_detail'),
)