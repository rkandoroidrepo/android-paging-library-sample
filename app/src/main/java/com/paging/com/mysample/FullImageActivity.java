package com.paging.com.mysample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent intent = getIntent();
        String url = intent.getStringExtra("imageUrl");
        if (!url.isEmpty()) {
            ImageView imageView = findViewById(R.id.image);
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.shimmer_background)
                    .into(imageView);
        }
    }
}
