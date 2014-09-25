from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from curator.models import Museum
from curator.serializers import MuseumSerializer


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

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = MuseumSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JSONResponse(serializer.data, status=201)
        return JSONResponse(serializer.errors, status=400)

@csrf_exempt
def museum_detail(request, pk):

    try:
        museum = Museum.objects.get(pk=pk)
    except Museum.DoesNotExist:
        return HttpResponse(status=404)


    if request.method == 'GET':
        serializer = MuseumSerializer(museum)
        return JSONResponse(serializer.data)


    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = MuseumSerializer(museum, data=data)
        if serializer.is_valid():
            serializer.save()
            return JSONResponse(serializer.data)
        return JSONResponse(serializer.errors, status=400)

    elif request.method == 'DELETE':
        museum.delete()
        return HttpResponse(status=204)