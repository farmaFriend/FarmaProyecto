package com.example.ixtilion.proyecto;

/**
 * Created by USUARIO on 27/04/2015.
 */
public class Cita {

    private String descripcion;
    private String fecha;
    private String hora;
    private String medico;
    private String id;

    public Cita(String medico, String descripcion, String fecha, String hora, String id){
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.hora=hora;
        this.medico=medico;
        this.id=id;
    }


    public String getDescripcion(){
        return this.descripcion;
    }

    public String getFecha(){
        return this.fecha;
    }

    public String getHora(){
        return this.hora;
    }

    public String getMedico(){
        return this.medico;
    }

    public String getId(){return  this.id;}

}
