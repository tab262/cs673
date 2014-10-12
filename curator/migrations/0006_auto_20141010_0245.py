# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0005_auto_20141010_0225'),
    ]

    operations = [
        migrations.AlterField(
            model_name='museum',
            name='description',
            field=models.CharField(default=None, max_length=800),
        ),
        migrations.AlterField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(max_digits=30, decimal_places=20, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(max_digits=30, decimal_places=20, blank=True),
        ),
    ]
