package com.fernanda.fajri.popularmoviesapps.details;

/**
 * Created by Fajri on 01/09/2017.
 */

        import android.os.AsyncTask;
        import android.util.Log;

        import com.fernanda.fajri.popularmoviesapps.BuildConfig;
        import com.fernanda.fajri.popularmoviesapps.API.MovieDatabaseService;
        import com.fernanda.fajri.popularmoviesapps.API.Review;
        import com.fernanda.fajri.popularmoviesapps.API.Reviews;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andri Ginting on 7/4/2017.
 */

public class FetchReviewsTask extends AsyncTask<Long,Void,List<Review>>{

    public static String LOG_TAG =FetchReviewsTask.class.getSimpleName();
    private final Listener mListener;

    public interface Listener {
        void onReviewsFetchFinished(List<Review> reviews);
    }
    public FetchReviewsTask(Listener listener){
        mListener=listener;
    }

    @Override
    protected List<Review> doInBackground(Long... params) {
        if (params.length==0){
            return null;
        }
        long movieId = params[0];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDatabaseService service = retrofit.create(MovieDatabaseService.class);
        Call<Reviews> call = service.findReviewsById(movieId,"90e8d5a665eaa955b4d8864b2ab7ccf2");

        try{
            Response<Reviews> response = call.execute();
            Reviews reviews = response.body();
            return reviews.getReviews();
        } catch (IOException e) {
            Log.e(LOG_TAG,"A problem occured taking to the movie db"+e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Review> reviews) {
        if (reviews!=null){
            mListener.onReviewsFetchFinished(reviews);
        }else{
            mListener.onReviewsFetchFinished(new ArrayList<Review>());
        }
    }
}
