package com.example.connect2you;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connect2you.Adapters.PostAdapter;
import com.example.connect2you.Dtos.PostDto;
import com.example.connect2you.Interfaces.PostInterface;
import com.example.connect2you.Utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends AppCompatActivity {
    public ListView listViewPosts;
    private PostAdapter postAdapter;
    public List<PostDto> postDtoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Feed");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);
        listViewPosts = findViewById(R.id.listViewPosts);
        getAllPosts(listViewPosts);
    }

    private void getAllPosts(final ListView listView) {
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

        PostInterface postInterface = retrofit.create(PostInterface.class);

        Call<List<PostDto>> call = postInterface.getAllPost();
        call.enqueue(new Callback<List<PostDto>>() {
            @Override
            public void onResponse(Call<List<PostDto>> call, Response<List<PostDto>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error connecting to Connect2You Api!", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<PostDto> postsList = response.body();
                for (PostDto post: postsList) {
                    postDtoList.add(post);
                }
                postAdapter = new PostAdapter(postDtoList, getApplicationContext());
                listView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<PostDto>> call, Throwable t) {
                Log.d("", t.getMessage());
                Toast.makeText(getApplicationContext(), "Has ocurred an error getting posts", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
