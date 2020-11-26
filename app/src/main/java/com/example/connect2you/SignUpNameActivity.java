package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpNameActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextLastName;
    public static final String EXTRA_NUMBER = "com.example.connect2you";
    public double phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_name);

        Intent intent = getIntent();
        phone = intent.getDoubleExtra(SignUpMobileActivity.EXTRA_NUMBER, 0);
        editTextName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextLastName = (EditText) findViewById(R.id.editTextTextPersonName2);
    }

    public void goToSignUpEmailActivity(View view) {
        if (validate()) {
            String name = editTextName.getText().toString();
            String lastname = editTextLastName.getText().toString();
            Intent intent = new Intent(getApplicationContext(),SignUpEmailActivity.class);
            intent.putExtra("Name", name);
            intent.putExtra("Lastname", lastname);
            intent.putExtra(EXTRA_NUMBER, phone);
            startActivity(intent);
        }
    }

    private boolean validate()
    {
        boolean validation = true;

        String name = editTextName.getText().toString();
        String lastname = editTextLastName.getText().toString();

        if (name.isEmpty())
        {
            editTextName.setError("This field is required");
            validation = false;
        }
        if (lastname.isEmpty())
        {
            editTextLastName.setError("This field is required");
            validation = false;
        }
        return validation;
    }

}