package com.lio.adgar.myapplication.NetworkChange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by AGHED on 14/08/2017.
 */
public class OnNetworkChangeBroadCastRevicer extends BroadcastReceiver {
    private TriggerThreadService triggerThreadService=new TriggerThreadService();
    @Override
    public void onReceive(Context context, Intent intent) {
        int ConnectionStatus=NetWorkUtil.getConnectivityStatus(context);
        //check if it is wifi or data
        if(ConnectionStatus==NetWorkUtil.TYPE_WIFI || ConnectionStatus==NetWorkUtil.TYPE_MOBILE_DATA)
        {
            context.startService(new Intent(context,TriggerThreadService.class));
        }
        else
        {
            context.stopService(new Intent(context,TriggerThreadService.class));
        }
    }

    public void StopTheServiceOnUnregister(Context context)
    {
        Log.d("note","stopping the service");
        context.stopService(new Intent(context,TriggerThreadService.class));
    }
}
