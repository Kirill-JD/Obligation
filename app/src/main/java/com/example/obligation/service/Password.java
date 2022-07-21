package com.example.obligation.service;

import android.content.Context;
import android.widget.EditText;

import com.example.obligation.dataBase.userDAO.UserDB;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Password {

    private static final int MIN_PASSWORD_LENGTH = 6;

    public static boolean comparison (EditText et, Context context) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return Security.SHA1(et.getText().toString()).equals(UserDB.getInstance(context).userDAO().getPassword());
    }

    public static boolean passwordLength (EditText et) {
        return et.getText().toString().length() >= MIN_PASSWORD_LENGTH;
    }
}
