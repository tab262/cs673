# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0005_auto_20141009_0002'),
    ]

    operations = [
        migrations.RenameField(
            model_name='museum',
            old_name='author',
            new_name='owner',
        ),
        migrations.AlterField(
            model_name='museum',
            name='latitude',
            field=models.DecimalField(null=True, editable=False, max_digits=40, decimal_places=30, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='longitude',
            field=models.DecimalField(null=True, editable=False, max_digits=40, decimal_places=30, blank=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='name',
            field=models.CharField(max_length=200, null=True),
        ),
    ]
