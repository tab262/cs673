# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('curator', '0006_auto_20141009_0234'),
    ]

    operations = [
        migrations.AddField(
            model_name='art',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(null=True, max_digits=40, decimal_places=30, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(null=True, max_digits=40, decimal_places=30, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
        ),
    ]
