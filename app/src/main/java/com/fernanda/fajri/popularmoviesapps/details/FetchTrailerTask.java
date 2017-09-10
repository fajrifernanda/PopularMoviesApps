package com.fernanda.fajri.popularmoviesapps.details;

/**
 * Created by Fajri on 01/09/2017.
 */
import android.os.AsyncTask;
import android.util.Log;

import com.fernanda.fajri.popularmoviesapps.BuildConfig;
import com.fernanda.fajri.popularmoviesapps.API.MovieDatabaseService;
import com.fernanda.fajri.popularmoviesapps.API.Trailer;
import com.fernanda.fajri.popularmoviesapps.API.Trailers;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class FetchTrailerTask extends AsyncTask<Long,Void,List<Trailer>> {

    public static String LOG_TAG=FetchReviewsTask.class.getName();
    private final Listener mListener;

    public interface Listener{
        void onFetchFinished(List<Trailer> trailers);
    }

    public FetchTrailerTask(Listener listener){mListener=listener;}

    @Override
    protected List<Trailer> doInBackground(Long... params) {
        if (params.length==0){
            return null;
        }
        long movieId=params[0];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDatabaseService service = retrofit.create(MovieDatabaseService.class);
        Call<Trailers> call = service.findTrailersById(movieId, "90e8d5a665eaa955b4d8864b2ab7ccf2");

        try{
            Response<Trailers> response = call.execute();
            Trailers trailers = response.body();
            return  trailers.getTrailers();
        }catch (IOException e){
            Log.e(LOG_TAG,"A problem occured taking to the movie db"+e);
        }
        return null;
    }

}
