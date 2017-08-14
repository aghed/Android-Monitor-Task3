package com.lio.adgar.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by AGHED on 14/08/2017.
 */
public class OnNetworkChangeBroadCastRevicer extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int ConnectionStatus=NetWorkUtil.getConnectivityStatus(context);

        //check if it is wifi or data
        if(ConnectionStatus==NetWorkUtil.TYPE_WIFI || ConnectionStatus==NetWorkUtil.TYPE_MOBILE_DATA)
        {
            context.startService(new Intent(context,TriggerThreadService.class));
        }
    }
}
