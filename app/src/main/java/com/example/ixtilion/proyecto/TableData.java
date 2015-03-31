package com.example.ixtilion.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Alumno on 10/03/2015.
 */
public final class TableData {

    public TableData() {
    }

    ;



    public static abstract class TableInfo implements BaseColumns {
        public static final String TABLE_NAME_AGENDA = "agenda";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_TELEFEONO = "telefono";
    }
    public static abstract class TableInfoMedic implements BaseColumns {
        public static final String TABLE_NAME_MEDICAMENTO = "medicamento";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_CANTIDAD = "cantidad";
    }

    public static abstract class TableInfoMedico implements BaseColumns{
        public static final String TABLE_NAME_MEDICO = "medico";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_ESPECIALIDAD = "especialidad";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
    }


    public static abstract class TableInfoRecordatorio implements BaseColumns{
        public static final String TABLE_NAME_RECORDATORIO = "recordatorio";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_FECHAINICIO = "fecha inicio";
        public static final String COLUMN_NAME_FECHAFIN = "fecha fin";
        public static final String COLUMN_NAME_CANTIDADTOMA = " cantidad toma";
        public static final String COLUMN_NAME_INTERVALO = "intervalo";
        public static final String COLUMN_NAME_DIASTOMASMES = "dias tomas mes";
        public static final String COLUMN_NAME_DIASDESCANSOMES = "dias descanso mes";
        public static final String COLUMN_NAME_MEDICAMENTO = "medicamento";
    }


}
