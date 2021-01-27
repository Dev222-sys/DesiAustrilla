package com.app.desiaustralia.service;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.app.desiaustralia.HomePageDetailsActivity;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
       /* Intent intent1=new Intent(this,MainActivity2.class);
        startActivity(intent1);
*/
        if (intent!=null)
        {
                String title=intent.getStringExtra("title");
                String  image=intent.getStringExtra("image");
                String body=intent.getStringExtra("body");

                Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(this, HomePageDetailsActivity.class);
            intent1.putExtra("page", "home");
            intent1.putExtra("title", title);
            intent1.putExtra("image", image);
            intent1.putExtra("desc", body);

            startActivity(intent1);






        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}