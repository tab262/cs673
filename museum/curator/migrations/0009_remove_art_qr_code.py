# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('curator', '0008_art_qr_code'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='art',
            name='qr_code',
        ),
    ]
