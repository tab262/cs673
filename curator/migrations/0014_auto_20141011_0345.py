# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0013_auto_20141010_0342'),
    ]

    operations = [
        migrations.AddField(
            model_name='museum',
            name='membership',
            field=models.CharField(default=None, max_length=200),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='parking',
            field=models.CharField(default=None, max_length=200),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='ticket_prices',
            field=models.CharField(default=None, max_length=200),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='visitor_info',
            field=models.CharField(default=None, max_length=200),
            preserve_default=True,
        ),
    ]
