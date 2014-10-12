from django.conf.urls import patterns, url

urlpatterns = patterns('curator.views',
    url(r'^curator/$', 'museum_list'),
    url(r'^curator/(?P<pk>[0-9]+)/$', 'museum_detail'),
    url(r'^curator/state/(?P<state>[a-zA-Z]{2}/$)', 'get_list_by_state')
)