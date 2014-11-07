# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import geoposition.fields


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0002_auto_20140916_2143'),
    ]

    operations = [
        migrations.CreateModel(
            name='Location',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('address_1', models.CharField(max_length=200)),
                ('address_2', models.CharField(max_length=200, null=True)),
                ('city', models.CharField(max_length=100)),
                ('state', models.CharField(max_length=50)),
                ('position', geoposition.fields.GeopositionField(max_length=42)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.RenameField(
            model_name='art',
            old_name='time',
            new_name='year_created',
        ),
        migrations.AlterField(
            model_name='museum',
            name='location',
            field=models.ForeignKey(to='curator.Location'),
        ),
    ]
