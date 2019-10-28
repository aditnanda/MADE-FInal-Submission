package com.nand_project.moviecatalogue.fragment.favoritmovie;

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
import com.nand_project.moviecatalogue.adapter.FavMovieAdapter;
import com.nand_project.moviecatalogue.model.FavoritMovieModel;

import java.util.ArrayList;

public class FavMovieFragment extends Fragment implements FavMovieFragmentView, SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView myrv;
    private RecyclerView.LayoutManager layoutManager;
    private FavMovieAdapter myAdapter;
    private FavMovieFragmentPresenter presenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String KEY_MOVIES = "fav_movie";
    private ArrayList<FavoritMovieModel> movies;

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
        }else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            myAdapter = new FavMovieAdapter(getActivity(),movies);
            myrv.setAdapter(myAdapter);
        }

    }

    private void setPresenter() {

        presenter = new FavMovieFragmentPresenter(this,getActivity());
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
    public void onGetResult(ArrayList<FavoritMovieModel> resultMovieModels) {
        movies = resultMovieModels;
        myAdapter = new FavMovieAdapter(getActivity(),resultMovieModels);
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
    public void onResume() {
        super.onResume();
        setPresenter();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }
}
