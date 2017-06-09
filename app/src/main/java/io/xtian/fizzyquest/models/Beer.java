package io.xtian.fizzyquest.models;

public class Beer {
    private String mBeerId;
    private String mName;
    private String mBrewery;
    private String mBrewsite;

    private String mDescription;
    private String mAbv;
    private String mIbu;
    public Beer(String beerId, String name, String brewery, String brewsite, String description, String abv, String ibu) {
        this.mBeerId = beerId;
        this.mName = name;
        this.mBrewery = brewery;
        this.mBrewsite = brewsite;
        this.mDescription = description;
        this.mAbv = abv;
        this.mIbu = ibu;
    }

    public String getBeerId() {
        return mBeerId;
    }

    public String getName() {
        return mName;
    }

    public String getBrewery() {return mBrewery;}

    public String getBrewsite() {
        return mBrewsite;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAbv() {
        return mAbv;
    }

    public String getIbu() {
        return mIbu;
    }

}
