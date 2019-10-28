package com.nand_project.moviecatalogue.fragment.favoritmovie;

import androidx.room.Room;
import android.content.Context;

import com.nand_project.moviecatalogue.data.factory.AppDatabase;
import com.nand_project.moviecatalogue.model.FavoritMovieModel;

import java.util.ArrayList;
import java.util.Arrays;

public class FavMovieFragmentPresenter {
    private FavMovieFragmentView view;
    private Context context;

    public FavMovieFragmentPresenter(FavMovieFragmentView view, Context context){
        this.view = view;
        this.context = context;
    }

    void getData(){
        view.showLoading();

        ArrayList<FavoritMovieModel> favoritMovieModels = new ArrayList<>();
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();

        favoritMovieModels.addAll(Arrays.asList(db.favMovieDAO().selectAllData()));
        view.onGetResult(favoritMovieModels);

        view.hideLoading();
    }


}
