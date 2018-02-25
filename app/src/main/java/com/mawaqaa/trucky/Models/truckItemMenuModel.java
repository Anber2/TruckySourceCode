package com.mawaqaa.trucky.Models;

/**
 * Created by Ayadi on 2/14/2018.
 */

public class truckItemMenuModel {
    private int id ;
    private String name , image , desc ,price , time_t_finish;

    public truckItemMenuModel(int id, String name, String image, String desc, String price, String time_t_finish) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.price = price;
        this.time_t_finish = time_t_finish;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime_t_finish() {
        return time_t_finish;
    }

    public void setTime_t_finish(String time_t_finish) {
        this.time_t_finish = time_t_finish;
    }
}
