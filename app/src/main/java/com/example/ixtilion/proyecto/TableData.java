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
        public static final String COLUMN_NAME_DI = "id";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_ESPECIALIDAD = "especialidad";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_TELEFEONO = "telefono";
    }



}
