package com.lio.adgar.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent filter for the network connectivity action
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //the Defined class for broadcast receiver
        OnNetworkChangeBroadCastRevicer NetBroadCasrReciver=new OnNetworkChangeBroadCastRevicer();
        //register the broadcast receiver dynamically
        //it can be registered  in the manifest
        registerReceiver(NetBroadCasrReciver,intentFilter);
    }
}
