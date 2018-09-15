package com.developer.annant.gopaltyres.Extras_imp;

/**
 * Created by Annant on 05-01-2017.
 */

public class TyreData {
    private String mTreadType , mTyreSize, mPrice ;
    private int mResourceImageView ;

    public TyreData(String TreadType , String TyreSize, String Price , int ResourceImageView ){
        mTreadType = TreadType;
        mTyreSize = TyreSize;
        mPrice = Price ;
        mResourceImageView = ResourceImageView;
    }

    public TyreData(String TreadType , String TyreSize, String Price ){
        mTreadType = TreadType;
        mTyreSize = TyreSize;
        mPrice = Price ;
        //mResourceImageView = ResourceImageView;
    }

    public String getTreadType() {
        return mTreadType;
    }

    public String getTyreSize() {
        return mTyreSize;
    }

    public String getPrice() {
        return mPrice;
    }

    public int getResourceImageView() {
        return mResourceImageView;
    }
}
