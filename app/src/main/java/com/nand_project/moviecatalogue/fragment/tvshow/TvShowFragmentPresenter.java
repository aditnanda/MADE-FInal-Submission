package com.nand_project.moviecatalogue.fragment.tvshow;

import android.content.Context;

import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.api.RetrofitClient;
import com.nand_project.moviecatalogue.model.TvShowModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nand_project.moviecatalogue.api.ApiURL.TVSHOW_URL;

public class TvShowFragmentPresenter {
    private TvShowFragmentView view;
    private Context context;

    public TvShowFragmentPresenter(TvShowFragmentView view, Context context){
        this.view = view;
        this.context = context;
    }

    void getData(){
        view.showLoading();

        Call<TvShowModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getTvShow(TVSHOW_URL+context.getString(R.string.language));

        call.enqueue(new Callback<TvShowModel>() {
            @Override
            public void onResponse(Call<TvShowModel> call, Response<TvShowModel> response) {

                view.hideLoading();
                if (response.isSuccessful()&& response.body() != null){
                    view.onGetResult(response.body().getResults());
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
