package com.fernanda.fajri.popularmoviesapps.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Fajri on 29/08/2017.
 */

public interface MovieDatabaseService {
    @GET("3/movie/{sort_by}")
    Call<Movies> discoverMovies(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Call<Trailers> findTrailersById(@Path("id") long movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Call<Reviews> findReviewsById(@Path("id") long movieId, @Query("api_key") String apiKey);
}
