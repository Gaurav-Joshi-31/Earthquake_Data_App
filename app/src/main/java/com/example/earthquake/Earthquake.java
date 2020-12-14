package com.example.earthquake;

public class Earthquake {
    private double mMagnitude;
    private String mcity;
    private long mTimeInMilliseconds;
    private String mUrl;
public Earthquake(double magnitude,String city,long time, String url){
    mMagnitude=magnitude;
    mcity=city;
    mTimeInMilliseconds=time;
    mUrl=url;
}
public double getMagnitude(){
    return mMagnitude;
}
    public String getCity(){
        return mcity;
    }
    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }
    public String getUrl(){return mUrl;}

}
