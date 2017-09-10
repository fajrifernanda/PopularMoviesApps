package com.fernanda.fajri.popularmoviesapps.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajri on 29/08/2017.
 */

public class Reviews {
    @SerializedName("results")
    private List<Review> reviews = new ArrayList<>();

    public List<Review> getReviews(){return reviews;}
}
