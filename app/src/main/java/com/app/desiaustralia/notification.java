package com.app.desiaustralia;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class notification extends FirebaseMessagingService {
   /* @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

      *//* Intent intent=new Intent(this,RequestConfirmationActivity.class);
        startActivity(intent);*//*
       showNotifcation(remoteMessage.getNotification().getBody());
    }*/


   /* public void showNotifcation(String title) {

        PendingIntent pi= PendingIntent.getActivity(this,0,new Intent(this,RequestConfirmationActivity.class),0);
        Notification notification=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("")
                .setContentText(title)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);
    }*/
}


