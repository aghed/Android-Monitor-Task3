package com.lio.adgar.myapplication.ExtraTask;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;

/**
 * Created by AGHED on 16/08/2017.
 */
public class ServiceByPort extends AsyncTask<String,Void,ServiceRow>{
    private Context context;
    public ServiceByPort(Context context)
    {
        this.context=context;
    }


    @Override
    protected ServiceRow doInBackground(String... params) {
        
        String DB_NAME=context.getApplicationInfo().dataDir+"/databases/ServicesByPortDB.db";
        Log.d("note",DB_NAME);
        String Port= (String) params[0];
        //defined class to provide sqlite helper
        FeedServiceDBHelper feedServiceDBHelper=new FeedServiceDBHelper(context);
        //custom method fully explained in the feedServiceDbHelper class
        feedServiceDBHelper.CreateDataBase();
        SQLiteDatabase db=feedServiceDBHelper.getReadableDatabase();
        //columns names to get
        String projection[]={NetService.FeedService.COL_NAME, NetService.FeedService.COL_PROTOCOL,
                NetService.FeedService.COL_DESCRIPTION};
        //search conditions
        String selection= NetService.FeedService.COL_PORT+"=?";
        String selectionArgs[]={"21"};
        //search the database for the given port
        Cursor testcursor=db.rawQuery("SELECT * FROM sqlite_master WHERE type='table';",null);
        testcursor.moveToFirst();
        Log.d("note",testcursor.getString(1));
        Cursor cursor=db.query(NetService.FeedService.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
        //get only first match
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            String servicename=cursor.getString(cursor.getColumnIndex(NetService.FeedService.COL_NAME));
            String protocol=cursor.getString(cursor.getColumnIndex(NetService.FeedService.COL_PROTOCOL));
            String descreption=cursor.getString(cursor.getColumnIndex(NetService.FeedService.COL_DESCRIPTION));
            Log.d("note","service : "+servicename+"\n protocol : "+protocol);
            return new ServiceRow(servicename,protocol,descreption);
        }
        return null;
    }

}
