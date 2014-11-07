from django.conf.urls import patterns, include, url
from django.contrib import admin
from curator import views
from curator.models import Museum, Art

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'museum.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^admin/', include(admin.site.urls)),
    url(r'qr-list/(?P<museum_id>\d+)', views.qr_list)
)
