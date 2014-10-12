# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('curator', '0003_museum_username'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='museum',
            name='username',
        ),
        migrations.AddField(
            model_name='art',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='artist',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='exhibitions',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='description',
            field=models.CharField(default=None, max_length=400),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='website',
            field=models.URLField(null=True, blank=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='tours',
            name='owner',
            field=models.ForeignKey(blank=True, to=settings.AUTH_USER_MODEL, null=True),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='art',
            name='description',
            field=models.TextField(),
        ),
        migrations.AlterField(
            model_name='museum',
            name='events',
            field=models.CharField(default=None, max_length=200, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='image',
            field=models.CharField(default=None, max_length=200, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(null=True, max_digits=30, decimal_places=20, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(null=True, max_digits=30, decimal_places=20, blank=True),
        ),
    ]
