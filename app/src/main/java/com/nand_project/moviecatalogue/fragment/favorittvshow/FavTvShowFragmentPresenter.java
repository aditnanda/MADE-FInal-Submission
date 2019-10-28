package com.nand_project.moviecatalogue.fragment.favorittvshow;

import androidx.room.Room;
import android.content.Context;

import com.nand_project.moviecatalogue.data.factory.AppDatabase;
import com.nand_project.moviecatalogue.model.FavoritTvShowModel;

import java.util.ArrayList;
import java.util.Arrays;

public class FavTvShowFragmentPresenter {
    private FavTvShowFragmentView view;
    private Context context;

    public FavTvShowFragmentPresenter(FavTvShowFragmentView view, Context context){
        this.view = view;
        this.context = context;
    }

    void getData(){
        view.showLoading();

        ArrayList<FavoritTvShowModel> favoritTvShowModels = new ArrayList<>();
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        favoritTvShowModels.addAll(Arrays.asList(db.favTvShowDAO().selectAllData()));
        view.onGetResult(favoritTvShowModels);

        view.hideLoading();
    }


}
