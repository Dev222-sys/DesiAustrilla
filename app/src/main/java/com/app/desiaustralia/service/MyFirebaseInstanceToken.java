package com.app.desiaustralia.service;

import android.util.Log;

import com.app.desiaustralia.storage.SharedPrefManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceToken extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshtoken= FirebaseInstanceId.getInstance().getToken();
        Log.d("myfriebaseid","refresh token"+refreshtoken);
        storeToken(refreshtoken);
        
    }

    private void storeToken(String refreshtoken) {
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(refreshtoken);

    }
}
