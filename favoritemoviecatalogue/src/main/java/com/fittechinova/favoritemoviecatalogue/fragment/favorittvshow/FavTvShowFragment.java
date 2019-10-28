package com.fittechinova.favoritemoviecatalogue.fragment.favorittvshow;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fittechinova.favoritemoviecatalogue.R;
import com.fittechinova.favoritemoviecatalogue.adapter.FavTvShowAdapter;

public class FavTvShowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor>{
    private RecyclerView myrv;
    private RecyclerView.LayoutManager layoutManager;
    private FavTvShowAdapter myAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private final int LOAD_NOTES_ID = 2;
    public static final String AUTHORITY = "com.nand_project.moviecatalogue.provider.tv";

    public static final Uri URI_FAVTVSHOW = Uri.parse(
            "content://" + AUTHORITY + "/" + "tb_fav_tv_show");

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

        myAdapter = new FavTvShowAdapter(getActivity());


        layoutManager = new LinearLayoutManager(getActivity());

        mSwipeRefreshLayout.setRefreshing(true);
        getLoaderManager().initLoader(LOAD_NOTES_ID, null, this);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(myAdapter);

        return view;
    }

    private void setLoader() {
        mSwipeRefreshLayout.setRefreshing(true);
        getLoaderManager().restartLoader(LOAD_NOTES_ID, null, this);
    }


    @Override
    public void onRefresh() {
        setLoader();
    }


    @Override
    public void onResume() {
        super.onResume();
        setLoader();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(
                getActivity(),
                URI_FAVTVSHOW,
                null,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        myAdapter.setFavMovie(cursor);
        mSwipeRefreshLayout.setRefreshing(false);    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        myAdapter.setFavMovie(null);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
