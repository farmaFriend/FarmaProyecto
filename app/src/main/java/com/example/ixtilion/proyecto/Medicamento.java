package com.example.ixtilion.proyecto;

/**
 * Created by rok on 14/03/2015.
 */
public class Medicamento {
    private String nombre; //clave
    private float num_pastillas; //numero de pastillas que se posee

    public Medicamento (String nombre, float num_pastillas){
        this.nombre=nombre;
        this.num_pastillas=num_pastillas;
    }
    public String getNombre(){
        return this.nombre;
    }
    public float getNum_pastillas(){
        return this.num_pastillas;
    }
}
