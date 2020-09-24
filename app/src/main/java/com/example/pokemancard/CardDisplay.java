package com.example.pokemancard;

public class CardDisplay {
    private String mImgUrl;
    private String mName;
    private String mBody;
    private String mCandy;
    private String mHeight;
    private String mWeight;

   private String mWeakness;


    public String getmImgUrl() {
        return mImgUrl;
    }

    public String getmName() {
        return mName;
    }

    public String getmBody() {
        return mBody;
    }
    public String getmCandy() {
        return mCandy;
    }

    public String getmHeight() {
        return mHeight;
    }

    public String getmWeight() {
        return mWeight;
    }
//

//
    public String getmWeakness() {
        return mWeakness;
    }

    public CardDisplay(String mImgUrl, String mName, String mBody, String mCandy,String mWeakness,String mHeight, String mWeight) {
        this.mImgUrl = mImgUrl;
        this.mName = mName;
        this.mBody = mBody;
        this.mCandy = mCandy;
        this.mHeight = mHeight;
        this.mWeight = mWeight;
//
       this.mWeakness = mWeakness;
    }
}
