package com.example.android.quakereport;

/**
 * Created by PCEH on 12-03-2017.
 */

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliSeconds;
    private String mUrl;


    public Earthquake(double Magnitude, String Location, long TimeInMilliseconds , String  Url)
    {
        mMagnitude = Magnitude;
        mLocation = Location;
        mTimeInMilliSeconds = TimeInMilliseconds;
        mUrl = Url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }


    public long getTimeInMilliSeconds() {
        return mTimeInMilliSeconds;
    }

    public String getUrl(){
        return mUrl;
    }

}
