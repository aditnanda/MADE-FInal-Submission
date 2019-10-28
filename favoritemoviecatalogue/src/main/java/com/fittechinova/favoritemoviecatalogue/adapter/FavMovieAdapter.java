package com.fittechinova.favoritemoviecatalogue.adapter;

import android.content.Context;
import android.database.Cursor;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fittechinova.favoritemoviecatalogue.R;

import static com.fittechinova.favoritemoviecatalogue.ApiURL.POSTER_URL;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.ViewHolder> {
    private Cursor mCursor;
    private Context context;

    public FavMovieAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtDescription, txtTglRilis,txtVoteAverage;
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

    public void setFavMovie(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder,final  int i) {
        if (mCursor.moveToPosition(i)) {
            viewHolder.txtName.setText(mCursor.getString(
                    mCursor.getColumnIndexOrThrow("title")));
            viewHolder.txtDescription.setText(mCursor.getString(
                    mCursor.getColumnIndexOrThrow("overview")));
            viewHolder.txtTglRilis.setText(mCursor.getString(
                    mCursor.getColumnIndexOrThrow("release_date")));
            Glide.with(context)
                    .load(POSTER_URL+"w185"+mCursor.getString(
                            mCursor.getColumnIndexOrThrow("poster_path")))
                    .placeholder(R.drawable.default_image)
                    .into(viewHolder.imgPhoto);
            viewHolder.txtVoteAverage.setText(mCursor.getString(
                    mCursor.getColumnIndexOrThrow("vote_average"))+"/10");

        }
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }
}
