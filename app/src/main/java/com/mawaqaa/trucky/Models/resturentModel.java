package com.mawaqaa.trucky.Models;

/**
 * Created by Ayadi on 2/6/2018.
 */

public class resturentModel {
    private int ID;
    private String name;
    private String rest_img ;
    private String cusine_type;
    private String rest_rate;
    private int truke;
    private int live_station;
    private boolean is_favorite;

    public resturentModel(int ID, String name ,String rest_img, String cusine_type, String rest_rate, int truke, int live_station, boolean is_favorite) {
        this.ID = ID;
        this.name = name;
        this.cusine_type = cusine_type;
        this.rest_rate = rest_rate;
        this.truke = truke;
        this.live_station = live_station;
        this.is_favorite = is_favorite;
        this.rest_img = rest_img ;

    }

    public String getRest_img() {
        return rest_img;
    }

    public void setRest_img(String rest_img) {
        this.rest_img = rest_img;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCusine_type() {
        return cusine_type;
    }

    public void setCusine_type(String cusine_type) {
        this.cusine_type = cusine_type;
    }

    public String getRest_rate() {
        return rest_rate;
    }

    public void setRest_rate(String rest_rate) {
        this.rest_rate = rest_rate;
    }

    public int getTruke() {
        return truke;
    }

    public void setTruke(int truke) {
        this.truke = truke;
    }

    public int getLive_station() {
        return live_station;
    }

    public void setLive_station(int live_station) {
        this.live_station = live_station;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }
}
