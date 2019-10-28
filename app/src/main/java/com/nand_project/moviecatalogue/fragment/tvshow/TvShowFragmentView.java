package com.nand_project.moviecatalogue.fragment.tvshow;

import com.nand_project.moviecatalogue.model.ResultTvShowModel;

import java.util.ArrayList;

public interface TvShowFragmentView {
    void showLoading();
    void hideLoading();
    void onGetResult(ArrayList<ResultTvShowModel> resultTvShowModels);
    void onErrorLoading(String localizedMessage);
}
