package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by USUARIO on 12/03/2015.
 */
public class RegAct extends Fragment {
    EditText ID, NOMBRE, TELF;
    String id, nombre, telf;
    Button anadir;
    Context c;
    int i = 0;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        c = container.getContext();

        View view = inflater.inflate(R.layout.agregar_contacto, container, false);


        anadir = (Button) view.findViewById(R.id.buttonAñadir);

        NOMBRE = (EditText) view.findViewById(R.id.editTextNombre);
        TELF = (EditText) view.findViewById(R.id.editTextTelf);
        //ID = (EditText) view.findViewById(R.id.editTextId);


        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i ++;

                id = String.valueOf(i);
                nombre = NOMBRE.getText().toString();
                telf = TELF.getText().toString();


                if(c!=null) {

                    Log.d("NO error","if");
                    DatabaseOperations dbHelper = new DatabaseOperations(c);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();


                    if (db != null) {
                        ContentValues cv = new ContentValues();

                        cv.put(TableData.TableInfo.COLUMN_NAME_ID, id);
                        cv.put(TableData.TableInfo.COLUMN_NAME_NOMBRE, nombre);
                        cv.put(TableData.TableInfo.COLUMN_NAME_TELEFEONO, telf);

                        db.insert(TableData.TableInfo.TABLE_NAME_AGENDA, null, cv);
                        Log.d("Operaciones bases de datos", "Insertada una fila");

                        db.close();
                    }
                }
                else{
                    Log.d("error","else");
                }

            }
        });

        return view;
    }



}
