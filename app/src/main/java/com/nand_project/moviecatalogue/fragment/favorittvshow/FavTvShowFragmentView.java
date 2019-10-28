package com.nand_project.moviecatalogue.fragment.favorittvshow;

import com.nand_project.moviecatalogue.model.FavoritTvShowModel;

import java.util.ArrayList;

public interface FavTvShowFragmentView {
    void showLoading();
    void hideLoading();
    void onGetResult(ArrayList<FavoritTvShowModel> resultTvShowModels);
    void onErrorLoading(String localizedMessage);
}
