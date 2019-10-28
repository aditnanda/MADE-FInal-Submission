/*
 * Created by Jihad044.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 25/10/18 9:55 AM.
 */

package com.nand_project.moviecatalogue.widget.movie;

import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.data.factory.AppDatabase;
import com.nand_project.moviecatalogue.model.FavoritMovieModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.nand_project.moviecatalogue.api.ApiURL.POSTER_URL;


public class MovieStackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;

    private ArrayList<FavoritMovieModel> resultMovieModels;

    public MovieStackRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {
        resultMovieModels = new ArrayList<>();
        AppDatabase db = Room.databaseBuilder(mContext,
                AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();

        resultMovieModels.addAll(Arrays.asList(db.favMovieDAO().selectAllData()));
    }

    @Override
    public void onDataSetChanged() {
        if (resultMovieModels != null){
            resultMovieModels.clear();
        }

        final long identityToken = Binder.clearCallingIdentity();

        resultMovieModels = new ArrayList<>();
        AppDatabase db = Room.databaseBuilder(mContext,
                AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();

        resultMovieModels.addAll(Arrays.asList(db.favMovieDAO().selectAllData()));

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return resultMovieModels.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.favourite_widget_item);

        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(POSTER_URL+"w185"+resultMovieModels.get(position).getPoster_path())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        rv.setImageViewBitmap(R.id.imageView, bitmap);

        Bundle extras = new Bundle();
        extras.putInt(MovieFavouriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


}
