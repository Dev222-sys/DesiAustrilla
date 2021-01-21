package com.app.desiaustralia.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.app.desiaustralia.HomePageDetailsActivity;
import com.app.desiaustralia.HomepageActivity;
import com.app.desiaustralia.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MyFirebaseService3 extends FirebaseMessagingService {
    public static int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {


            generateNotification(
                    remoteMessage.getNotification().getBody(),
                    remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getImageUrl()
            );
        }



    }

    private void generateNotification(String body, String title, Uri url) {

        String stringUri = url.toString();

        Bitmap bit=null;
        try {
            bit = BitmapFactory.decodeStream((InputStream)new URL(stringUri).getContent());

        } catch (Exception e) {}

        Intent intent = new Intent(this, HomePageDetailsActivity.class);
        intent.putExtra("page", "home");
        intent.putExtra("title", title);
        intent.putExtra("image", stringUri);
        intent.putExtra("desc", body);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent
                , PendingIntent.FLAG_CANCEL_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifcationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(bit)
                .setSound(alarmSound)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (NOTIFICATION_ID > 1073741824) {
            NOTIFICATION_ID = 0;
        }
        notificationManager.notify(NOTIFICATION_ID++, notifcationBuilder.build());

        startForeground(NOTIFICATION_ID++,notifcationBuilder.build());



    }

}