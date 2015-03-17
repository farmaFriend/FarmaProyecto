package com.example.ixtilion.proyecto;

/**
 * Created by Ixtilion on 3/5/2015.
 */
public class Contacto {
    private String name, phone;

    public   Contacto ( String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

}
