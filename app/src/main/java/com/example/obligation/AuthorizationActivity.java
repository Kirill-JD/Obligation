package com.example.obligation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.databinding.ActivityAuthorizationBinding;

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
                Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                startActivity(intent);
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
}
