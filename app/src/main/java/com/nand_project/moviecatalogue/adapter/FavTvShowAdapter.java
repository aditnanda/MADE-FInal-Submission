package com.nand_project.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nand_project.moviecatalogue.R;
import com.nand_project.moviecatalogue.activity.detail.DetailActivity;
import com.nand_project.moviecatalogue.model.FavoritTvShowModel;
import com.nand_project.moviecatalogue.model.ResultTvShowModel;

import java.util.ArrayList;

import static com.nand_project.moviecatalogue.api.ApiURL.POSTER_URL;

public class FavTvShowAdapter extends RecyclerView.Adapter<FavTvShowAdapter.ViewHolder> {
    ArrayList<FavoritTvShowModel> rvData;
    Context context;

    public FavTvShowAdapter(Context mContext, ArrayList<FavoritTvShowModel> inputan) {
        this.rvData = inputan;
        this.context = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtDescription, txtTglRilis, txtVoteAverage;
        private ImageView imgPhoto;
        private CardView cvFilm;

        public ViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            txtTglRilis = view.findViewById(R.id.txt_tgl_rilis);
            imgPhoto = view.findViewById(R.id.img_photo);
            cvFilm = view.findViewById(R.id.cv_film);
            txtVoteAverage = view.findViewById(R.id.tv_vote_average);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup,final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_movie_list,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder,final  int i) {
        viewHolder.txtName.setText(rvData.get(i).getName());
        viewHolder.txtDescription.setText(rvData.get(i).getOverview());
        viewHolder.txtTglRilis.setText(rvData.get(i).getFirst_air_date());
        Glide.with(context)
                .load(POSTER_URL+"w185"+rvData.get(i).getPoster_path())
                .placeholder(R.drawable.default_image)
                .into(viewHolder.imgPhoto);

        viewHolder.txtVoteAverage.setText(rvData.get(i).getVote_average()+"/10");

        viewHolder.cvFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultTvShowModel tvShowModel = new ResultTvShowModel();
                tvShowModel.setName(rvData.get(i).getName());
                tvShowModel.setFirst_air_date(rvData.get(i).getFirst_air_date());
                tvShowModel.setOverview(rvData.get(i).getOverview());
                tvShowModel.setPoster_path(rvData.get(i).getPoster_path());
                tvShowModel.setBackdrop_path(rvData.get(i).getBackdrop_path());
                tvShowModel.setVote_average(rvData.get(i).getVote_average());
                tvShowModel.setId(rvData.get(i).getId());

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movie_model", tvShowModel);
                intent.putExtra("mode", "tv_show");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }
}
