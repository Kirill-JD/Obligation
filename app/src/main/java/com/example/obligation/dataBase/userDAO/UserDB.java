package com.example.obligation.dataBase.userDAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.obligation.domain.User;

@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {

    private static UserDB database;
    private static final String DATABASE_NAME = "UserApp";

    public synchronized static UserDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    UserDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract UserDAO userDAO();
}
