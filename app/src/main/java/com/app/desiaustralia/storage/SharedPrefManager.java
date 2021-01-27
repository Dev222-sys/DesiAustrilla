package com.app.desiaustralia.storage;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context mCtx;
    private static  final String SHARED_PREF_NAME="my_shared_preff";

    private static  final String Access_Token="token";
    private static final String keypage="page";

    private static final String keytitle="title";
    private static final String keyurl="url";
    private static final String  keyDes="des";


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


    public boolean userLogin(String page, String title, String des,String url){

        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(keypage,page);
        editor.putString(keytitle,title);
        editor.putString(keyDes,des);
        editor.putString(keyurl,url);




        editor.apply();
        return true;
    }




    public String getpage(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(keypage,null);

    }

    public String gettitle(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(keytitle,null);

    }

    public String getdes(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(keyDes,null);

    }

    public String geturl(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(keyurl,null);

    }


    public boolean isurl(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(keyurl, null) != null;
    }


}
