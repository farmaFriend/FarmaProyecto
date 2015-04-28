package com.example.ixtilion.proyecto;

import java.util.Date;

/**
 * Created by rok on 14/03/2015.
 */
public class Recordatorio_medicamento {
    private String medicamento;
    private String fecha_ini;
    private String fecha_fin;
    private int intervalo; //cada cuantas horas quiere que recuerde
    private int horaInic;
    private float cantidadToma;
    private int id;
    private String descripcion; //opcional
    private int diasTomaMes; //opcional, solo con configuracion avanzada
    private int diasDescansoMes; //opcional, solo con configuracion avanzada

    public Recordatorio_medicamento(String medicamento, String fecha_ini, String fecha_fin, int intervalo, float cantidadToma, int id, int horaIni){
        this.medicamento=medicamento;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.intervalo=intervalo;
        this.horaInic = horaIni;
        this.cantidadToma=cantidadToma;
        this.id=id;
        this.descripcion="";
        this.diasTomaMes=30;//Si es 0 es que se repite el mes entero
        this.diasDescansoMes=0;
    }
    public Recordatorio_medicamento(String medicamento, String fecha_ini, String fecha_fin, int intervalo, float cantidadToma, int id, String descripcion, int diasTomaMes, int diasDescansoMes, int horaIni){
        this.medicamento=medicamento;
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        this.intervalo=intervalo;
        this.cantidadToma=cantidadToma;
        this.id=id;
        this.descripcion=descripcion;
        this.diasTomaMes=diasTomaMes;
        this.diasDescansoMes=diasDescansoMes;
        this.horaInic = horaIni;
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
    public int getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getDiasTomaMes() {
        return diasTomaMes;
    }
    public int getDiasDescansoMes() {
        return diasDescansoMes;
    }
}
