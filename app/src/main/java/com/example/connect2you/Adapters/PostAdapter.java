package com.example.connect2you.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect2you.Dtos.CategoryType;
import com.example.connect2you.Dtos.PostDto;
import com.example.connect2you.R;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private List<PostDto> postsList;
    private Context context;

    public PostAdapter(List<PostDto> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Object getItem(int i) {
        return postsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PostDto postDto = (PostDto) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.post_item_layout, null);
        TextView txtTitle = view.findViewById(R.id.textViewTitle);
        TextView txtCategory = view.findViewById(R.id.textViewCategory);
        TextView txtDescription = view.findViewById(R.id.textViewDescription);
        ImageView image = view.findViewById(R.id.imagePost);

        txtTitle.setText(postDto.getTitle());
        txtCategory.setText((CategoryType.values()[postDto.getCategory()]).toString());
        txtDescription.setText(postDto.getDescription());
        switch (postDto.getCategory()){
            case 0:
                image.setImageResource(R.drawable.ciencia);
                break;
            case 1:
                image.setImageResource(R.drawable.ball);
                break;
            case 2:
                image.setImageResource(R.drawable.videogames);
                break;
        }

        return view;
    }
}
