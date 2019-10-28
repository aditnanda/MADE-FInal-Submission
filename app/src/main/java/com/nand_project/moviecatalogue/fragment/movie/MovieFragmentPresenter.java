package com.nand_project.moviecatalogue.fragment.movie;

import android.content.Context;

import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.api.RetrofitClient;
import com.nand_project.moviecatalogue.model.MovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nand_project.moviecatalogue.api.ApiURL.MOVIE_URL;

public class MovieFragmentPresenter {
    private MovieFragmentView view;
    private Context context;

    public MovieFragmentPresenter(MovieFragmentView view, Context context){
        this.view = view;
        this.context = context;
    }

    void getData(){
        view.showLoading();

        Call<MovieModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMovie(MOVIE_URL+context.getString(R.string.language));

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                view.hideLoading();
                if (response.isSuccessful()&& response.body() != null){
                    view.onGetResult(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }


}
