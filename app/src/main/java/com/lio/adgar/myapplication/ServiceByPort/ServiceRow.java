package com.lio.adgar.myapplication.ServiceByPort;

/**
 * Created by AGHED on 16/08/2017.
 */
// a class to save the resulting row from the database
public class ServiceRow {
    private String servicename;
    private String protocol;
    private String descreption;
    public ServiceRow(String Servicename,String Protocol,String Descreption)
    {
        this.servicename=Servicename;
        this.protocol=Protocol;
        this.descreption=Descreption;
    }
}
