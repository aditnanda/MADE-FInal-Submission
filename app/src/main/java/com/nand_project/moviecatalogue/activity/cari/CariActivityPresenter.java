package com.nand_project.moviecatalogue.activity.cari;

import android.content.Context;

import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.api.RetrofitClient;
import com.nand_project.moviecatalogue.fragment.movie.MovieFragmentView;
import com.nand_project.moviecatalogue.model.MovieModel;
import com.nand_project.moviecatalogue.model.TvShowModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nand_project.moviecatalogue.api.ApiURL.MOVIE_URL;
import static com.nand_project.moviecatalogue.api.ApiURL.SEARCH_MOVIE_URL;
import static com.nand_project.moviecatalogue.api.ApiURL.SEARCH_TVSHOW_URL;

public class CariActivityPresenter {
    private CariActivityView view;
    private Context context;

    public CariActivityPresenter(CariActivityView view, Context context){
        this.view = view;
        this.context = context;
    }

    void getDataMovie(String s){
        view.showLoading();

        Call<MovieModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMovie(SEARCH_MOVIE_URL+context.getString(R.string.language)+"&query="+s);

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                view.hideLoading();
                if (response.isSuccessful()&& response.body() != null){
                    view.onGetResultMovie(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }


    public void getDataTvShow(String s) {
        view.showLoading();

        Call<TvShowModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getTvShow(SEARCH_TVSHOW_URL+context.getString(R.string.language)+"&query="+s);

        call.enqueue(new Callback<TvShowModel>() {
            @Override
            public void onResponse(Call<TvShowModel> call, Response<TvShowModel> response) {

                view.hideLoading();
                if (response.isSuccessful()&& response.body() != null){
                    view.onGetResultTvShow(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<TvShowModel> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
