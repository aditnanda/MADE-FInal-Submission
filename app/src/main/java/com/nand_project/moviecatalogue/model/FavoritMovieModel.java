package com.nand_project.moviecatalogue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = FavoritMovieModel.TABLE_NAME_FAV_MOVIE)
public class FavoritMovieModel implements Serializable, Parcelable {

    /** The name of the Cheese table. */
    public static final String TABLE_NAME_FAV_MOVIE = "tb_fav_movie";
    public static final String COLUMN_ID = "id";

    /** The name of the name column. */
    public static final String COLUMN_NAME = "title";

    public FavoritMovieModel() {
    }

    @PrimaryKey()
    @NonNull public String id;

    @ColumnInfo(name = "vote_average")
    private String vote_average;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "release_date")
    private String release_date;

    protected FavoritMovieModel(Parcel in) {
        id = in.readString();
        vote_average = in.readString();
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<FavoritMovieModel> CREATOR = new Creator<FavoritMovieModel>() {
        @Override
        public FavoritMovieModel createFromParcel(Parcel in) {
            return new FavoritMovieModel(in);
        }

        @Override
        public FavoritMovieModel[] newArray(int size) {
            return new FavoritMovieModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(vote_average);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(release_date);
    }

    public static FavoritMovieModel fromContentValues(ContentValues values) {
        final FavoritMovieModel cheese = new FavoritMovieModel();
        if (values.containsKey(COLUMN_ID)) {
            cheese.id = values.getAsString(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)) {
            cheese.title = values.getAsString(COLUMN_NAME);
        }
        return cheese;
    }
}
