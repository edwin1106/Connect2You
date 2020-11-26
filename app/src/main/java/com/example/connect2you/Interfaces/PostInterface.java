package com.example.connect2you.Interfaces;

import com.example.connect2you.Dtos.PostDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostInterface {
    @Headers({
            "Content-type: application/json"
    })
    @POST("api/Post")
    Call<PostDto> createPost(@Body PostDto userDto);

    @GET("api/Post")
    Call<List<PostDto>> getAllPost();
}
