package com.example.ixtilion.proyecto;

/**
 * Created by Ixtilion on 3/5/2015.
 */
public class Comunidad {
    private String name,link, ico;

    public Comunidad(String name, String link, String ico){
        this.name = name;
        this.link = link;
        this.ico = ico;
    }

    public String getName(){
        return this.name;
    }

    public String getLink(){
        return this.link;
    }
    public String getIco(){
        return this.ico;
    }

}
