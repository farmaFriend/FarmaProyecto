package com.example.ixtilion.proyecto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rok on 14/03/2015.
 */
public class Recordatorio_medicamento implements Serializable {
    private String medicamento;
    private String fecha_ini;
    private String fecha_fin;
    private int intervalo; //cada cuantas horas quiere que recuerde
    private int horaInic, minIni;
    private float cantidadToma;
    private int id;

    public Recordatorio_medicamento(String medicamento, String fecha_ini, String fecha_fin, int intervalo, float cantidadToma, int id, int horaIni, int minIni){
        this.medicamento=medicamento;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.intervalo=intervalo;
        this.horaInic = horaIni;
        this.minIni=minIni;
        this.cantidadToma=cantidadToma;
        this.id=id;
    }
    public String getMedicamento() {
        return medicamento;
    }
    public String getFecha_ini() {
        return fecha_ini;
    }
    public String getFecha_fin() {
        return fecha_fin;
    }
    public int getIntervalo() {
        return intervalo;
    }
    public float getCantidadToma() {
        return cantidadToma;
    }
    public int getHoraIni() {
        return horaInic;
    }
    public int getMinIniIni() {
        return minIni;
    }
    public int getId() {
        return id;
    }
}
