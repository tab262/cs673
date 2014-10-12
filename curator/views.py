from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from curator.models import Museum
from curator.serializers import MuseumSerializer

# Create your views here.
class JSONResponse(HttpResponse):

    def __init__(self,data, **kwargs):
        content = JSONRenderer().render(data)
        kwargs['content_type'] = 'application/json'
        super(JSONResponse,self).__init__(content, **kwargs)




@csrf_exempt
def museum_list(request):
    if request.method == 'GET':
        museums = Museum.objects.all()
        serializer = MuseumSerializer(museums, many=True)
        return JSONResponse(serializer.data)


@csrf_exempt
def museum_detail(request, pk):

    try:
        museum = Museum.objects.get(pk=pk)
    except Museum.DoesNotExist:
        return HttpResponse(status=404)


    if request.method == 'GET':
        serializer = MuseumSerializer(museum)
        return JSONResponse(serializer.data)


def get_list_by_state(request,state):

    state.replace("/","")
    print "STATE: " + state
    museum_list = Museum.objects.filter(state=state[:-1])
    return_list = []
    for museum in museum_list:
        temp_string = str(museum.name) + ": " + str(museum.pk)
        return_list.append(temp_string)
    print museum_list
    return HttpResponse(str(return_list))#.replace("<","").replace(">","").replace("Museum: ",''))

    #if request.method == 'GET':
    #    serializer = MuseumSerializer(museum_list,many=True)
    #    return JSONResponse(serializer.data)