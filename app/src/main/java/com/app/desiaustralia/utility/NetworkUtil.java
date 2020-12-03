package com.app.desiaustralia.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    private NetworkUtil() {
    }


    public static boolean checkNetworkStatus(Context context) {
        boolean status = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo tempNetworkInfo : networkInfos) {

            if (tempNetworkInfo.isConnected()) {
                status = true;
                break;
            }
        }
        return status;
    }
}
