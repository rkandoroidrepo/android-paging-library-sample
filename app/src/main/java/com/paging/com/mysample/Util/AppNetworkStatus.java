package com.paging.com.mysample.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
public class AppNetworkStatus implements NetworkStatus {

    private Context context;

    public AppNetworkStatus(Context context) {
        this.context = context;
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // Device is online
        // Device is not online
        return networkInfo != null && networkInfo.isConnected();
    }
}
