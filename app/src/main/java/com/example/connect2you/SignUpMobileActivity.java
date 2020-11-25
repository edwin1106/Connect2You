package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpMobileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_mobile);
    }

    public void goToSignUpNameActivity(View view) {
        Intent intent = new Intent(getApplicationContext(),SignUpNameActivity.class);
        startActivity(intent);
    }
}