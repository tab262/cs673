# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Art',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('title', models.CharField(max_length=200)),
                ('artType', models.CharField(max_length=200)),
                ('creationDate', models.DateTimeField(verbose_name=b'creation date')),
                ('image', models.CharField(max_length=200)),
                ('audio', models.CharField(max_length=200)),
                ('description', models.CharField(max_length=200)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Artist',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(max_length=200)),
                ('nationality', models.CharField(max_length=200)),
                ('biography', models.CharField(max_length=200)),
                ('dateOfBirth', models.DateTimeField(verbose_name=b'DoB')),
                ('dateOfDeath', models.DateTimeField(verbose_name=b'DoD')),
                ('image', models.CharField(max_length=200)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Exhibitions',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('dateTime', models.DateTimeField(verbose_name=b'DoD')),
                ('features', models.CharField(max_length=200)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Museum',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(max_length=200)),
                ('streetAddress', models.CharField(max_length=200)),
                ('city', models.CharField(max_length=200)),
                ('state', models.CharField(max_length=2)),
                ('zipCode', models.CharField(max_length=5)),
                ('latitude', models.DecimalField(max_digits=8, decimal_places=2)),
                ('longitude', models.DecimalField(max_digits=8, decimal_places=2)),
                ('openingHours_M', models.CharField(max_length=200)),
                ('openingHours_T', models.CharField(max_length=200)),
                ('openingHours_W', models.CharField(max_length=200)),
                ('openingHours_R', models.CharField(max_length=200)),
                ('openingHours_F', models.CharField(max_length=200)),
                ('openingHours_ST', models.CharField(max_length=200)),
                ('openingHours_SN', models.CharField(max_length=200)),
                ('events', models.CharField(default=None, max_length=200)),
                ('image', models.CharField(default=None, max_length=200)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Tours',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('dateTime', models.DateTimeField(verbose_name=b'DoD')),
                ('features', models.CharField(max_length=200)),
                ('museum', models.ForeignKey(to='curator.Museum')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.AddField(
            model_name='exhibitions',
            name='museum',
            field=models.ForeignKey(to='curator.Museum'),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='art',
            name='artist',
            field=models.ForeignKey(to='curator.Artist'),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='art',
            name='museum',
            field=models.ForeignKey(to='curator.Museum'),
            preserve_default=True,
        ),
    ]
