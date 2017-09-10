package com.fernanda.fajri.popularmoviesapps.API;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Review implements Parcelable {

    @SerializedName("id")
    private String mId;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("content")
    private String mContent;
    @SerializedName("url")
    private String mUrl;


    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            Review review = new Review();

            review.mId=in.readString();
            review.mAuthor=in.readString();
            review.mContent=in.readString();
            review.mUrl=in.readString();
            return review;
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getContent(){return mContent;}

    public String getAuthor(){return mAuthor;}

    public String getUrl(){return mUrl;}



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mAuthor);
        dest.writeString(mContent);
        dest.writeString(mUrl);
    }
}

