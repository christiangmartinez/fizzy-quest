package io.xtian.fizzyquest.models;

import org.parceler.Parcel;

@Parcel
public class Beer {
    private String beerId;
    private String name;
    private String brewery;
    private String brewsite;
    private String description;
    private String abv;
    private String ibu;
    private String pushId;
    String index;

    public Beer(String beerId, String name, String brewery, String brewsite, String description, String abv, String ibu) {
        this.beerId = beerId;
        this.name = name;
        this.brewery = brewery;
        this.brewsite = brewsite;
        this.description = description;
        this.abv = abv;
        this.ibu = ibu;
        this.index = "not_specified";
    }

    public Beer() {}

    public String getBeerId() {
        return beerId;
    }

    public String getName() {
        return name;
    }

    public String getBrewery() {
        return brewery;
    }

    public String getBrewsite() {
        return brewsite;
    }

    public String getDescription() {
        return description;
    }

    public String getAbv() {
        return abv;
    }

    public String getIbu() {
        return ibu;
    }

    public String  getPushId() { return pushId; }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
