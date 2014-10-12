# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('curator', '0002_auto_20141009_0037'),
    ]

    operations = [
        migrations.AddField(
            model_name='museum',
            name='username',
            field=models.OneToOneField(default=None, to=settings.AUTH_USER_MODEL),
            preserve_default=True,
        ),
    ]
