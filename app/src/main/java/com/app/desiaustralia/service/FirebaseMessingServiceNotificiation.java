package com.app.desiaustralia.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.app.desiaustralia.HomePageDetailsActivity;
import com.app.desiaustralia.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class FirebaseMessingServiceNotificiation extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {

        String stringUri = null;
        try {


            Uri url = remoteMessage.getNotification().getImageUrl();


             stringUri = url.toString();




        }catch (Exception e1)
        { }

        // setPendingIntent(body_title, body_message, "NOT_SET", "{}");




        generateNotification(
                remoteMessage.getNotification().getBody(),
                remoteMessage.getNotification().getTitle(),
                stringUri
        );
    }

    private void generateNotification(String body, String title, String imageUrl) {



        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());

        } catch (Exception e) {
        }


        Intent ServiceIntent=new Intent(this,MyIntentService.class);


        ServiceIntent.putExtra("page", "home");
        ServiceIntent.putExtra("title", title);
        ServiceIntent.putExtra("image", imageUrl);
        ServiceIntent.putExtra("desc", body);


        PendingIntent ServicePendingIntent=PendingIntent.getService(

                this,0,ServiceIntent,PendingIntent.FLAG_UPDATE_CURRENT


        );





        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_TWO_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(bit)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(ServicePendingIntent)
                .setOnlyAlertOnce(false)
                .build();



        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0,notification);

    }


}
