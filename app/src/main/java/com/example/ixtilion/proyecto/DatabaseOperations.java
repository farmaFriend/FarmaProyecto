package com.example.ixtilion.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USUARIO on 12/03/2015.
 */
public class DatabaseOperations extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "farma.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String INT_TYPE = " integer";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TableData.TableInfo.TABLE_NAME_AGENDA + " (" +
                    TableData.TableInfo._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableInfo.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfo.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    TableData.TableInfo.COLUMN_NAME_TELEFEONO + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableData.TableInfo.TABLE_NAME_AGENDA;

    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Operaciones bases de datos","BD creada");

    }


    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("Operaciones bases de datos","Tabla creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    public void putInformation(DatabaseOperations dbo, String id, String nombre, String telf){

        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.COLUMN_NAME_ID, id);
        cv.put(TableData.TableInfo.COLUMN_NAME_NOMBRE, nombre);
        cv.put(TableData.TableInfo.COLUMN_NAME_TELEFEONO, telf);



        SQLiteDatabase sq = dbo.getWritableDatabase();
        long k = sq.insert(TableData.TableInfo.TABLE_NAME_AGENDA, "", cv);

        Log.d("Operaciones bases de datos","Insertada una fila");


    }
}
