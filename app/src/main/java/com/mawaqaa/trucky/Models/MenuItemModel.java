package com.mawaqaa.trucky.Models;

/**
 * Created by Ayadi on 2/6/2018.
 */

public class MenuItemModel {
    private int id ;
    private String name , image , cusine ,rate;
    private boolean favorite ;

    public MenuItemModel(int id, String name, String image, String cusine, String rate, boolean favorite) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cusine = cusine;
        this.rate = rate;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCusine() {
        return cusine;
    }

    public void setCusine(String cusine) {
        this.cusine = cusine;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
