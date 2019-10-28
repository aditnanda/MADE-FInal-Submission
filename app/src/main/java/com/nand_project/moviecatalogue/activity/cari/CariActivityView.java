package com.nand_project.moviecatalogue.activity.cari;

import com.nand_project.moviecatalogue.model.ResultMovieModel;
import com.nand_project.moviecatalogue.model.ResultTvShowModel;

import java.util.ArrayList;

public interface CariActivityView {
    void showLoading();
    void hideLoading();
    void onGetResultMovie(ArrayList<ResultMovieModel> resultMovieModels);
    void onGetResultTvShow(ArrayList<ResultTvShowModel> resultTvShowModels);
    void onErrorLoading(String localizedMessage);
}
