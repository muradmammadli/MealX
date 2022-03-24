package com.example.foodx.SqliteDb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodx.Models.MealModel;

import java.util.ArrayList;

public class MealDao {

    public void addMeal(DatabaseHelper databaseHelper, String meal_name, int meal_price, String meal_desc, int meal_image){
        SQLiteDatabase dbx = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("meal_name",meal_name);
        values.put("meal_price",meal_price);
        values.put("meal_desc",meal_desc);
        values.put("meal_image",meal_image);

        dbx.insertOrThrow("meals",null,values);
        dbx.close();
    }

    public ArrayList<MealModel> mealModelArrayList(DatabaseHelper databaseHelper){
        ArrayList<MealModel> mealsArrayList = new ArrayList<>();
        SQLiteDatabase dbx = databaseHelper.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM meals",null);

        while (c.moveToNext()){
            MealModel mealModel = new MealModel(c.getInt(c.getColumnIndexOrThrow("meal_id")),
                    c.getString(c.getColumnIndexOrThrow("meal_name")),
                    c.getString(c.getColumnIndexOrThrow("meal_desc")),
                    c.getInt(c.getColumnIndexOrThrow("meal_price")),
                    c.getInt(c.getColumnIndexOrThrow("meal_image")));
            mealsArrayList.add(mealModel);
        }
        return mealsArrayList;
    }


}
