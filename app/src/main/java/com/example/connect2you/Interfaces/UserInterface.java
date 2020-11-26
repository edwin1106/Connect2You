package com.example.connect2you.Interfaces;

import com.example.connect2you.Dtos.CreateUserDto;
import com.example.connect2you.Dtos.UserCredentialsDto;
import com.example.connect2you.Dtos.UserValidDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserInterface {
    @Headers({
            "Content-type: application/json"
    })
    @POST("api/User")
    Call<CreateUserDto> createUser(@Body CreateUserDto userDto);

    @POST("api/User/validate-user")
    Call<UserValidDto> validateUser(@Body UserCredentialsDto credentialsDto);
}
