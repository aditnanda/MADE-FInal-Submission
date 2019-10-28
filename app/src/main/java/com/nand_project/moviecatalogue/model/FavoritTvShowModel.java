package com.nand_project.moviecatalogue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = FavoritTvShowModel.TABLE_NAME_FAV_TVSHOW)
public class FavoritTvShowModel implements Serializable, Parcelable {

    /** The name of the Cheese table. */
    public static final String TABLE_NAME_FAV_TVSHOW = "tb_fav_tv_show";
    public static final String COLUMN_ID = "id";

    /** The name of the name column. */
    public static final String COLUMN_NAME = "name";
    public FavoritTvShowModel() {
    }

    @PrimaryKey()
    @NonNull public String id;

    @ColumnInfo(name = "vote_average")
    private String vote_average;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "first_air_date")
    private String first_air_date;

    protected FavoritTvShowModel(Parcel in) {
        id = in.readString();
        vote_average = in.readString();
        name = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        first_air_date = in.readString();
    }

    public static final Creator<FavoritTvShowModel> CREATOR = new Creator<FavoritTvShowModel>() {
        @Override
        public FavoritTvShowModel createFromParcel(Parcel in) {
            return new FavoritTvShowModel(in);
        }

        @Override
        public FavoritTvShowModel[] newArray(int size) {
            return new FavoritTvShowModel[size];
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(vote_average);
        dest.writeString(name);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(first_air_date);
    }

    public static FavoritTvShowModel fromContentValues(ContentValues values) {
        final FavoritTvShowModel cheese = new FavoritTvShowModel();
        if (values.containsKey(COLUMN_ID)) {
            cheese.id = values.getAsString(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)) {
            cheese.name = values.getAsString(COLUMN_NAME);
        }
        return cheese;
    }
}
