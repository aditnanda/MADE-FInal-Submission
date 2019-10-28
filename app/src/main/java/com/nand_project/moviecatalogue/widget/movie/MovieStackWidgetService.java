/*
 * Created by Jihad044.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 25/10/18 9:45 AM.
 */

package com.nand_project.moviecatalogue.widget.movie;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MovieStackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieStackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}