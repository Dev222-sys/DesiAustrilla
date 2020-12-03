package com.app.desiaustralia.retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.app.desiaustralia.model.ContactUsModel;
import com.app.desiaustralia.model.desitv.DesiTVModel;
import com.app.desiaustralia.model.eventscalender.EventCalenderModel;
import com.app.desiaustralia.model.eventsgallery.EventsGalleryModel;
import com.app.desiaustralia.model.firstdayfirstshow.FirstDayFirstShowModel;
import com.app.desiaustralia.model.home.HomeDataSuccess;
import com.app.desiaustralia.model.newsletter.NewsLetterModel;
import com.app.desiaustralia.model.topbollywoodsong.TopBollywoodSongModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {
    private static RetrofitHelper ourInstance = new RetrofitHelper();

    private final static String BASE_URL = "https://www.desiaustralia.com/wp-json/";

    public static RetrofitHelper getInstance() {
        return ourInstance;
    }

    private RetrofitHelper() {
    }

    private RetrofitApi retrofitapi;

    public void init(final Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        ongoing.addHeader("Accept", "application/json;versions=1");
//                        ongoing.addHeader("X-lang", Prefs.getString(context, LNG, "en"));
//                        if (Prefs.getString(context, "session", "false").equalsIgnoreCase("true")) {
//                            Log.d(">>>>>>>>>", "intercept: " + Prefs.getString(context, "token", null));
//                            ongoing.addHeader("Authorization", "Bearer " + Prefs.getString(context, "token", null));
//                            return chain.proceed(ongoing.build());
//                        }
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        retrofitapi = retrofit.create(RetrofitApi.class);
    }

    public void getHomeData(Callback<HomeDataSuccess> callback, String community_news) {
        Call<HomeDataSuccess> responseCall;
        responseCall = retrofitapi.getHomeData(community_news);
        responseCall.enqueue(callback);
    }

    public void getDesiTvData(Callback<DesiTVModel> callback) {
        Call<DesiTVModel> responseCall;
        responseCall = retrofitapi.getDesiTvData();
        responseCall.enqueue(callback);
    }

    public void getNewsLetter(Callback<NewsLetterModel> callback, String category) {
        Call<NewsLetterModel> responseCall;
        responseCall = retrofitapi.getNewsLetter(category);
        responseCall.enqueue(callback);
    }

    public void getEventCalendar(Callback<EventCalenderModel> callbackEventCalender) {
        Call<EventCalenderModel> responseCall;
        responseCall = retrofitapi.getEventCalendar();
        responseCall.enqueue(callbackEventCalender);

    }

    public void getEventGallery(Callback<EventsGalleryModel> callbackEventGallery) {
        Call<EventsGalleryModel> responseCall;
        responseCall = retrofitapi.getEventGallery();
        responseCall.enqueue(callbackEventGallery);
    }

    public void getFirstDayFirstShow(Callback<FirstDayFirstShowModel> firstDayFirstShowModelCallback) {
        Call<FirstDayFirstShowModel> responseCall;
        responseCall = retrofitapi.getFirstDayFirstShow();
        responseCall.enqueue(firstDayFirstShowModelCallback);
    }

    public void getTopBollywoodSong(Callback<TopBollywoodSongModel> topBollywoodCallback) {
        Call<TopBollywoodSongModel> responseCall;
        responseCall = retrofitapi.getTopBollywoodSong();
        responseCall.enqueue(topBollywoodCallback);
    }

    public void contactUs(Callback<ContactUsModel> callback, String name, String email, String subject, String message) {
        Call<ContactUsModel> responseCall;
        responseCall = retrofitapi.contactUs(name, email, subject, message);
        responseCall.enqueue(callback);
    }
}