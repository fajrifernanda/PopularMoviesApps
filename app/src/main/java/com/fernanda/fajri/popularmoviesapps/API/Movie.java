package com.fernanda.fajri.popularmoviesapps.API;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fernanda.fajri.popularmoviesapps.R;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Movie implements Parcelable{
    public static final String LOG_TAG=Movie.class.getSimpleName();
    public static final float POSTER_ASPECT_RATIO=1.5f;

    @SerializedName("id")
    private long mId;
    @SerializedName("original_title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mPoster;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("vote_average")
    private String mUserRating;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("backdrop_path")
    private String mBackdrop;

    //hanya berlaku untuk createFromParcel
    public Movie(){
    }

    public Movie(long mId, String mTitle, String mPoster, String mOverview, String mUserRating, String mReleaseDate, String mBackdrop) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mPoster = mPoster;
        this.mOverview = mOverview;
        this.mUserRating = mUserRating;
        this.mReleaseDate = mReleaseDate;
        this.mBackdrop = mBackdrop;
    }

    protected Movie(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mPoster = in.readString();
        mOverview = in.readString();
        mUserRating = in.readString();
        mReleaseDate = in.readString();
        mBackdrop = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            Movie movie = new Movie();
            movie.mId = source.readLong();
            movie.mTitle = source.readString();
            movie.mPoster = source.readString();
            movie.mOverview = source.readString();
            movie.mUserRating = source.readString();
            movie.mReleaseDate = source.readString();
            movie.mBackdrop = source.readString();
            return movie;
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    public long getId(){return mId;}

    public String getPosterUrl(Context context){
        if (mPoster!=null && !mPoster.isEmpty()){
            return  context.getResources().getString(R.string.url_for_downloading_poster)+mPoster;
        }
        return null;
    }

    public String getPoster(){return mPoster;}

    public String getmReleaseDate(Context context){
        String inputPattern ="yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        if (mReleaseDate !=null && !mReleaseDate.isEmpty()){
            try {
                Date date = inputFormat.parse(mReleaseDate);
                return DateFormat.getDateInstance().format(date);
            } catch (ParseException e) {
                Log.e(LOG_TAG,"Data tidak berhasil "+mReleaseDate);
            }
        }else{
            mPoster=context.getString(R.string.release_date_missing);
        }
        return mReleaseDate;
    }

    public String getmReleaseDate(){return mReleaseDate;}

    @Nullable
    public String getmOverview(){return mOverview;}

    @Nullable
    public String getmUserRating(){return  mUserRating;}

    @Nullable
    public String getmBackdropUrl(Context context){
        if (mBackdrop!=null && !mBackdrop.isEmpty()){
            return context.getResources().getString(R.string.url_for_downloading_poster)+mBackdrop;
        }return null;
    }

    public String getmBackdrop(){return mBackdrop;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mPoster);
        dest.writeString(mOverview);
        dest.writeString(mUserRating);
        dest.writeString(mReleaseDate);
        dest.writeString(mBackdrop);
    }
}

