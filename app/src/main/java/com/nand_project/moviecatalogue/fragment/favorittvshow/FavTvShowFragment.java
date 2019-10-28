package com.nand_project.moviecatalogue.fragment.favorittvshow;

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
import com.nand_project.moviecatalogue.adapter.FavTvShowAdapter;
import com.nand_project.moviecatalogue.model.FavoritTvShowModel;

import java.util.ArrayList;

public class FavTvShowFragment extends Fragment implements FavTvShowFragmentView, SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView myrv;
    private RecyclerView.LayoutManager layoutManager;
    private FavTvShowAdapter myAdapter;
    private FavTvShowFragmentPresenter presenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<FavoritTvShowModel> tvShowModels;
    private String KEY_TV_SHOWS = "fav_tv_show";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        myrv = view.findViewById(R.id.recyclerview_tv_show);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        layoutManager = new LinearLayoutManager(getActivity());
        myrv.setLayoutManager(layoutManager);

        setState(savedInstanceState);
        return view;
    }

    private void setState(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            setPresenter();
        } else {
            tvShowModels = savedInstanceState.getParcelableArrayList(KEY_TV_SHOWS);
            myAdapter = new FavTvShowAdapter(getActivity(),tvShowModels);
            myrv.setAdapter(myAdapter);
        }

    }

    private void setPresenter() {
        presenter = new FavTvShowFragmentPresenter(this,getActivity());
        presenter.getData();
    }


    @Override
    public void onRefresh() {
        setPresenter();
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
    public void onGetResult(ArrayList<FavoritTvShowModel> resultTvShowModels) {
        tvShowModels = resultTvShowModels;
        myAdapter = new FavTvShowAdapter(getActivity(),resultTvShowModels);
        myAdapter.notifyDataSetChanged();
        myrv.setAdapter(myAdapter);
    }

    @Override
    public void onErrorLoading(String localizedMessage) {
        Toast.makeText(getActivity(), localizedMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        setPresenter();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV_SHOWS, tvShowModels);
        super.onSaveInstanceState(outState);
    }
}
