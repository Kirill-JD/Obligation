package com.example.obligation;

import static com.example.obligation.service.Password.comparison;
import static com.example.obligation.service.Password.passwordLength;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.dataBase.userDAO.UserDB;
import com.example.obligation.databinding.ActivityChangePasswordBinding;
import com.example.obligation.service.Security;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText etCurrentPassword;
    private EditText etNewPassword;
    private EditText etRepeatNewPassword;
    private UserDB userDB;

    private ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etCurrentPassword = findViewById(R.id.etCurrentPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etRepeatNewPassword = findViewById(R.id.etRepeatNewPassword);
        Button buttonChangePassword = findViewById(R.id.buttonChangePassword);

        buttonChangePassword.setOnClickListener(view -> {
            userDB = UserDB.getInstance(binding.getRoot().getContext());
            try {
                if (comparison(etCurrentPassword, binding.getRoot().getContext())) {
                    if (passwordLength(etNewPassword)) {
                        if (etNewPassword.getText().toString().equals(etRepeatNewPassword.getText().toString())) {
                            userDB.userDAO().update(userDB.userDAO().getLogin(), Security.SHA1(etNewPassword.getText().toString()));
                            etCurrentPassword.getText().clear();
                            etNewPassword.getText().clear();
                            etRepeatNewPassword.getText().clear();
                            Snackbar.make(binding.getRoot(), "Пароль успешно изменён", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(binding.getRoot(), "Пароли не совпадают", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(binding.getRoot(), "Пароль должен содержать не меньше 6 символов", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), "Неверный текущий пароль", Snackbar.LENGTH_LONG).show();
                }
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });
    }
}
