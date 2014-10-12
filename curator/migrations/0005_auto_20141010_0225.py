# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0004_auto_20141010_0214'),
    ]

    operations = [
        migrations.AlterField(
            model_name='museum',
            name='description',
            field=models.CharField(default=None, max_length=400, null=True),
        ),
    ]
