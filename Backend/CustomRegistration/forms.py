"""
from django import forms
from registration.forms import RegistrationForm as BaseRegistrationForm

#registration form
# modify The base registration to include Museum info fields
class RegistrationForm(BaseRegistrationForm):
    
    museumName=forms.CharField(label='Museum name', max_length=100)
    streetAddress=forms.CharField(label='Street Address', max_length=100)
    city=forms.CharField(label='City', max_length=100)
    state=forms.CharField(label='State', max_length=2)
    zipCode=forms.CharField(label='ZipCode', max_length=5)

    openingHours_M=forms.CharField(label='Opening Hours Monday', max_length=100)
    openingHours_T=forms.CharField(label='Opening Hours Tuesday', max_length=100)
    openingHours_W=forms.CharField(label='Opening Hours Wednesday', max_length=100)
    openingHours_R=forms.CharField(label='Opening Hours Thursday', max_length=100)
    openingHours_F=forms.CharField(label='Opening Hours Friday', max_length=100)
    openingHours_ST=forms.CharField(label='Opening Hours Saturday', max_length=100)
    openingHours_SN=forms.CharField(label='Opening Hours Sunday', max_length=100)
    events=forms.CharField(label='events', max_length=100)
    description=forms.CharField(label='Description', max_length=100)
    
    
    membership=forms.CharField(label='membership', max_length=100)
    
    ticket_prices=forms.CharField(label='ticket_prices', max_length=100)
    
    parking=forms.CharField(label='parking', max_length=100)
    
    website=forms.URLField()

    visitor_info=forms.CharField(label='visitor_info', max_length=100)
    
    image=forms.ImageField(label='image', required=False)
    
"""