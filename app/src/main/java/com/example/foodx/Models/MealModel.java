package com.example.foodx.Models;

public class MealModel {
    private int meal_id;
    private String meal_name;
    private String meal_desc;
    private int meal_price;
    private int meal_image;

    public MealModel(int meal_id, String meal_name, String meal_desc, int meal_price, int meal_image) {
        this.meal_id = meal_id;
        this.meal_name = meal_name;
        this.meal_desc = meal_desc;
        this.meal_price = meal_price;
        this.meal_image = meal_image;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getMeal_desc() {
        return meal_desc;
    }

    public void setMeal_desc(String meal_desc) {
        this.meal_desc = meal_desc;
    }

    public int getMeal_price() {
        return meal_price;
    }

    public void setMeal_price(int meal_price) {
        this.meal_price = meal_price;
    }

    public int getMeal_image() {
        return meal_image;
    }

    public void setMeal_image(int meal_image) {
        this.meal_image = meal_image;
    }
}
