package com.flickr.imagegallery.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Thia class will check the internet connection
 */

public class CheckInternetConnection {
    Context context = null;
    public CheckInternetConnection(Context context){
        this.context = context;
    }

    /**
     * This function will return boolean value
     * @return - if true internet is available otherwise return false
     */

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
