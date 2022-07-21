package com.example.obligation.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "usr", indices = {@Index(value = {"password", "login"}, unique = true)})
public class User implements Serializable {

    @PrimaryKey
    int id = 0;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "password")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
