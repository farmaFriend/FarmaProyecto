package com.example.ixtilion.proyecto;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Medico {
    private String nombre;
    private String especialidad;
    private String direccion;
    private String telefono;

    public Medico(String nombre, String especialidad, String direccion, String telefono){
        this.nombre=nombre;
        this.especialidad=especialidad;
        this.direccion=direccion;
        this.telefono=telefono;
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

    public String getTelefono(){
        return this.telefono;
    }

}
