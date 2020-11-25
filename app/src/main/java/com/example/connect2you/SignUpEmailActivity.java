package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
    }

    public void goToSignUpPasswordActivity(View view) {
        Intent intent = new Intent(getApplicationContext(),SignUpPasswordActivity.class);
        startActivity(intent);
    }
}