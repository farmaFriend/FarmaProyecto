package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
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
 * Created by Alumno on 26/03/2015.
 */
public class Anadir_medico extends Fragment{
    EditText NOMBRE, ESPECIALIDAD, DIRECCION;
    String nombre, especialidad, direccion,id;
    Button anadir;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;
    final Resources res = getResources();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.agregar_medico, container, false);

        anadir = (Button) view.findViewById(R.id.btAgregarMedico);
        NOMBRE = (EditText) view.findViewById(R.id.tbNombreMedico);
        ESPECIALIDAD = (EditText) view.findViewById(R.id.tbEspecialidad);
        DIRECCION = (EditText) view.findViewById(R.id.tbDireccion);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) &&
                   (ESPECIALIDAD.getText().length()!=0)){


                    nombre = NOMBRE.getText().toString();
                    especialidad = ESPECIALIDAD.getText().toString();
                    direccion = DIRECCION.getText().toString();

                    if(c!=null) {
                        Log.d("NO error", "if");
                        dbOp = new DatabaseOperations(c);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                        id=nombre+especialidad;

                        if (db != null) {

                            //Mirar si en la base de datos existe un medico con ese nombre y especialidad

                            cursor = dbOp.cargarCursorMedicos();


                            final ArrayList<Medico> medicos = new ArrayList<Medico>();

                            if (cursor.moveToFirst()) {
                                do {
                                    String id = cursor.getString(0);
                                    String nombre = cursor.getString(1);
                                    String especialidad = cursor.getString(2);
                                    String direccion = cursor.getString(3);

                                    medicos.add(new Medico(nombre,especialidad, direccion, id));

                                } while (cursor.moveToNext());
                            }

                            int i=0;
                            boolean existe=false;
                            while(i<medicos.size()&& existe==false) {
                                if(id.compareTo(medicos.get(i).getId())==0){
                                    Toast.makeText(c, res.getString(R.string.ErrorMed), Toast.LENGTH_LONG).show();                                    existe=true;
                                }
                                i++;
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_ID, id);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_DIRECCION, direccion);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_ESPECIALIDAD, especialidad);

                                db.insert(TableData.TableInfoMedico.TABLE_NAME_MEDICO, null, cv);

                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA MEDICOS
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_medico())
                                        .commit();

                                Toast.makeText(c, res.getString(R.string.Añadido), Toast.LENGTH_LONG).show();                                    existe=true;

                            }

                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(c, res.getString(R.string.Error), Toast.LENGTH_LONG).show();                                                 }
            }
        });

        return view;
    }
}
