package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.connect2you.Dtos.CreateUserDto;
import com.example.connect2you.Interfaces.UserInterface;
import com.example.connect2you.Utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpPasswordActivity extends AppCompatActivity {

    EditText editTextPassword;
    public String name;
    public String lastName;
    public String email;
    public String password;
    public double phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_password);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        lastName = intent.getStringExtra("Lastname");
        phone = intent.getDoubleExtra("Number", 0);
        email = intent.getStringExtra("Email");
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword2);
    }

    public void goToSignUpAccountCreatedActivity(View view) {
        if (validate()) {
            Intent intent = new Intent(getApplicationContext(),AccountCreatedActivity.class);
            password = editTextPassword.getText().toString();
            createUser();
            startActivity(intent);
        }
    }

    private void createUser() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        UserInterface userInterface = retrofit.create(UserInterface.class);

        CreateUserDto userDto = new CreateUserDto();
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setFirstName(name);
        userDto.setLastName(lastName);
        userDto.setPhone(phone);
        Call<CreateUserDto> call = userInterface.createUser(userDto);
        call.enqueue(new Callback<CreateUserDto>() {
            @Override
            public void onResponse(Call<CreateUserDto> call, Response<CreateUserDto> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Toast.makeText(getApplicationContext(), "User successfully registered!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CreateUserDto> call, Throwable t) {
                Log.d("", t.getMessage());
                Toast.makeText(getApplicationContext(), "Has ocurred an error registering user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate()
    {
        boolean validation = true;

        String password = editTextPassword.getText().toString();

        if (password.isEmpty())
        {
            editTextPassword.setError("This field is required");
            validation = false;
        }
        return validation;
    }
}