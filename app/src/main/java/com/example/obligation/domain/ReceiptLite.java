package com.example.obligation.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReceiptLite {
    private String city;
    private String today;
    private String fullNameBorrower;
    private String fullNameLender;
    private Double amount;
    private String amountText;
    private String day;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ReceiptLite(String city, String fullNameBorrower, String fullNameLender, Double amount, String day) {
        this.city = city;
        this.today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.fullNameBorrower = fullNameBorrower;
        this.fullNameLender = fullNameLender;
        this.amount = amount;
        this.amountText = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT).format(amount);
        this.day = day;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getToday() {
        return today;
    }

    public String getFullNameBorrower() {
        return fullNameBorrower;
    }

    public void setFullNameBorrower(String fullNameBorrower) {
        this.fullNameBorrower = fullNameBorrower;
    }

    public String getFullNameLender() {
        return fullNameLender;
    }

    public void setFullNameLender(String fullNameLender) {
        this.fullNameLender = fullNameLender;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountText() {
        return amountText;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
