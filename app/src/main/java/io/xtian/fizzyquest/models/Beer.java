package io.xtian.fizzyquest.models;

public class Beer {
    private String mBeerId;
    private String mName;
    private String mDescription;
    private String mAbv;
    private String mIbu;

    public Beer(String beerId, String name, String description, String abv, String ibu) {
        this.mBeerId = beerId;
        this.mName = name;
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
