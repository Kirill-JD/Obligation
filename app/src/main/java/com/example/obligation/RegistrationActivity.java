package com.example.obligation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etRegPassword;
    private Button buttonRegistration;

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
                Intent intent = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
                startActivity(intent);
            }
        });
    }
}