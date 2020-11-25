package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AccountCreatedActivity extends AppCompatActivity {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_created);
        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.aqua);
    }
}