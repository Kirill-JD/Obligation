package com.example.obligation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.dataBase.userDAO.UserDB;
import com.example.obligation.databinding.ActivityRegistrationBinding;
import com.example.obligation.domain.User;
import com.example.obligation.service.Security;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etRegPassword;
    private Button buttonRegistration;
    private final int MIN_PASSWORD_LENGTH = 6;
    private UserDB userDB;

    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etRegPassword = (EditText) findViewById(R.id.etRegPassword);
        buttonRegistration = (Button) findViewById(R.id.buttonRegistration);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String imei = telephonyManager.getImei();
                userDB = UserDB.getInstance(binding.getRoot().getContext());
                if (etRegPassword.getText().toString().length() >= MIN_PASSWORD_LENGTH) {
                    try {
                        String imeiHash = Security.SHA1(imei);
                        if (!imeiHash.equals(userDB.userDAO().getLogin())) {
                            String passwordHash = Security.SHA1(etRegPassword.getText().toString());
                            userDB.userDAO().insert(new User(imeiHash, passwordHash));
                            Intent intent = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(binding.getRoot(), "Пароль уже существует", Snackbar.LENGTH_LONG).show();
                            etRegPassword.clearComposingText();
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), "Пароль должен содержать не меньше 6 символов", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}