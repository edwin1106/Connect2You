package com.example.connect2you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.connect2you.Adapters.PostAdapter;
import com.example.connect2you.Dtos.PostDto;
import com.example.connect2you.Dtos.UserCredentialsDto;
import com.example.connect2you.Dtos.UserValidDto;
import com.example.connect2you.Interfaces.PostInterface;
import com.example.connect2you.Interfaces.UserInterface;
import com.example.connect2you.Utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextMobile;
    private EditText editTextPassword;
    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign In");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextMobile = (EditText) findViewById(R.id.editTextPhone);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(view -> {
            if (validate()){
                validateUser();
            }
        });
    }

    private void validateUser() {
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
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();
        userCredentialsDto.setPassword(editTextPassword.getText().toString());
        userCredentialsDto.setPhone(Double.parseDouble(editTextMobile.getText().toString()));
        Call<UserValidDto> call = userInterface.validateUser(userCredentialsDto);
        call.enqueue(new Callback<UserValidDto>() {
            @Override
            public void onResponse(Call<UserValidDto> call, Response<UserValidDto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error connecting to Connect2You Api!", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserValidDto userValidDto = response.body();
                if (userValidDto.isValid()) {
                    Intent intent = new Intent(getApplicationContext(), PostsActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserValidDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Has ocurred an error loging user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate()
    {
        boolean validation = true;

        String mobile = editTextMobile.getText().toString();
        String password = editTextPassword.getText().toString();

        if (mobile.isEmpty())
        {
            editTextMobile.setError("This field is required");
            validation = false;
        }
        if (password.isEmpty())
        {
            editTextPassword.setError("This field is required");
            validation = false;
        }
        return validation;
    }
}