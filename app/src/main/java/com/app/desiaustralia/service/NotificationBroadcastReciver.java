package com.app.desiaustralia.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationBroadcastReciver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent!=null)
        {


            Toast.makeText(context, intent.getStringExtra("title")+"", Toast.LENGTH_SHORT).show();


        }else {

            Toast.makeText(context, "blank", Toast.LENGTH_SHORT).show();
        }


    }
}
