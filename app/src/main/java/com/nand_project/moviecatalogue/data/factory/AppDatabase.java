package com.nand_project.moviecatalogue.data.factory;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.nand_project.moviecatalogue.data.FavMovieDAO;
import com.nand_project.moviecatalogue.data.FavTvShowDAO;
import com.nand_project.moviecatalogue.model.FavoritMovieModel;
import com.nand_project.moviecatalogue.model.FavoritTvShowModel;

@Database(entities = {FavoritMovieModel.class, FavoritTvShowModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavMovieDAO favMovieDAO();
    public abstract FavTvShowDAO favTvShowDAO();

    private static AppDatabase sInstance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db_moviecatalogue")
                    .build();
        }
        return sInstance;
    }
}
