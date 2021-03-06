package com.lio.adgar.myapplication.NetworkChange;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by AGHED on 14/08/2017.
 */
public class TriggerThreadService extends Service{
    public static final String MESSAGE_TAG="note";
    public String CONNECTION_TYPE;
    private Thread thread;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Note","Service Started");
        //define the thread
        thread= new Thread() {
            @Override
            public void run() {
                //check for Connectivity
                while ( NetWorkUtil.getConnectivityStatus(getApplicationContext())!= NetWorkUtil.TYPE_NOT_CONNECTED ) {

                    if(NetWorkUtil.getConnectivityStatus(getApplication())==NetWorkUtil.TYPE_WIFI)
                        CONNECTION_TYPE="Wi fi";

                    else
                    if(NetWorkUtil.getConnectivityStatus(getApplicationContext())==NetWorkUtil.TYPE_MOBILE_DATA)

                        CONNECTION_TYPE="Mobile Data";
                    if(! CONNECTION_TYPE.isEmpty())
                        Log.d(MESSAGE_TAG,"Connection Type : "+CONNECTION_TYPE);
                    else
                    //it is connected but not through wifi or mobile data then we don't want it
                    return;
                    try {
                        //make any task you want every 5 seconds 'I made it only 5 sec to see the results
                        // faster instead of waiting 30 seconds
                        Thread.sleep(5000);
                        Log.d(MESSAGE_TAG,"thread running");
                    } catch (InterruptedException e) {
                        Log.d(MESSAGE_TAG,"thread is killed");
                    }
                }
                //if no Connection is Active Kill the thread
                Log.d(MESSAGE_TAG,"thread will be killed");
                stopSelf();
            }
        };
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("note","Service is down");
        thread.interrupt();
        Log.d("note","thread is killed");
    }
}
