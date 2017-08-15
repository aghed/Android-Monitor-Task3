package com.lio.adgar.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public final OnNetworkChangeBroadCastRevicer NetBroadCasrReciver=new OnNetworkChangeBroadCastRevicer();
    public final IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent filter for the network connectivity action

        //the Defined class for broadcast receiver

        //register the broadcast receiver dynamically
        //it can be registered  in the manifest
        registerReceiver(NetBroadCasrReciver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(NetBroadCasrReciver);
    }
}
