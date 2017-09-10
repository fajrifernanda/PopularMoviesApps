package com.fernanda.fajri.popularmoviesapps.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajri on 29/08/2017.
 */

public class Movies {

    @SerializedName("results")
    private List<Movie> movies = new ArrayList<>();
    public List<Movie> getMovies(){return movies;}
}