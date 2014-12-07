from django.shortcuts import render
from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.http import HttpResponseRedirect
from django.shortcuts import render
from curator.models import Museum
from django.contrib.auth.models import User
from django.core.mail import send_mail


# Create your views here.


#registration form
class registerForm(forms.Form):
    
    username=forms.CharField(label='User name', max_length=100)
    password=forms.CharField(widget=forms.PasswordInput())
    email=forms.EmailField()
    
    museumName=forms.CharField(label='Museum name', max_length=100)
    streetAddress=forms.CharField(label='Street Address', max_length=100)
    city=forms.CharField(label='City', max_length=100)
    state=forms.CharField(label='State', max_length=2)
    zipCode=forms.CharField(label='Street Address', max_length=5)

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
    
    image=forms.CharField(label='image', max_length=100)
    
    def save(self):
        
        #cleaning the form data
        if self.is_valid():
        
            # create a new user
            # print self.cleaned_data['email']
            user = User.objects.create_user(self.cleaned_data['username'], self.cleaned_data['email'], self.cleaned_data['password'])
            user.save()

            # create new museum record and assign the newly created user as owner
            museum=Museum(name=self.cleaned_data['museumName'],streetAddress=self.cleaned_data['streetAddress'],city=self.cleaned_data['city'],state=self.cleaned_data['state'],zipCode=self.cleaned_data['zipCode'],openingHours_M=self.cleaned_data['openingHours_M'],openingHours_T=self.cleaned_data['openingHours_T'],openingHours_W=self.cleaned_data['openingHours_W'],openingHours_R=self.cleaned_data['openingHours_R'],openingHours_F=self.cleaned_data['openingHours_F'],openingHours_ST=self.cleaned_data['openingHours_ST'],openingHours_SN=self.cleaned_data['openingHours_SN'],membership=self.cleaned_data['membership'],ticket_prices=self.cleaned_data['ticket_prices'],parking=self.cleaned_data['parking'],website=self.cleaned_data['website'],visitor_info=self.cleaned_data['visitor_info'],image=self.cleaned_data['image'],owner=user)
        
       
            museum.save()

            send_mail('New Registration',' Thank You for your registration, your application is pending review','admin@musuemscurator.com',[self.cleaned_data['email']],fail_silently=False)

def register(request):
    if request.method == 'POST':
        form = registerForm(request.POST)

        if form.is_valid():
            
            form.save()

            return HttpResponseRedirect('/admin/')
    else:
        form = registerForm()
        return render(request, 'registration/register.html', {
                  'form': form,
                  })