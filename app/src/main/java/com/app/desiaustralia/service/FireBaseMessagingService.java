  package com.app.desiaustralia.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.app.desiaustralia.HomePageDetailsActivity;
import com.app.desiaustralia.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class FireBaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "FireBaseNotification ";

    @Override
    public void onNewToken(@NonNull String s) {

        super.onNewToken(s);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {

        Log.e("FireBaseNotification ", "onMessageReceived");
        Log.i("PVL", "RECEIVED MESSAGE: " + remoteMessage.getData());

        Log.i("PVL", "RECEIVED notification : " + remoteMessage.getNotification());







        if (remoteMessage.getData().size() > 0) {

            try {
                JSONObject objectJson = new JSONObject(remoteMessage.getData());
                Log.e(TAG, "Message data objectJson : " + objectJson + " ");
                String title = remoteMessage.getData().get("title");
                String message = remoteMessage.getData().get("message");


                /*Uri type = remoteMessage.getData().get();
                setPendingIntent(title, message, "j");
*/
                  //   setPendingIntent(title, message, "type", objectJson.toString());

            } catch (Exception e) {
                e.printStackTrace();

                if (remoteMessage.getNotification() != null) {

                    String body_message = remoteMessage.getNotification().getBody();
                    String body_title = remoteMessage.getNotification().getTitle();
                    //String url = "https://www.desiaustralia.com/biz-directory/wp-content/uploads/2017/11/cropped-Untitled-1.png";
                    try {


                    Uri url = remoteMessage.getNotification().getImageUrl();


                    String stringUri = url.toString();
                    Log.e(TAG, "Response: body_title " + body_title);

                    setPendingIntent(body_title, body_message, stringUri);

                    }catch (Exception e1)
                    { }

                    // setPendingIntent(body_title, body_message, "NOT_SET", "{}");


                }
            }
        }
        else {

            if (remoteMessage.getNotification() != null) {

                String body_message = remoteMessage.getNotification().getBody();
                String body_title = remoteMessage.getNotification().getTitle();
                 Uri url=remoteMessage.getNotification().getImageUrl();
                 try {


                     String stringUri = url.toString();



                //String url="https://www.desiaustralia.com/biz-directory/wp-content/uploads/2017/11/cropped-Untitled-1.png";




                Log.e(TAG, "Response: body_title " + body_title);


                setPendingIntent(body_title, body_message, stringUri);
                // setPendingIntent(body_title, body_message, "NOT_SET", "{}");
                 }catch (Exception e)
                 {

                 }



            }
            else {
                Toast.makeText(this, "nnot get", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void setPendingIntent(String title, String message, String url) {

     //   String stringUri = url.toString();


       /* Intent broadcastIntent=new Intent(this,NotificationBroadcastReciver.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, broadcastIntent
                , PendingIntent.FLAG_CANCEL_CURRENT);
        broadcastIntent.putExtra("page", "home");
        broadcastIntent.putExtra("title", title);
        broadcastIntent.putExtra("image", url);
        broadcastIntent.putExtra("desc", message);
*/




        Intent intent = new Intent(this, HomePageDetailsActivity.class);
        intent.putExtra("page", "home");
        intent.putExtra("title", title);
        intent.putExtra("image", url);
        intent.putExtra("desc", message);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent
                , PendingIntent.FLAG_UPDATE_CURRENT);

        sendNotification(title, message, url, pendingIntent);
    }

    private void sendNotification(String title, String message, String url, PendingIntent pendingIntent) {

        String channelId = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());

        } catch (Exception e) {
        }





        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(bit)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setSound(defaultSoundUri)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setOnlyAlertOnce(false)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = notificationBuilder.build();
        notificationManager.notify(1 /* ID of notification */, notification);
    }

    private boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = null;
        if (activityManager != null) appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) return false;
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName))
                return true;
        }
        return false;
    }

}
