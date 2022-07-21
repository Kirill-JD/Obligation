package com.example.obligation;

import static com.example.obligation.service.Password.passwordLength;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;

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
    private UserDB userDB;

    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etRegPassword = findViewById(R.id.etRegPassword);
        Button buttonRegistration = findViewById(R.id.buttonRegistration);

        buttonRegistration.setOnClickListener(view -> {
            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            String imei = telephonyManager.getImei();
            userDB = UserDB.getInstance(binding.getRoot().getContext());
            if (passwordLength(etRegPassword)) {
                try {
                    String imeiHash = Security.SHA1(imei);
                    if (!imeiHash.equals(userDB.userDAO().getLogin())) {
                        String passwordHash = Security.SHA1(etRegPassword.getText().toString());
                        userDB.userDAO().insert(new User(imeiHash, passwordHash));
                        Intent intent = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
                        startActivity(intent);
                    } else {
                        Snackbar.make(binding.getRoot(), "Пароль уже существует", Snackbar.LENGTH_LONG).show();
                        etRegPassword.getText().clear();
                    }

                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                Snackbar.make(binding.getRoot(), "Пароль должен содержать не меньше 6 символов", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}