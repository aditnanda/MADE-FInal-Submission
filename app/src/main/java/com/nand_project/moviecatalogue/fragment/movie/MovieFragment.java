package com.nand_project.moviecatalogue.fragment.movie;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.adapter.MovieAdapter;
import com.nand_project.moviecatalogue.model.ResultMovieModel;

import java.util.ArrayList;

public class MovieFragment extends Fragment implements MovieFragmentView, SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView myrv;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter myAdapter;
    private MovieFragmentPresenter presenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String KEY_MOVIES = "movie";
    private ArrayList<ResultMovieModel> movies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        myrv = view.findViewById(R.id.recyclerview_movie);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        layoutManager = new LinearLayoutManager(getActivity());
        myrv.setLayoutManager(layoutManager);

        setState(savedInstanceState );
        return view;
    }

    private void setState(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            setPresenter();
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            myAdapter = new MovieAdapter(getActivity(),movies);
            myrv.setAdapter(myAdapter);
        }

    }

    private void setPresenter() {

        presenter = new MovieFragmentPresenter(this,getActivity());
        presenter.getData();
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
    public void onGetResult(ArrayList<ResultMovieModel> resultMovieModels) {
        movies = resultMovieModels;
        myAdapter = new MovieAdapter(getActivity(),resultMovieModels);
        myAdapter.notifyDataSetChanged();
        myrv.setAdapter(myAdapter);
    }

    @Override
    public void onErrorLoading(String localizedMessage) {
        Toast.makeText(getActivity(), localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        setPresenter();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }
}
