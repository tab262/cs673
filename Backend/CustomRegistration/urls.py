
from django.conf.urls import patterns, url, include
from CustomRegistration.views import RegistrationView, ActivationView
from django.views.generic.base import TemplateView

from django.core.urlresolvers import reverse_lazy

from django.contrib.auth import views as auth_views

urlpatterns = patterns('',
                       url(r'^activate/complete/$',
                           TemplateView.as_view(template_name='registration/activation_complete.html'),
                           name='registration_activation_complete'),
                       # Activation keys get matched by \w+ instead of the more specific
                       # [a-fA-F0-9]{40} because a bad activation key should still get to the view;
                       # that way it can return a sensible "invalid key" message instead of a
                       # confusing 404.
                       url(r'^activate/(?P<activation_key>\w+)/$',
                           ActivationView.as_view(),
                           name='registration_activate'),
                       url(r'^register/$',
                           RegistrationView.as_view(),
                           name='registration_register'),
                       url(r'^register/complete/$',
                           TemplateView.as_view(template_name='registration/registration_complete.html'),
                           name='registration_complete'),
                       url(r'^password/reset/$',
                           auth_views.password_reset,
                           {'post_reset_redirect': reverse_lazy('auth_password_reset_done')},
                           name='auth_password_reset'),
                       url(r'^password/reset/confirm/(?P<uidb64>[0-9A-Za-z_\-]+)/(?P<token>.+)/$',
                           auth_views.password_reset_confirm,
                           {'post_reset_redirect': reverse_lazy('auth_password_reset_complete')},
                           name='auth_password_reset_confirm'),
                       url(r'^password/reset/complete/$',
                           auth_views.password_reset_complete,
                           name='auth_password_reset_complete'),
                       url(r'^password/reset/done/$',
                           auth_views.password_reset_done,
                           name='auth_password_reset_done'),
                       )
                       