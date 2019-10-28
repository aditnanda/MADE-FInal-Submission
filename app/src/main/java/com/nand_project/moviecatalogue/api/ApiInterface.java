package com.nand_project.moviecatalogue.api;

import com.nand_project.moviecatalogue.model.MovieModel;
import com.nand_project.moviecatalogue.model.TvShowModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET
    Call<MovieModel> getMovie(
            @Url String url
    );

    @GET
    Call<TvShowModel> getTvShow(
            @Url String url
    );
}
