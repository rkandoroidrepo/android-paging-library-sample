package com.paging.com.mysample.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppNetworkStatus implements NetworkStatus {

    private Context context;

    public AppNetworkStatus(Context context) {
        this.context = context;
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Device is online
            return true;
        } else {
            // Device is not online
            return false;
        }
    }
}
