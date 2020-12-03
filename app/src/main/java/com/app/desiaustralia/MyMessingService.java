package com.app.desiaustralia;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public  void shownotification(String title,String msg)
    {

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyNotification")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_notifications)
                .setAutoCancel(true)
                .setContentText(msg);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
}
