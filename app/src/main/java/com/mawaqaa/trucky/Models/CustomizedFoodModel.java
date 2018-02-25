package com.mawaqaa.trucky.Models;

/**
 * Created by Ayadi on 2/15/2018.
 */

public class CustomizedFoodModel {
    private int idFood ;
    private String nameFood ;

    public CustomizedFoodModel(int idFood, String nameFood) {
        this.idFood = idFood;
        this.nameFood = nameFood;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }
}
