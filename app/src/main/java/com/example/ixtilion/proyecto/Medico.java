package com.example.ixtilion.proyecto;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Medico {
    private String nombre;
    private String especialidad;
    private String direccion;
    private String id;

    public Medico(String nombre, String especialidad, String direccion, String id){
        this.nombre=nombre;
        this.especialidad=especialidad;
        this.direccion=direccion;
        this.id=id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getEspecialidad(){
        return this.especialidad;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public String getId(){
        return this.id;
    }

}
