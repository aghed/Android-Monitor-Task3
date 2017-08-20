package com.lio.adgar.myapplication.ServiceByPort;

import android.provider.BaseColumns;

/**
 * Created by AGHED on 16/08/2017.
 */

//a class as container for database columns tags
public final class NetService{
    private NetService(){}

    public  static class FeedService implements BaseColumns {
        public static final String TABLE_NAME="services";
        public static final String COL_NAME="name";
        public static final String COL_PORT="port";
        public static final String COL_PROTOCOL="protocol";
        public static final String COL_DESCRIPTION="description";
    }
}
