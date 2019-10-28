package com.nand_project.moviecatalogue.activity.detail;

import android.graphics.drawable.Drawable;

import com.nand_project.moviecatalogue.model.ResultMovieModel;
import com.nand_project.moviecatalogue.model.ResultTvShowModel;

public interface DetailActivityView {
    void showLoading();
    void hideLoading();
    void onGetResultMovie(ResultMovieModel movieModel);
    void onGetResultTvShow(ResultTvShowModel tvShowModel);
    void setFavIcon(Drawable drawable, boolean b);
}
