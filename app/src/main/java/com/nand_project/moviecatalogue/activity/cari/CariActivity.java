package com.nand_project.moviecatalogue.activity.cari;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.adapter.MovieAdapter;
import com.nand_project.moviecatalogue.adapter.TvShowAdapter;
import com.nand_project.moviecatalogue.model.ResultMovieModel;
import com.nand_project.moviecatalogue.model.ResultTvShowModel;

import java.util.ArrayList;

public class CariActivity extends AppCompatActivity implements CariActivityView, SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener{

    private RadioGroup rgMovie;
    private RadioButton rbMovTemp, rbMovie, rbTvshow;
    private SearchView svCari;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CariActivityPresenter presenter;

    private RecyclerView myrv;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter movieAdapter;
    private TvShowAdapter tvShowAdapter;
    private int selected;
    private String query;
    private ArrayList<ResultMovieModel> movies;
    private ArrayList<ResultTvShowModel> tvShowModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        setTitle(getString(R.string.pencarian));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rgMovie = findViewById(R.id.rg_movie);
        rbMovie = findViewById(R.id.movie);
        rbTvshow = findViewById(R.id.tvshow);
        svCari = findViewById(R.id.searchView);
        myrv = findViewById(R.id.recyclerview_movie);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        layoutManager = new LinearLayoutManager(CariActivity.this);
        myrv.setLayoutManager(layoutManager);

        presenter = new CariActivityPresenter(this,CariActivity.this);

        selected = rgMovie.getCheckedRadioButtonId();
        rbMovTemp = findViewById(selected);

        setState(savedInstanceState);

        svCari.setOnQueryTextListener(this);

    }

    private void setState(Bundle savedInstanceState){
        if (savedInstanceState == null) {
        } else {
            selected = savedInstanceState.getInt("selected");
            query = savedInstanceState.getString("query");
            rbMovTemp = findViewById(selected);
            if (rbMovTemp.getText().toString().toLowerCase().equals("movies") || rbMovTemp.getText().toString().toLowerCase().equals("film")){
                rbMovie.setChecked(true);
                movies = savedInstanceState.getParcelableArrayList("movies");
                movieAdapter = new MovieAdapter(CariActivity.this,movies);
                movieAdapter.notifyDataSetChanged();
                myrv.setAdapter(movieAdapter);
            }else{
                rbTvshow.setChecked(true);
                tvShowModels = savedInstanceState.getParcelableArrayList("tvshow");
                tvShowAdapter = new TvShowAdapter(CariActivity.this,tvShowModels);
                tvShowAdapter.notifyDataSetChanged();
                myrv.setAdapter(tvShowAdapter);
            }


//            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
//            myAdapter = new MovieAdapter(getActivity(),movies);
//            myrv.setAdapter(myAdapter);
        }

    }

    private void setPresenterTvShow(String s) {
        presenter.getDataTvShow(s);
    }

    private void setPresenterMovie(String s) {
        presenter.getDataMovie(s);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onRefresh() {
        selected = rgMovie.getCheckedRadioButtonId();
        rbMovTemp = findViewById(selected);
        if (rbMovTemp.getText().toString().toLowerCase().equals("movies") || rbMovTemp.getText().toString().toLowerCase().equals("film")){
            setPresenterMovie(query);
        }else{
            setPresenterTvShow(query);
        }
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onGetResultMovie(ArrayList<ResultMovieModel> resultMovieModels) {
        movies = resultMovieModels;
        movieAdapter = new MovieAdapter(CariActivity.this,resultMovieModels);
        movieAdapter.notifyDataSetChanged();
        myrv.setAdapter(movieAdapter);
    }

    @Override
    public void onGetResultTvShow(ArrayList<ResultTvShowModel> resultTvShowModels) {
        tvShowModels = resultTvShowModels;
        tvShowAdapter = new TvShowAdapter(CariActivity.this,resultTvShowModels);
        tvShowAdapter.notifyDataSetChanged();
        myrv.setAdapter(tvShowAdapter);
    }

    @Override
    public void onErrorLoading(String localizedMessage) {
        Toast.makeText(this, localizedMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        query = s;
        selected = rgMovie.getCheckedRadioButtonId();
        rbMovTemp = findViewById(selected);
        if (rbMovTemp.getText().toString().toLowerCase().equals("movies") || rbMovTemp.getText().toString().toLowerCase().equals("film")){
            setPresenterMovie(s);
        }else{
            setPresenterTvShow(s);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        query = s;
        selected = rgMovie.getCheckedRadioButtonId();
        rbMovTemp = findViewById(selected);
        if (rbMovTemp.getText().toString().toLowerCase().equals("movies") || rbMovTemp.getText().toString().toLowerCase().equals("film")){
            setPresenterMovie(s);
        }else{
            setPresenterTvShow(s);
        }
        return false;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("movies", movies);
        outState.putParcelableArrayList("tvshow",tvShowModels);
        outState.putInt("selected",selected);
        outState.putString("query",query);
        super.onSaveInstanceState(outState);
    }

}
