package com.lio.adgar.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lio.adgar.myapplication.ExtraTask.FeedServiceDBHelper;
import com.lio.adgar.myapplication.ExtraTask.ServiceByPort;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public final OnNetworkChangeBroadCastRevicer NetBroadCasrReciver=new OnNetworkChangeBroadCastRevicer();
    public final IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    public byte[]buffer=new byte[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent filter for the network connectivity action

        //the Defined class for broadcast receiver

        //register the broadcast receiver dynamically
        //it can be registered  in the manifest
        registerReceiver(NetBroadCasrReciver,intentFilter);
        //test the service
        String port="21";
        new ServiceByPort(this).execute(port);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(NetBroadCasrReciver);
    }

}
