# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='art',
            name='title',
            field=models.CharField(default=b'untitled', max_length=100),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='museum',
            name='name',
            field=models.CharField(default=b'Test Museum', max_length=200),
        ),
    ]
