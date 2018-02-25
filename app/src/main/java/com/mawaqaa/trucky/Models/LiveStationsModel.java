package com.mawaqaa.trucky.Models;

/**
 * Created by HP on 2/19/2018.
 */

public class LiveStationsModel {
   private String id;
    private String name;
    private double longt;
    private double lat;
    private String image;
    private String availability;
    private String rating;
    private String cusin;
    private String isFav;
    private int position;

    public LiveStationsModel(String id, String name, double longt, double lat, String image, String availability, String rating, String cusin, String isFav, int position) {
        this.setId(id);
        this.setName(name);
        this.setLongt(longt);
        this.setLat(lat);
        this.setImage(image);
        this.setAvailability(availability);
        this.setRating(rating);
        this.setCusin(cusin);
        this.setIsFav(isFav);
        this.setPosition(position);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCusin() {
        return cusin;
    }

    public void setCusin(String cusin) {
        this.cusin = cusin;
    }

    public String getIsFav() {
        return isFav;
    }

    public void setIsFav(String isFav) {
        this.isFav = isFav;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
