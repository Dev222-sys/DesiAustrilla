package com.app.desiaustralia.storage;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context mCtx;
    private static  final String SHARED_PREF_NAME="my_shared_preff";

    private static  final String Access_Token="token";


    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized  SharedPrefManager getInstance(Context mCtx)
    {
        if( mInstance==null)
        {
            mInstance=new SharedPrefManager(mCtx);

        }
        return  mInstance;
    }

    public  boolean storeToken(String token)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Access_Token,token);
        editor.apply();

        return true;

    }
    public String getAccess_Token(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return sharedPreferences.getString(Access_Token,null);

    }
}
