# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0003_auto_20140921_2315'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='location',
            name='position',
        ),
        migrations.AddField(
            model_name='museum',
            name='collections',
            field=models.TextField(null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='tour_times',
            field=models.TextField(null=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='museum',
            name='website',
            field=models.URLField(null=True),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='museum',
            name='hours',
            field=models.TextField(null=True),
        ),
        migrations.AlterField(
            model_name='museum',
            name='location',
            field=models.ForeignKey(to='curator.Location', null=True),
        ),
    ]
