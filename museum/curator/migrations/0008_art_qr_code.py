# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0007_auto_20141009_1733'),
    ]

    operations = [
        migrations.AddField(
            model_name='art',
            name='qr_code',
            field=models.ImageField(null=True, upload_to=b'', blank=True),
            preserve_default=True,
        ),
    ]
