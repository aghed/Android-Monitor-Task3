package com.lio.adgar.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by AGHED on 14/08/2017.
 */
public class OnNetworkChangeBroadCastRevicer extends BroadcastReceiver {
    private TriggerThreadService triggerThreadService=new TriggerThreadService();
    public Intent Seriveintent;
    @Override
    public void onReceive(Context context, Intent intent) {
        int ConnectionStatus=NetWorkUtil.getConnectivityStatus(context);
        Seriveintent=new Intent(context,TriggerThreadService.class);
        //check if it is wifi or data
        if(ConnectionStatus==NetWorkUtil.TYPE_WIFI || ConnectionStatus==NetWorkUtil.TYPE_MOBILE_DATA)
        {
            context.startService(Seriveintent);
        }
        else
        {
            context.stopService(Seriveintent);
        }
    }

    public void StopTheServiceOnUnregister(Context context)
    {
        Log.d("note","stopping the service");
        context.stopService(Seriveintent);
    }
}
