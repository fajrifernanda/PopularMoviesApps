package com.fernanda.fajri.popularmoviesapps.API;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajri on 29/08/2017.
 */

public class Trailer implements Parcelable {

    public static final String LOG_TAG = Trailer.class.getSimpleName();

    @SerializedName("id")
    private String mId;
    @SerializedName("key")
    private String mKey;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("size")
    private String mSize;

    private Trailer(){}

    public String getName(){return mName;}

    public String getKey(){return mKey;}

    public String getTrailerUrl(){return "http://www.youtube.com/watch?v=" +mKey;}

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            Trailer trailer = new Trailer();
            trailer.mId = in.readString();
            trailer.mKey=in.readString();
            trailer.mSite=in.readString();
            trailer.mSize= in.readString();
            trailer.mName=in.readString();
            return trailer;
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mKey);
        dest.writeString(mName);
        dest.writeString(mSite);
        dest.writeString(mSize);
    }
}