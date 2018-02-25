package com.mawaqaa.trucky.Models;

/**
 * Created by Ayadi on 2/8/2018.
 */

public class stationMenuModel {
   private int Item_id ;
   private String Item_Name ;
   private String item_Image ;
   private String item_Cusine ;
   private String item_price ;
   private int item_services ;

    public stationMenuModel(int item_id, String item_Name, String item_Image, String item_Cusine, String item_price, int item_services) {
        Item_id = item_id;
        Item_Name = item_Name;
        this.item_Image = item_Image;
        this.item_Cusine = item_Cusine;
        this.item_price = item_price;
        this.item_services = item_services;
    }

    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public String getItem_Image() {
        return item_Image;
    }

    public void setItem_Image(String item_Image) {
        this.item_Image = item_Image;
    }

    public String getItem_Cusine() {
        return item_Cusine;
    }

    public void setItem_Cusine(String item_Cusine) {
        this.item_Cusine = item_Cusine;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public int getItem_services() {
        return item_services;
    }

    public void setItem_services(int item_services) {
        this.item_services = item_services;
    }
}
