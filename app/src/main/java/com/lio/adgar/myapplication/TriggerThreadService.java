package com.lio.adgar.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by AGHED on 14/08/2017.
 */
public class TriggerThreadService extends Service{
    public static final String MESSAGE_TAG="note";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //define the thread

        Thread thread= new Thread() {
            @Override
            public void run() {
                //check for Connectivity
                while ( NetWorkUtil.getConnectivityStatus(getApplicationContext())!= NetWorkUtil.TYPE_NOT_CONNECTED ) {
                    Log.d("Note","Service Started");
                    try {
                        Thread.sleep(5000);
                        Log.d(MESSAGE_TAG,"thread running");
                    } catch (InterruptedException e) {
                        Log.d("Exception","thread faild to sleep");
                    }
                }
                //if no Connection is Active Kill the thread
                Log.d(MESSAGE_TAG,"thread will be killed");
                this.interrupt();
            }
        };

        //start the thread
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
