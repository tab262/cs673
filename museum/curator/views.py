from django.shortcuts import render, get_object_or_404
from curator.models import Museum, Art

# Create your views here.
def qr_list(request, museum_id):
    # gets the object from the database or throws a 404 error
     museum = get_object_or_404(Museum, id=museum_id)

     # https://docs.djangoproject.com/en/dev/topics/http/shortcuts/#django.shortcuts.render
     return render(request, 'qr_list.html', {'museum': museum})
