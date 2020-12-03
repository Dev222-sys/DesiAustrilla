package com.app.desiaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HomePageDetailsActivity extends AppCompatActivity {

    TextView titleTv, descTv, dateTv;
    ImageView imageView, playImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        titleTv = findViewById(R.id.titleTv);
        descTv = findViewById(R.id.descTv);
        imageView = findViewById(R.id.imageView);
        playImg = findViewById(R.id.playImg);
        dateTv = findViewById(R.id.dateTv);
        Glide.with(HomePageDetailsActivity.this).load(getIntent().getStringExtra("image")
        ).into(imageView);
        titleTv.setText(getIntent().getStringExtra("title"));
        descTv.setText(getIntent().getStringExtra("desc"));
        if (getIntent().getStringExtra("page").equalsIgnoreCase("home")){
        dateTv.setVisibility(View.GONE);
        }
        else if (getIntent().getStringExtra("page").equalsIgnoreCase("magazine")){
            dateTv.setText(getIntent().getStringExtra("date"));
        }
        else if (getIntent().getStringExtra("page").equalsIgnoreCase("newsletter")) {
            dateTv.setText(getIntent().getStringExtra("date"));
            descTv.setVisibility(View.GONE);
        }
        else if (getIntent().getStringExtra("page").equalsIgnoreCase("desi")){
            dateTv.setText(getIntent().getStringExtra("date"));
            playImg.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomePageDetailsActivity.this, WebViewActivity.class);
                    intent.putExtra("video", getIntent().getStringExtra("video"));
                    startActivity(intent);
                }
            });

        }
        }
}