package com.nand_project.moviecatalogue.api;

import com.nand_project.moviecatalogue.BuildConfig;

public class ApiURL {

    public static final String
        BASE_URL            = "https://api.themoviedb.org/3/",
        API_KEY             = BuildConfig.TMDB_API_KEY,

        MOVIE_URL           = "discover/movie?api_key="+API_KEY+"&language=",
        TVSHOW_URL          = "discover/tv?api_key="+API_KEY+"&language=",
        SEARCH_MOVIE_URL    = "search/movie?api_key="+API_KEY+"&language=",
        SEARCH_TVSHOW_URL   = "search/tv?api_key="+API_KEY+"&language=",
        MOVIE_NOW_URL       = "discover/movie?api_key="+API_KEY+"&primary_release_date.gte=",
        POSTER_URL          = "https://image.tmdb.org/t/p/";


}
