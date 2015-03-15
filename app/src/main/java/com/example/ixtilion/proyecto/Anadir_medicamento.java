package com.example.ixtilion.proyecto;

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
 * Created by rok on 14/03/2015.
 */
public class Anadir_medicamento extends Fragment {
    EditText NOMBRE, CANTIDAD;
    String nombre;
    int cantidad;
    Button anadir;
    Context c;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.agregar_medicamento, container, false);

        anadir = (Button) view.findViewById(R.id.btAnadirMed);
        NOMBRE = (EditText) view.findViewById(R.id.tbNomMedic);
        CANTIDAD = (EditText) view.findViewById(R.id.tbNumPasti);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = NOMBRE.getText().toString();
                cantidad = Integer.parseInt(CANTIDAD.getText().toString());

                if(c!=null) {
                    Log.d("NO error", "if");
                    DatabaseOperations dbHelper = new DatabaseOperations(c);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    if (db != null) {
                        ContentValues cv = new ContentValues();

                        cv.put(TableData.TableInfoMedic.COLUMN_NAME_NOMBRE, nombre);
                        cv.put(TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD, cantidad);

                        db.insert(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO, null, cv);
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
