package com.example.ixtilion.proyecto;

/**
 * Created by Ixtilion on 3/5/2015.
 */
public class Contacto {
    private String name, phone, id;

    public   Contacto (String id, String name, String phone){
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getId(){return this.id;}
}
