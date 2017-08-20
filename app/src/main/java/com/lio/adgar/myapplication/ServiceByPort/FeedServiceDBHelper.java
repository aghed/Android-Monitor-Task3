package com.lio.adgar.myapplication.ServiceByPort;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AGHED on 16/08/2017.
 */
public class FeedServiceDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ServicesByPortDB.db";
    private String DB_PATH;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    //get it in the constructor
    public FeedServiceDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
        this.DB_PATH=context.getApplicationInfo().dataDir+"/"+"databases/";
    }

    //the shipped database need to be copied to the root because this sqlite helper will read it from there not from
    // our assets file
    public void CreateDataBase()
    {
        //check for file existence or create one then copy our assets file to it

        boolean dbExist=checkDataBase();
        if(dbExist)
        {
            //we already have the data base
        }else{
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }catch (Exception e)
            {
                Log.d("note","couldnt copy database");
            }
        }
    }


    private boolean checkDataBase() {
        SQLiteDatabase checkdb=null;
        try{
            checkdb=SQLiteDatabase.openDatabase(DB_PATH+DATABASE_NAME,null,SQLiteDatabase.CONFLICT_ABORT);
        }catch (Exception e)
        {
            Log.d("note","no database");
        }
        if(checkdb!=null)
        {
            checkdb.close();
        }
        return checkdb!=null ? true:false;
    }
    private void copyDataBase()  {
        //our db in assets file
        InputStream inputStream= null;
        try {
            inputStream = context.getAssets().open(DATABASE_NAME);

        //the file we will write to
        String dbfileout=DB_PATH+DATABASE_NAME;
        //the output stream for it
        OutputStream outputStream=new FileOutputStream(dbfileout);
        byte[] buffer=new byte[1024];
        int length;
        while((length=inputStream.read(buffer))>0)
        {
            Log.d("note","copying");
            outputStream.write(buffer,0,length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        } catch (IOException e) {
            Log.d("note","problem copying file");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
