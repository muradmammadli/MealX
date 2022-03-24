package com.example.foodx.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Basket {

    @PrimaryKey(autoGenerate = true)
    public int basketId;

    @ColumnInfo(name = "meal_name")
    public String mealName;

    @ColumnInfo(name = "meal_price")
    public int mealPrice;

    @ColumnInfo(name = "meal_image")
    public int mealImage;
}
