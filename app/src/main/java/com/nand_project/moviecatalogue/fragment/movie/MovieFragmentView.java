package com.nand_project.moviecatalogue.fragment.movie;

import com.nand_project.moviecatalogue.model.ResultMovieModel;

import java.util.ArrayList;

public interface MovieFragmentView {
    void showLoading();
    void hideLoading();
    void onGetResult(ArrayList<ResultMovieModel> resultMovieModels);
    void onErrorLoading(String localizedMessage);
}
