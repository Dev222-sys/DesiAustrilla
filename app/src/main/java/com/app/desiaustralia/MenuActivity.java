package com.app.desiaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.desiaustralia.adapter.NewsLetterAdapter;
import com.app.desiaustralia.adapter.TopBollywoodAdapter;
import com.app.desiaustralia.model.eventscalender.EventCalenderModel;
import com.app.desiaustralia.model.eventsgallery.EventsGalleryModel;
import com.app.desiaustralia.model.firstdayfirstshow.FirstDayFirstShowModel;
import com.app.desiaustralia.model.newsletter.DataItems;
import com.app.desiaustralia.model.newsletter.NewsLetterModel;
import com.app.desiaustralia.model.topbollywoodsong.BollywoodSongData;
import com.app.desiaustralia.model.topbollywoodsong.TopBollywoodSongModel;
import com.app.desiaustralia.retrofit.RetrofitHelper;
import com.app.desiaustralia.utility.NetworkUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recylerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recylerView = findViewById(R.id.recylerView);
        progressBar = findViewById(R.id.progressBar);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MenuActivity.this, 1);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setNestedScrollingEnabled(true);

        toolbar.setTitle(getIntent().getStringExtra("title"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (!getIntent().getStringExtra("category").equalsIgnoreCase("")){
            if (getIntent().getStringExtra("category").equalsIgnoreCase("eventscalendar")) {
                if (NetworkUtil.checkNetworkStatus(MenuActivity.this)) {
                    progressBar.setVisibility(View.VISIBLE);
                    RetrofitHelper.getInstance().getEventCalendar(callbackEventCalender);
                } else {
                    Toast.makeText(MenuActivity.this, "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }
            } else if (getIntent().getStringExtra("category").equalsIgnoreCase("eventsgallery")) {
                if (NetworkUtil.checkNetworkStatus(MenuActivity.this)) {
                    progressBar.setVisibility(View.VISIBLE);
                    RetrofitHelper.getInstance().getEventGallery(callbackEventGallery);
                } else {
                    Toast.makeText(MenuActivity.this, "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }
            }
            else if (getIntent().getStringExtra("category").equalsIgnoreCase("firstdayfirstshow")) {
                if (NetworkUtil.checkNetworkStatus(MenuActivity.this)) {
                    progressBar.setVisibility(View.VISIBLE);
                    RetrofitHelper.getInstance().getFirstDayFirstShow(firstDayFirstShowModelCallback);
                } else {
                    Toast.makeText(MenuActivity.this, "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }
            }
            else if (getIntent().getStringExtra("category").equalsIgnoreCase("top10bollywoodsong")) {
                if (NetworkUtil.checkNetworkStatus(MenuActivity.this)) {
                    progressBar.setVisibility(View.VISIBLE);
                    RetrofitHelper.getInstance().getTopBollywoodSong(topBollywoodCallback);
                } else {
                    Toast.makeText(MenuActivity.this, "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if (NetworkUtil.checkNetworkStatus(MenuActivity.this)) {
                    progressBar.setVisibility(View.VISIBLE);
                    RetrofitHelper.getInstance().getNewsLetter(callback, getIntent().getStringExtra("category"));
                } else {
                    Toast.makeText(MenuActivity.this, "" + getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    Callback<TopBollywoodSongModel> topBollywoodCallback = new Callback<TopBollywoodSongModel>() {
        @Override
        public void onResponse(Call<TopBollywoodSongModel> call, Response<TopBollywoodSongModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1){
                    ArrayList<BollywoodSongData> arrayList = response.body().getData().get(0).getData();
                    TopBollywoodAdapter adapter = new TopBollywoodAdapter(MenuActivity.this, arrayList);
                    recylerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        @Override
        public void onFailure(Call<TopBollywoodSongModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }
    };

    Callback<FirstDayFirstShowModel> firstDayFirstShowModelCallback = new Callback<FirstDayFirstShowModel>() {
        @Override
        public void onResponse(Call<FirstDayFirstShowModel> call, Response<FirstDayFirstShowModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1){
                    ArrayList<DataItems> arrayList = response.body().getData().get(0).getArrayList();
                    NewsLetterAdapter adapter = new NewsLetterAdapter(MenuActivity.this, arrayList, "magazine");
                    recylerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<FirstDayFirstShowModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }
    };

    Callback<EventsGalleryModel> callbackEventGallery = new Callback<EventsGalleryModel>() {
        @Override
        public void onResponse(Call<EventsGalleryModel> call, Response<EventsGalleryModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1){
                    ArrayList<DataItems> arrayList = response.body().getData().get(0).getArrayList();
                    NewsLetterAdapter adapter = new NewsLetterAdapter(MenuActivity.this, arrayList, "magazine");
                    recylerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<EventsGalleryModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }
    };

    Callback<EventCalenderModel> callbackEventCalender = new Callback<EventCalenderModel>() {
        @Override
        public void onResponse(Call<EventCalenderModel> call, Response<EventCalenderModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1){
                    ArrayList<DataItems> arrayList = response.body().getData().get(0).getArrayList();
                    NewsLetterAdapter adapter = new NewsLetterAdapter(MenuActivity.this, arrayList, "magazine");
                    recylerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<EventCalenderModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);

        }
    };

    Callback<NewsLetterModel> callback = new Callback<NewsLetterModel>() {
        @Override
        public void onResponse(Call<NewsLetterModel> call, Response<NewsLetterModel> response) {
            progressBar.setVisibility(View.GONE);
            try {
                if (response.body().getStatus() == 1){
                    ArrayList<DataItems> arrayList = response.body().getData().get(0).getArrayList();
                    NewsLetterAdapter adapter = new NewsLetterAdapter(MenuActivity.this, arrayList, "magazine");
                    recylerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<NewsLetterModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(MenuActivity.this, ContactUsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}