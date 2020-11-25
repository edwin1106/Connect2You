package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_name);
    }

    public void goToSignUpEmailActivity(View view) {
        Intent intent = new Intent(getApplicationContext(),SignUpEmailActivity.class);
        startActivity(intent);

    }
}