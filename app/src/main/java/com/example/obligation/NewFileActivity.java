package com.example.obligation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.obligation.databinding.ActivityNewFileBinding;
import com.example.obligation.domain.ReceiptLite;
import com.example.obligation.domain.Word;
import com.example.obligation.service.FileService;

import java.io.IOException;
import java.io.InputStream;

public class NewFileActivity extends AppCompatActivity {
    private FileService fileService;
    private SharedPreferences sharedPreferences;

    private EditText etCity;
    private EditText etFullNameBorrower;
    private EditText etFullNameLender;
    private EditText etAmount;
    private EditText etDay;
    private Button newButton;
    private Word word;

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewFileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etCity = (EditText) findViewById(R.id.etCity);
        etFullNameBorrower = (EditText) findViewById(R.id.etFullNameBorrower);
        etFullNameLender = (EditText) findViewById(R.id.etFullNameLender);
        etAmount = (EditText) findViewById(R.id.etAmount);
        etDay = (EditText) findViewById(R.id.etDay);

        newButton = (Button) findViewById(R.id.newButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                ReceiptLite receiptLite = new ReceiptLite(etCity.getText().toString(),
                        etFullNameBorrower.getText().toString(),
                        etFullNameLender.getText().toString(),
                        Double.valueOf(etAmount.getText().toString()),
                        etDay.getText().toString());
                sharedPreferences = getPreferences(MODE_PRIVATE);
                fileService = new FileService();
                try {
                    InputStream inputStream = getAssets().open("raspiska.doc");
                    word = fileService.renderWord(receiptLite, inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent();
                    intent.putExtra("word", word);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}