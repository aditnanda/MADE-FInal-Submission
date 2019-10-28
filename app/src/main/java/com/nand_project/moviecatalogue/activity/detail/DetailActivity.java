package com.nand_project.moviecatalogue.activity.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.model.ResultMovieModel;
import com.nand_project.moviecatalogue.model.ResultTvShowModel;

import static com.nand_project.moviecatalogue.api.ApiURL.POSTER_URL;

public class DetailActivity extends AppCompatActivity implements DetailActivityView, SwipeRefreshLayout.OnRefreshListener{
    private ImageView ivPoster, ivBackdrop;
    private TextView txtName;
    private TextView txtDescription, txtTglRilis, txtRating;
    private DetailActivityPresenter presenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FloatingActionButton fabFav;
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivPoster = findViewById(R.id.img_photo);
        ivBackdrop = findViewById(R.id.img_backdrop);
        txtName = findViewById(R.id.txt_name);
        txtTglRilis = findViewById(R.id.txt_tgl_rilis);
        txtDescription = findViewById(R.id.txt_description);
        txtRating = findViewById(R.id.txt_rating);
        fabFav = findViewById(R.id.myFavButton);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        presenter = new DetailActivityPresenter(this, DetailActivity.this, getIntent());

        setPresenter();

    }

    private void setPresenter() {
        presenter.setData();
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
    public void onGetResultMovie(final ResultMovieModel movieModel) {
        setTitle(movieModel.getTitle());

        txtName.setText(movieModel.getTitle());
        txtDescription.setText(movieModel.getOverview());
        txtTglRilis.setText(movieModel.getRelease_date());
        txtRating.setText(String.format("%s/10", movieModel.getVote_average()));

        Glide.with(DetailActivity.this)
                .load(POSTER_URL+"w185"+movieModel.getPoster_path())
                .placeholder(R.drawable.default_image)
                .into(ivPoster);

        Glide.with(DetailActivity.this)
                .load(POSTER_URL+"w780"+movieModel.getBackdrop_path())
                .placeholder(R.drawable.default_image)
                .into(ivBackdrop);

        fabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setFavoritMovie(flag, movieModel);
            }
        });
    }

    @Override
    public void onGetResultTvShow(final ResultTvShowModel tvShowModel) {
        setTitle(tvShowModel.getName());

        txtName.setText(tvShowModel.getName());
        txtDescription.setText(tvShowModel.getOverview());
        txtTglRilis.setText(tvShowModel.getFirst_air_date());
        txtRating.setText(String.format("%s/10", tvShowModel.getVote_average()));

        Glide.with(DetailActivity.this)
                .load(POSTER_URL+"w185"+tvShowModel.getPoster_path())
                .placeholder(R.drawable.default_image)
                .into(ivPoster);

        Glide.with(DetailActivity.this)
                .load(POSTER_URL+"w780"+tvShowModel.getBackdrop_path())
                .placeholder(R.drawable.default_image)
                .into(ivBackdrop);

        fabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setFavoritTvShow(flag, tvShowModel);
            }
        });
    }

    @Override
    public void setFavIcon(Drawable drawable, boolean b) {
        fabFav.setImageDrawable(drawable);
        flag = b;

    }

    @Override
    public void onRefresh() {
        setPresenter();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
