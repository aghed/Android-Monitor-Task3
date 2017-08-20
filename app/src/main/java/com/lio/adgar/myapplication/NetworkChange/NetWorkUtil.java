package com.lio.adgar.myapplication.NetworkChange;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by AGHED on 14/08/2017.
 */
public class NetWorkUtil {
    public static final int TYPE_NOT_CONNECTED=0;
    public static final int TYPE_MOBILE_DATA=1;
    public static final int TYPE_WIFI=2;
    //maybe there is a dummy or bluetooth connection type
    public static final int TYPE_OTHER=3;

    public static int getConnectivityStatus(Context context)
    {
        //the connection manger
        ConnectivityManager connectivityManager=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        //check if there is a connection at all
        if(networkInfo==null)
            return TYPE_NOT_CONNECTED;
        //check if it is wifi
        if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI)
            return TYPE_WIFI;
        //check if it is mobile data
        if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE)
            return  TYPE_MOBILE_DATA;
        //a bluetooth or dummy
        return TYPE_OTHER;
    }
}
