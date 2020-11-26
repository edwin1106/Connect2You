package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpMobileActivity extends AppCompatActivity {

    EditText editText;
    public static final String EXTRA_NUMBER = "com.example.connect2you";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_mobile);
        editText = (EditText) findViewById(R.id.editTextPhone2);
    }

    public void goToSignUpNameActivity(View view) {
        if (validate()){
            Intent intent = new Intent(getApplicationContext(),SignUpNameActivity.class);
            intent.putExtra(EXTRA_NUMBER, Double.parseDouble(editText.getText().toString()));
            startActivity(intent);
        }
    }

    private boolean validate()
    {
        boolean validation = true;

        String mobile = editText.getText().toString();

        if (mobile.isEmpty())
        {
            editText.setError("This field is required");
            validation = false;
        }

        return validation;
    }
}