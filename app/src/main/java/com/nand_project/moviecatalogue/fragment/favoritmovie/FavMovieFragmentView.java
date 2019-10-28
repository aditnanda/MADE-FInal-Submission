package com.nand_project.moviecatalogue.fragment.favoritmovie;

import com.nand_project.moviecatalogue.model.FavoritMovieModel;

import java.util.ArrayList;

public interface FavMovieFragmentView {
    void showLoading();
    void hideLoading();
    void onGetResult(ArrayList<FavoritMovieModel> resultMovieModels);
    void onErrorLoading(String localizedMessage);
}
