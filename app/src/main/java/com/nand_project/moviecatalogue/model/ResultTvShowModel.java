package com.nand_project.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultTvShowModel implements Parcelable {
    private String vote_count;
    private String id;
    private String vote_average;
    private String name;
    private String popularity;
    private String poster_path;
    private String original_name;
    private String backdrop_path;
    private String overview;
    private String first_air_date;

    public ResultTvShowModel() {
    }

    protected ResultTvShowModel(Parcel in) {
        vote_count = in.readString();
        id = in.readString();
        vote_average = in.readString();
        name = in.readString();
        popularity = in.readString();
        poster_path = in.readString();
        original_name = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        first_air_date = in.readString();
    }

    public static final Creator<ResultTvShowModel> CREATOR = new Creator<ResultTvShowModel>() {
        @Override
        public ResultTvShowModel createFromParcel(Parcel in) {
            return new ResultTvShowModel(in);
        }

        @Override
        public ResultTvShowModel[] newArray(int size) {
            return new ResultTvShowModel[size];
        }
    };

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
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
        dest.writeString(vote_count);
        dest.writeString(id);
        dest.writeString(vote_average);
        dest.writeString(name);
        dest.writeString(popularity);
        dest.writeString(poster_path);
        dest.writeString(original_name);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(first_air_date);
    }
}
