package com.example.ixtilion.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by USUARIO on 12/03/2015.
 */
public class DatabaseOperations extends SQLiteOpenHelper{

    private Context c;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "farma.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String INT_TYPE = " integer";
    public static final String REAL_TYPE = " real";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TableData.TableInfo.TABLE_NAME_AGENDA + " (" +
                    TableData.TableInfo.COLUMN_NAME_NOMBRE + TEXT_TYPE  + COMMA_SEP +
                    TableData.TableInfo.COLUMN_NAME_TELEFEONO + TEXT_TYPE+ " primary key " + " )";

    private static final String SQL_CREATE_ENTRIES_MEDICAMENTO =
            "CREATE TABLE " + TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO + " (" +
                    TableData.TableInfoMedic.COLUMN_NAME_NOMBRE + TEXT_TYPE+ " primary key " + COMMA_SEP +
                    TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD + REAL_TYPE + " )";

    private static final String SQL_CREATE_ENTRIES_MEDICO =
            "CREATE TABLE " + TableData.TableInfoMedico.TABLE_NAME_MEDICO + " ( " +
                    TableData.TableInfoMedico.COLUMN_NAME_ID + TEXT_TYPE + " primary key " + COMMA_SEP +
                    TableData.TableInfoMedico.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfoMedico.COLUMN_NAME_ESPECIALIDAD + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfoMedico.COLUMN_NAME_DIRECCION + TEXT_TYPE + " ) ";

    private static final String SQL_CREATE_ENTRIES_RECORDATORIO =
            "CREATE TABLE " + TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO + " ( " +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_ID + TEXT_TYPE + " primary key " + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAINICIO + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAFIN + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_CANTIDADTOMA  + REAL_TYPE + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_INTERVALO + INT_TYPE + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_DIASTOMASMES + INT_TYPE + COMMA_SEP +
                    TableData.TableInfoRecordatorio.COLUMN_NAME_DIASDESCANSOMES + INT_TYPE + COMMA_SEP +
                    " foreign key ( " + TableData.TableInfoRecordatorio.COLUMN_NAME_MEDICAMENTO +" ) references " +
                            TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO + " ( " + TableData.TableInfoMedic.COLUMN_NAME_NOMBRE + " ) ) ";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableData.TableInfo.TABLE_NAME_AGENDA;

    private static final String SQL_DELETE_ENTRIES_MEDICAMENTO =
            "DROP TABLE IF EXISTS " + TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO;

    private static final String SQL_DELETE_ENTRIES_MEDICO =
            "DROP TABLE IF EXISTS " + TableData.TableInfoMedico.TABLE_NAME_MEDICO;

    private static final String SQL_DELETE_ENTRIES_RECORDATORIO =
            "DROP TABLE IF EXISTS " + TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO;


    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.c = context;
        Log.d("Operaciones bases de datos","BD creada");
    }



    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_MEDICAMENTO);
        db.execSQL(SQL_CREATE_ENTRIES_MEDICO);
        db.execSQL(SQL_DELETE_ENTRIES_RECORDATORIO);
        Log.d("Operaciones bases de datos","Tabla creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_MEDICAMENTO);
        db.execSQL(SQL_DELETE_ENTRIES_MEDICO);
        db.execSQL(SQL_DELETE_ENTRIES_RECORDATORIO);
        onCreate(db);
    }

    public Cursor cargarCursorContactos(){
        SQLiteDatabase db = this.getWritableDatabase();

        String s = "select "  + TableData.TableInfo.COLUMN_NAME_NOMBRE + " , " + TableData.TableInfo.COLUMN_NAME_TELEFEONO +
                " FROM " + TableData.TableInfo.TABLE_NAME_AGENDA;

        return db.rawQuery( s, null);
    }

    public Cursor cargarCursorMedicamentos(){
        SQLiteDatabase db = this.getWritableDatabase();

        String s = "select " + TableData.TableInfoMedic.COLUMN_NAME_NOMBRE + " , " + TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD +
                " FROM " + TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO;

        return db.rawQuery( s, null);
    }

    public Cursor cargarCursorMedicos(){
        SQLiteDatabase db = this.getWritableDatabase();

        String s = "select "+ TableData.TableInfoMedico.COLUMN_NAME_ID +" , " + TableData.TableInfoMedico.COLUMN_NAME_NOMBRE +
                " , " + TableData.TableInfoMedico.COLUMN_NAME_ESPECIALIDAD + " , " + TableData.TableInfoMedico.COLUMN_NAME_DIRECCION +
                " FROM " + TableData.TableInfoMedico.TABLE_NAME_MEDICO;

        return db.rawQuery( s, null);
    }


    public Cursor cargarCursorRecordatorios(){
        SQLiteDatabase db = this.getWritableDatabase();

        String s = "select "+ TableData.TableInfoRecordatorio.COLUMN_NAME_ID +" , " + TableData.TableInfoRecordatorio.COLUMN_NAME_DESCRIPCION +
                " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAINICIO + " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAFIN +
                " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_CANTIDADTOMA + " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_INTERVALO +
                " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_DIASTOMASMES + " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_DIASDESCANSOMES +
                " , " + TableData.TableInfoRecordatorio.COLUMN_NAME_MEDICAMENTO + " FROM " + TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO;

        return db.rawQuery( s, null);
    }

    public Cursor cargarCursorRecordatoriosCount(){
        SQLiteDatabase db = this.getWritableDatabase();

        String s = "SELECT COUNT(*) FROM " + TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO;

        return db.rawQuery(s,null);
    }

}
