package com.mawaqaa.trucky.Models;

/**
 * Created by Ayadi on 2/11/2018.
 */

public class MyOrderModel {
    private String id , restaurent , order_status , ratio , item_name , time_to_ready , date , time ,payment_type , price ;

    public MyOrderModel(String id, String restaurent, String order_status, String ratio, String item_name, String time_to_ready, String date, String time, String payment_type, String price) {
        this.id = id;
        this.restaurent = restaurent;
        this.order_status = order_status;
        this.ratio = ratio;
        this.item_name = item_name;
        this.time_to_ready = time_to_ready;
        this.date = date;
        this.time = time;
        this.payment_type = payment_type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurent() {
        return restaurent;
    }

    public void setRestaurent(String restaurent) {
        this.restaurent = restaurent;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getTime_to_ready() {
        return time_to_ready;
    }

    public void setTime_to_ready(String time_to_ready) {
        this.time_to_ready = time_to_ready;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}