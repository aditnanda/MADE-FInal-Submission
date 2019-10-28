package com.nand_project.moviecatalogue.activity.detail;

import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.data.factory.AppDatabase;
import com.nand_project.moviecatalogue.model.FavoritMovieModel;
import com.nand_project.moviecatalogue.model.FavoritTvShowModel;
import com.nand_project.moviecatalogue.model.ResultMovieModel;
import com.nand_project.moviecatalogue.model.ResultTvShowModel;

public class DetailActivityPresenter {
    private DetailActivityView view;
    private Context context;
    private Intent intent;
    private AppDatabase db;


    public DetailActivityPresenter(DetailActivityView view, Context context, Intent intent){
        this.view = view;
        this.context = context;
        this.intent = intent;
    }

    public void setDataMovie() {
        ResultMovieModel movieModel;
        movieModel = intent.getParcelableExtra("movie_model");
        view.onGetResultMovie(movieModel);
        Log.d("tes movie model", movieModel.getTitle());
        setCheckFavoritMovie(movieModel);
    }

    public void setDataTvShow() {
        ResultTvShowModel tvShowModel;
        tvShowModel = intent.getParcelableExtra("movie_model");
        view.onGetResultTvShow(tvShowModel);
        Log.d("tes movie model", tvShowModel.getName());
        setCheckFavoritTvShow(tvShowModel);
    }

    private void setCheckFavoritTvShow(ResultTvShowModel tvShowModel) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();
        Log.d("check fav", db.favTvShowDAO().selectDataDetail(tvShowModel.getId())+"");
        if (db.favTvShowDAO().selectDataDetail(tvShowModel.getId()) != null){
            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite), true);
        }else {
            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border) , false);
        }
    }

    public void setData() {
        if (intent.getStringExtra("mode").equals("movie")){
            view.showLoading();
            setDataMovie();
            view.hideLoading();
        }else {
            view.showLoading();
            setDataTvShow();
            view.hideLoading();
        }
    }

    private void setCheckFavoritMovie(ResultMovieModel movieModel) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();
        Log.d("check fav", db.favMovieDAO().selectDataDetail(movieModel.getId())+"");
        if (db.favMovieDAO().selectDataDetail(movieModel.getId()) != null){
            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite), true);
        }else {
            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border) , false);
        }
    }

    public void setFavoritMovie(boolean b, ResultMovieModel movieModel){
        boolean flag = b;

        Intent intent = new Intent("reload-widget-movie");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        if (!flag) {

            //benar
            b = true;
            FavoritMovieModel favoritMovieModel = new FavoritMovieModel();
            favoritMovieModel.setId(movieModel.getId());
            favoritMovieModel.setBackdrop_path(movieModel.getBackdrop_path());
            favoritMovieModel.setOverview(movieModel.getOverview());
            favoritMovieModel.setPoster_path(movieModel.getPoster_path());
            favoritMovieModel.setRelease_date(movieModel.getRelease_date());
            favoritMovieModel.setTitle(movieModel.getTitle());
            favoritMovieModel.setVote_average(movieModel.getVote_average());
            Log.d("favmov_id",movieModel.getId().toString());
            insertDataFavMovie(favoritMovieModel);

            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite), b);

        } else if (flag) {

            //tidak benar
            b = false;
            AppDatabase db = Room.databaseBuilder(context,
                    AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();
            FavoritMovieModel favoritMovieModel = new FavoritMovieModel();
            favoritMovieModel.setId(movieModel.getId());
            favoritMovieModel.setBackdrop_path(movieModel.getBackdrop_path());
            favoritMovieModel.setOverview(movieModel.getOverview());
            favoritMovieModel.setPoster_path(movieModel.getPoster_path());
            favoritMovieModel.setRelease_date(movieModel.getRelease_date());
            favoritMovieModel.setTitle(movieModel.getTitle());
            favoritMovieModel.setVote_average(movieModel.getVote_average());
            db.favMovieDAO().deleteData(favoritMovieModel);

            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border) , b);

        }
    }

    private void insertDataFavMovie(final FavoritMovieModel favoritMovieModel){
        db = Room.databaseBuilder(context,
                AppDatabase.class, "db_moviecatalogue").fallbackToDestructiveMigration().build();

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.favMovieDAO().insertData(favoritMovieModel);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
//                Toast.makeText(context, "status row "+status, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, db.favMovieDAO().selectAllData().toString(), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public void setFavoritTvShow(boolean b, ResultTvShowModel tvShowModel){
        boolean flag = b;

        if (!flag) {

            //benar
            b = true;
            FavoritTvShowModel favoritTvShowModel = new FavoritTvShowModel();
            favoritTvShowModel.setId(tvShowModel.getId());
            favoritTvShowModel.setBackdrop_path(tvShowModel.getBackdrop_path());
            favoritTvShowModel.setOverview(tvShowModel.getOverview());
            favoritTvShowModel.setPoster_path(tvShowModel.getPoster_path());
            favoritTvShowModel.setFirst_air_date(tvShowModel.getFirst_air_date());
            favoritTvShowModel.setName(tvShowModel.getName());
            favoritTvShowModel.setVote_average(tvShowModel.getVote_average());
            Log.d("favmov_id",favoritTvShowModel.getId().toString());
            insertDataFavTvShow(favoritTvShowModel);

            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite), b);

        } else if (flag) {

            //tidak benar
            b = false;
            AppDatabase db = Room.databaseBuilder(context,
                    AppDatabase.class, "db_moviecatalogue").allowMainThreadQueries().build();
            FavoritTvShowModel favoritTvShowModel = new FavoritTvShowModel();
            favoritTvShowModel.setId(tvShowModel.getId());
            favoritTvShowModel.setBackdrop_path(tvShowModel.getBackdrop_path());
            favoritTvShowModel.setOverview(tvShowModel.getOverview());
            favoritTvShowModel.setPoster_path(tvShowModel.getPoster_path());
            favoritTvShowModel.setFirst_air_date(tvShowModel.getFirst_air_date());
            favoritTvShowModel.setName(tvShowModel.getName());
            favoritTvShowModel.setVote_average(tvShowModel.getVote_average());
            db.favTvShowDAO().deleteData(favoritTvShowModel);

            view.setFavIcon(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border) , b);

        }
    }

    private void insertDataFavTvShow(final FavoritTvShowModel favoritTvShowModel){
        db = Room.databaseBuilder(context,
                AppDatabase.class, "db_moviecatalogue").build();

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.favTvShowDAO().insertData(favoritTvShowModel);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
//                Toast.makeText(context, "status row "+status, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, db.favMovieDAO().selectAllData().toString(), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}
