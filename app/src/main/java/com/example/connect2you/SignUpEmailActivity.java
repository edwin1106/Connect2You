package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpEmailActivity extends AppCompatActivity {

    EditText editTextEmail;
    public String name;
    public String lastName;
    public String email;
    public double phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name").toString();
        lastName = intent.getStringExtra("Lastname").toString();
        phone = intent.getDoubleExtra(SignUpNameActivity.EXTRA_NUMBER, 0);
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
    }

    public void goToSignUpPasswordActivity(View view) {
        if (validate()){
            email = editTextEmail.getText().toString();
            Intent intent = new Intent(getApplicationContext(),SignUpPasswordActivity.class);
            intent.putExtra("Name", name);
            intent.putExtra("Lastname", lastName);
            intent.putExtra("Number", phone);
            intent.putExtra("Email", email);
            startActivity(intent);
        }
    }

    private boolean validate()
    {
        boolean validation = true;

        String email = editTextEmail.getText().toString();

        if (email.isEmpty())
        {
            editTextEmail.setError("This field is required");
            validation = false;
        }
        else {
            if (!email.contains("@")) {
                editTextEmail.setError("Enter a valid email");
                validation = false;
            }
        }
        return validation;
    }
}