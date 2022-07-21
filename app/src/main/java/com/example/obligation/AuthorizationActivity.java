package com.example.obligation;

import static com.example.obligation.service.Password.comparison;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.databinding.ActivityAuthorizationBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class AuthorizationActivity extends AppCompatActivity {

    private EditText etInPassword;

    private ActivityAuthorizationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etInPassword = findViewById(R.id.etInPassword);
        TextView tvCreatePassword = findViewById(R.id.tvCreatePassword);
        Button buttonAuthorization = findViewById(R.id.buttonAuthorization);

        buttonAuthorization.setOnClickListener(view -> {
            try {
                if (comparison(etInPassword, binding.getRoot().getContext())) {
                    Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (etInPassword.getText().toString().isEmpty()) {
                        Snackbar.make(binding.getRoot(), "Введите пароль", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(binding.getRoot(), "Не вырный пароль", Snackbar.LENGTH_LONG).show();
                        etInPassword.getText().clear();
                    }
                }
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });

        tvCreatePassword.setOnClickListener(view -> {
            Intent intent = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public void onBackPressed() {
    }
}
