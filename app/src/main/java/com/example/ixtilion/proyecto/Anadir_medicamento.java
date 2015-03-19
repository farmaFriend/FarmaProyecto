package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rok on 14/03/2015.
 */
public class Anadir_medicamento extends Fragment {
    EditText NOMBRE, CANTIDAD;
    String nombre;
    float cantidad;
    Button anadir;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.agregar_medicamento, container, false);

        anadir = (Button) view.findViewById(R.id.btAnadirMed);
        NOMBRE = (EditText) view.findViewById(R.id.tbNomMedic);
        CANTIDAD = (EditText) view.findViewById(R.id.tbNumPasti);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) && (CANTIDAD.getText().length()!=0)){
                    nombre = NOMBRE.getText().toString();
                    cantidad = Float.parseFloat(CANTIDAD.getText().toString());

                    if(c!=null) {
                        Log.d("NO error", "if");
                        DatabaseOperations dbHelper = new DatabaseOperations(c);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();

                        if (db != null) {
                            ContentValues cv = new ContentValues();

                            cv.put(TableData.TableInfoMedic.COLUMN_NAME_NOMBRE, nombre);
                            cv.put(TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD, cantidad);


                            //Mirar si en la base de datos exite un medicamento con ese nombre

                            dbOp = new DatabaseOperations(c);
                            cursor = dbOp.cargarCursorMedicamentos();
                            Medicamento m;

                            final ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();

                            if (cursor.moveToFirst()) {
                                do {
                                    String nombre = cursor.getString(0);
                                    float cantidad = cursor.getFloat(1);

                                    medicamentos.add(new Medicamento(nombre, cantidad));

                                } while (cursor.moveToNext());
                            }

                            int i=0;
                            boolean existe=false;
                            while(i<medicamentos.size()&& existe==false) {
                                if(nombre.compareTo(medicamentos.get(i).getNombre())==0){
                                    Toast.makeText(c, "Ya existe un medicamento con ese nombre", Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(existe==false){
                                db.insert(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO, null, cv);
                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA MEDICAMENTOS
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_medicamento())
                                        .commit();

                                Toast.makeText(c, "Medicamento añadido correctamente", Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                    NOMBRE.setText("");
                    CANTIDAD.setText("");
                }
                else{
                    Toast.makeText(c,"Error: Algún campo vacío", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
