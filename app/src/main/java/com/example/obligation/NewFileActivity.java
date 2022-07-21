package com.example.obligation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligation.databinding.ActivityNewFileBinding;
import com.example.obligation.domain.ReceiptLite;
import com.example.obligation.domain.Word;
import com.example.obligation.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewFileActivity extends AppCompatActivity {
    private FileService fileService;
    private SharedPreferences sharedPreferences;

    private EditText etCity;
    private EditText etFullNameBorrower;
    private EditText etFullNameLender;
    private EditText etAmount;
    private EditText etDay;
    private Word word;
    private ActivityNewFileBinding binding;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etCity = findViewById(R.id.etCity);
        etFullNameBorrower = findViewById(R.id.etFullNameBorrower);
        etFullNameLender = findViewById(R.id.etFullNameLender);
        etAmount = findViewById(R.id.etAmount);
        etDay = findViewById(R.id.etDay);

        Button newButton = findViewById(R.id.newButton);

        etDay.setOnClickListener(view -> {
            showDateDialog();
        });

        newButton.setOnClickListener(view -> {
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
        });
    }

    private void showDateDialog() {
        calendar = Calendar.getInstance();

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

                etDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(NewFileActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}