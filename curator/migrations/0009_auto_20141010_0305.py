# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0008_auto_20141010_0302'),
    ]

    operations = [
        migrations.AlterField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(default=0.0, max_digits=30, decimal_places=15),
        ),
        migrations.AlterField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(default=0.0, max_digits=30, decimal_places=15),
        ),
    ]
