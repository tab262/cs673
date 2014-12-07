from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from curator.models import Museum, Art, Artist, Exhibitions, Collection
from curator.serializers import MuseumSerializer
from curator.serializers import ArtSerializer
from curator.serializers import ArtistSerializer
from curator.serializers import ExhibitionsSerializer
from curator.serializers import CollectionsSerializer

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
        state = request.GET.get('state','')
        city = request.GET.get('city','')

        if state is not '' and city is '':
            museums = museums.filter(state=state.upper())
            m_list = set()
            for museum in museums:
                m_list.add(str(museum.city))

            r_string = []
            for museum in m_list:
                r_string.append(museum)

            return HttpResponse(str(r_string))



        if state is not '' and city is not '':
            museums = museums.filter(state=state.upper(), city=city.capitalize())

            m_list = set()
            for museum in museums:
                m_list.add(str(museum.name) + " : " + str(museum.pk))

            r_string = []
            for museum in m_list:
                r_string.append(museum)

            return HttpResponse(str(r_string))


        """
        if city is not '':
            print city.capitalize().replace("_"," ")
            museums = museums.filter(city=city.capitalize().replace("_"," "))

        if state is not '':
            museums = museums.filter(state=state.upper())
        """

        if city is not '':
            m_list = []
            print "returning museums in city"
            for museum in museums:
                m_list.append(str(museum.name) + " : " + str(museum.pk))
            return HttpResponse(str(m_list))

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


@csrf_exempt
def art_detail(request,pk):
    print "In art detail function"
    try:
        art = Art.objects.get(pk=pk)
    except Art.DoesNotExist:
        return HttpResponse(status=404)


    if request.method == 'GET':
        print "ART_DETAIL: GET"
        serializer = ArtSerializer(art)
        return JSONResponse(serializer.data)


@csrf_exempt
def artist_detail(request,pk):
    print "In artist detail function"
    try:
        artist = Artist.objects.get(pk=pk)
    except Art.DoesNotExist:
        return HttpResponse(status=404)


    if request.method == 'GET':
        print "ART_DETAIL: GET"
        serializer = ArtistSerializer(artist)
        return JSONResponse(serializer.data)


def exhibitions_detail(request,pk):
    print "In exhibition details function"

    try:
        museum = Museum.objects.get(pk=pk)
        e = museum.exhibitions_set.all().values()
    except Exhibitions.DoesNotExist:
        return HttpResponse(status=404)



    if request.method == 'GET':
        serializer = ExhibitionsSerializer(e, many=True)
        #for entry in serializer:
            #ex_pk =
        print serializer.data
        for item in serializer.data:
            #print item['id']
            #print item['art_objects']ll
            e = Exhibitions.objects.get(pk=item['id'])
            item['art_objects'] = e.art_objects.values()
            #print item['art_objects']
            #print "-" * 30
        return JSONResponse(serializer.data)


def collection_list(request,pk):
    c = Collection.objects.filter(museum=(Museum.objects.get(pk=pk)))
    serializer = CollectionsSerializer(c,many=True)
    return (JSONResponse(serializer.data))


def collection_detail(request,pk,cid):
    #return HttpResponse(cid)
    art_objects = Art.objects.filter(collection=Collection.objects.get(pk=cid))

    if request.method == 'GET':
        print "COLLECTION_DETAIL: GET"
        serializer = ArtSerializer(art_objects, many=True)
        return JSONResponse(serializer.data)


def locations_list(request):

    museums = Museum.objects.all()
    locations = {}
    #locations = defaultdict(lambda:[],locations)
    states = {}
    for m in museums:
        print locations
        print m.state
        if m.state in locations.keys():
            print m.city
            if m.city not in states[m.state]:
                print 'old state new city'
                states[m.state].append(m.city)
                newMuseum = {}
                newMuseum['city'] = m.city
                newMuseum['museum'] = [m.name]
                newMuseum['pk'] = [m.pk]
                locations[m.state].append(newMuseum)
            else:
                for item in locations[m.state]:
                    print item['city'] + "----"
                    if item['city'] == m.city:
                        item['museum'].append(m.name)
                        item['pk'].append(m.pk)

                #locations[m.state]['museum'].append(m.name)
                #locations[m.state]['pk'].append(m.pk)

        else:
            states[m.state] = []
            states[m.state].append(m.city)
            locations[m.state] = []
            newMuseum = {}
            newMuseum['city'] = m.city
            newMuseum['museum'] = [m.name]
            newMuseum['pk'] = [m.pk]
            locations[m.state].append(newMuseum)



    return JSONResponse(locations)
