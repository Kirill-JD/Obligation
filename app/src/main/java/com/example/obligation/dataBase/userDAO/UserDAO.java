package com.example.obligation.dataBase.userDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.obligation.domain.User;

@Dao
public interface UserDAO {

    @Insert
    void insert (User user);

    @Query("UPDATE usr SET password = :password WHERE login = :login")
    void update (String login, String password);

    @Query("SELECT login FROM usr")
    String getLogin();

    @Query("SELECT password FROM usr")
    String getPassword();
}
