package com.app.desiaustralia.retrofit;


import com.app.desiaustralia.model.ContactUsModel;
import com.app.desiaustralia.model.desitv.DesiTVModel;
import com.app.desiaustralia.model.eventscalender.EventCalenderModel;
import com.app.desiaustralia.model.eventsgallery.EventsGalleryModel;
import com.app.desiaustralia.model.firstdayfirstshow.FirstDayFirstShowModel;
import com.app.desiaustralia.model.home.HomeDataSuccess;
import com.app.desiaustralia.model.newsletter.NewsLetterModel;
import com.app.desiaustralia.model.topbollywoodsong.TopBollywoodSongModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApi {


    @FormUrlEncoded
    @POST("user/home")
    Call<HomeDataSuccess> getHomeData(
            @Field("user_name") String community_news);

    @POST("user/desitv")
    Call<DesiTVModel> getDesiTvData();

    @FormUrlEncoded
    @POST("user/databycategory")
    Call<NewsLetterModel> getNewsLetter(
            @Field("category") String category);

    @POST("user/eventscalendar")
    Call<EventCalenderModel> getEventCalendar();

    @POST("user/eventsgallery")
    Call<EventsGalleryModel> getEventGallery();

    @POST("user/firstdayfirstshow")
    Call<FirstDayFirstShowModel> getFirstDayFirstShow();

    @POST("user/top10bollywoodsong")
    Call<TopBollywoodSongModel> getTopBollywoodSong();


    @FormUrlEncoded
    @POST("cf7e/v1/enquiry")
    Call<ContactUsModel> contactUs(
            @Field("name") String name,
            @Field("email") String email,
            @Field("subject") String subject,
            @Field("message") String message
    );

}