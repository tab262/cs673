# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(max_digits=12, decimal_places=8),
        ),
        migrations.AlterField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(max_digits=12, decimal_places=8),
        ),
    ]
