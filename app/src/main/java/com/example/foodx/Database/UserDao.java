package com.example.foodx.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM basket")
    List<Basket> getAllBasket();

    @Insert
    void insertBasket(Basket...baskets);

    @Delete
    void deleteBasket(Basket basket);

}
