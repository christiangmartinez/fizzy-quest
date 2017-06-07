package io.xtian.fizzyquest.models;

public class Beer {
    private String mBeerId;
    private String mName;
    private String mDescription;
    private String mAbv;
    private String mIbu;

    public Beer(String beerId, String name) {
        this.mBeerId = beerId;
        this.mName = name;
    }

    public String getBeerId() {
        return mBeerId;
    }

    public String getName() {
        return mName;
    }

}
