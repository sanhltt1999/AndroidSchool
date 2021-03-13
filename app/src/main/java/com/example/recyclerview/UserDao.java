package com.example.recyclerview;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("select * from user")
    public List<User> getAll();

    @Insert
    public void insert(User... users);

}
