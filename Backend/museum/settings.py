"""
Django settings for museum project.

For more information on this file, see
https://docs.djangoproject.com/en/1.7/topics/settings/

For the full list of settings and their values, see
https://docs.djangoproject.com/en/1.7/ref/settings/
"""

# Build paths inside the project like this: os.path.join(BASE_DIR, ...)
import os
BASE_DIR = os.path.dirname(os.path.dirname(__file__))


# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/1.7/howto/deployment/checklist/

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = '^2pl4_1o(v_ariy$2a76#mfar3nqy)rr4g&uzl%*ug*k3&y8a^'

# SECURITY WARNING: don't run with debug turned on in production!
if "gaddis" in BASE_DIR:
    DEBUG = True
else:
    DEBUG = True

TEMPLATE_DEBUG = True

ALLOWED_HOSTS = []

TESTING = False;

ACCOUNT_ACTIVATION_DAYS = 7 # One-week activation window;
REGISTRATION_AUTO_LOGIN = True # Automatically log the user in.
#allows testing send_mail without stmp server
EMAIL_BACKEND = 'django.core.mail.backends.console.EmailBackend'

TEMPLATE_DIRS=[os.path.join(BASE_DIR,'templates')]


# Application definition


INSTALLED_APPS = (
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'rest_framework',
    'curator',
    'storages',
    'registration',
    'CustomRegistration',
)

MIDDLEWARE_CLASSES = (
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.auth.middleware.SessionAuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
)

ROOT_URLCONF = 'museum.urls'

WSGI_APPLICATION = 'museum.wsgi.application'


# Database
# https://docs.djangoproject.com/en/1.7/ref/settings/#databases
#

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': 'testing'
        #'USER': 
        #'PASSWORD': dbpw,
        #'HOST': 
        #'PORT': '5432',
    }
}

if (not TESTING) and ('gaddis' in BASE_DIR) or ("ashley" in BASE_DIR) or ("Sukayneh" in BASE_DIR):
    #print "working locally"

    '''
    dbpw = open('s.txt','r').read().split("\n")[0]
    db = open('db.txt','r').read().split("\n")
    
    DATABASES = {
    'default': {

        }
    }
    '''
elif TESTING and ('gaddis' in BASE_DIR) or ("ashley" in BASE_DIR) or ("Sukayneh" in BASE_DIR):
    DATABASES = {
        'default': {
            'ENGINE': 'django.db.backends.sqlite3',
            'NAME': 'database.sqlite',
        }
    }



else:
    print "Working on heroku"
    import dj_database_url
    DATABASES['default'] =  dj_database_url.config()


# Internationalization
# https://docs.djangoproject.com/en/1.7/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_L10N = True

USE_TZ = True


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/1.7/howto/static-files/




# Honor the 'X-Forwarded-Proto' header for request.is_secure()
SECURE_PROXY_SSL_HEADER = ('HTTP_X_FORWARDED_PROTO', 'https')

# Allow all host headers
ALLOWED_HOSTS = ['*']

# Static asset configuration
import os
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
STATIC_ROOT = 'staticfiles'
STATIC_URL = '/static/'

STATICFILES_DIRS = (
    os.path.join(BASE_DIR, 'static'),
    os.path.join(BASE_DIR, 'static/admin'),
)


DEFAULT_FILE_STORAGE = 'storages.backends.s3boto.S3BotoStorage'
AWS_STORAGE_BUCKET_NAME = 'edocent'

#STATICFILES_STORAGE = 'whitenoise.django.GzipManifestStaticFilesStorage'

#ADMIN_MEDIA_PREFIX = '/static/admin/' 
