package com.test.weatherapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    private Context ctx;

    public NetworkUtil(Context ctx){
        this.ctx = ctx;
    }

    public Boolean checkConnectStatus(){
            ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = netInfo != null &&
                    netInfo.isConnectedOrConnecting();
            return isConnected;


    }
}
