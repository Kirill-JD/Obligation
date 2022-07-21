package com.example.obligation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.dataBase.userDAO.UserDB;
import com.example.obligation.databinding.ActivityAuthorizationBinding;
import com.example.obligation.service.Security;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class AuthorizationActivity extends AppCompatActivity {

    private EditText etInPassword;
    private TextView tvCreatePassword;
    private Button buttonAuthorization;

    private ActivityAuthorizationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etInPassword = (EditText) findViewById(R.id.etInPassword);
        tvCreatePassword = (TextView) findViewById(R.id.tvCreatePassword);
        buttonAuthorization = (Button) findViewById(R.id.buttonAuthorization);

        buttonAuthorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Security.SHA1(etInPassword.getText().toString()).equals(UserDB.getInstance(binding.getRoot().getContext()).userDAO().getPassword())) {
                        Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        if (etInPassword.getText().toString().isEmpty()) {
                            Snackbar.make(binding.getRoot(), "Введите пароль", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(binding.getRoot(), "Не вырный пароль", Snackbar.LENGTH_LONG).show();
                            etInPassword.getText().clear();
                        }
                        return;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        tvCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}
