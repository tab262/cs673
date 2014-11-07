# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('curator', '0004_auto_20140924_0231'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='museum',
            name='location',
        ),
        migrations.DeleteModel(
            name='Location',
        ),
        migrations.AddField(
            model_name='museum',
            name='address',
            field=models.CharField(max_length=300, null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='author',
            field=models.ForeignKey(blank=True, editable=False, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(null=True, max_digits=40, decimal_places=30, blank=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(null=True, max_digits=40, decimal_places=30, blank=True),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='museum',
            name='collections',
            field=models.TextField(null=True, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='tour_times',
            field=models.TextField(null=True, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='website',
            field=models.URLField(null=True, blank=True),
        ),
    ]
