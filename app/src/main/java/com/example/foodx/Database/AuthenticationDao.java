package com.example.foodx.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodx.Models.User;

@Dao
public interface AuthenticationDao {

    @Query("SELECT * FROM user WHERE email =:email and password =:password ")
    User getUser(String email,String password);

    @Insert
    void insert(User user);
}
