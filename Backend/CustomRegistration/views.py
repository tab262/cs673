from django.shortcuts import render
from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.shortcuts import render
from curator.models import Museum
from django.contrib.auth.models import User,Group
from django.conf import settings
from django.contrib.sites.models import RequestSite
from django.contrib.sites.models import Site
from registration.models import RegistrationProfile
from registration.backends.default.views import RegistrationView as BaseRegistrationView
from registration.backends.default.views import ActivationView as BaseActivationView
from registration import signals
from CustomRegistration.forms import RegistrationForm


# Create your views here.


#registration view
class RegistrationView(BaseRegistrationView):
    
    form_class=RegistrationForm
    
    def register(self, request, **cleaned_data):
        # create a user
        username, email, password = cleaned_data['username'], cleaned_data['email'], cleaned_data['password1']

        if Site._meta.installed:
             site = Site.objects.get_current()
        else:
             site = RequestSite(request)
        
        new_user = RegistrationProfile.objects.create_inactive_user(
                                                            username, email, password, site,
                                                            send_email=self.SEND_ACTIVATION_EMAIL,
                                                            request=request,
                                                            )
        signals.user_registered.send(sender=self.__class__,
                             user=new_user,
                             request=request)

        # create new museum record and assign the newly created user as owner
        museum=Museum(name=cleaned_data['museumName'],streetAddress=cleaned_data['streetAddress'],city=cleaned_data['city'],state=cleaned_data['state'],zipCode=cleaned_data['zipCode'],openingHours_M=cleaned_data['openingHours_M'],openingHours_T=cleaned_data['openingHours_T'],openingHours_W=cleaned_data['openingHours_W'],openingHours_R=cleaned_data['openingHours_R'],openingHours_F=cleaned_data['openingHours_F'],openingHours_ST=cleaned_data['openingHours_ST'],openingHours_SN=cleaned_data['openingHours_SN'],membership=cleaned_data['membership'],ticket_prices=cleaned_data['ticket_prices'],parking=cleaned_data['parking'],website=cleaned_data['website'],visitor_info=cleaned_data['visitor_info'],image=cleaned_data['image'],owner=new_user)
       
        museum.save()
        return new_user

#Activation view

class ActivationView(BaseActivationView):
    def activate(self, request, activation_key):
        activated_user = RegistrationProfile.objects.activate_user(activation_key)
             
        if activated_user:
            
            #assign staff status
            activated_user.is_staff = True
            
            #assign group museumAdmins
            if  Group.objects.get(name='museumAdmins') is None:
                Group.objects.create(name='museumAdmins')

            activated_user.groups.add(Group.objects.get(name='museumAdmins'))

            activated_user.save()
            
            signals.user_activated.send(sender=self.__class__,
                            user=activated_user,
                            request=request)
         

        return activated_user
