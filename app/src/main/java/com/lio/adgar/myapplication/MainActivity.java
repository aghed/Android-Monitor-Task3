package com.lio.adgar.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lio.adgar.myapplication.ServiceByPort.ServiceByPort;
import com.lio.adgar.myapplication.NetworkChange.OnNetworkChangeBroadCastRevicer;

public class MainActivity extends AppCompatActivity {

    public final OnNetworkChangeBroadCastRevicer NetBroadCasrReciver=new OnNetworkChangeBroadCastRevicer();
    public final IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent filter for the network connectivity action

        //the Defined class for broadcast receiver

        //test ServiceByPort with any port
        String testport="443";
        new ServiceByPort(this).execute(testport);
        //register the broadcast receiver dynamically
        //it can be registered  in the manifest
                registerReceiver(NetBroadCasrReciver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("note","app destroyed");
                NetBroadCasrReciver.StopTheServiceOnUnregister(getApplicationContext());
                unregisterReceiver(NetBroadCasrReciver);
    }

}
