package com.example.foodx.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.foodx.Models.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract AuthenticationDao getUserDao();

}
