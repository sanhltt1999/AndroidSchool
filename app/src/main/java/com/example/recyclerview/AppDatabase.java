package com.example.recyclerview;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static AppDatabase instance;

    public static AppDatabase getInstance (Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "Test").build();
        }
        return instance;
    }

}
