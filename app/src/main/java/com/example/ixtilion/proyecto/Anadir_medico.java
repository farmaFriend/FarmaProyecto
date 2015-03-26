package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
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
    EditText NOMBRE, ESPECIALIDAD, DIRECCION, TELEFONO;
    String nombre, especialidad, direccion,telefono,id;
    Button anadir;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.agregar_medico, container, false);

        anadir = (Button) view.findViewById(R.id.btAgregarMedico);
        NOMBRE = (EditText) view.findViewById(R.id.tbNombreMedico);
        ESPECIALIDAD = (EditText) view.findViewById(R.id.tbEspecialidad);
        DIRECCION = (EditText) view.findViewById(R.id.tbDireccion);
        TELEFONO = (EditText) view.findViewById(R.id.tbTelf);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) &&
                   (ESPECIALIDAD.getText().length()!=0)){


                    nombre = NOMBRE.getText().toString();
                    especialidad = ESPECIALIDAD.getText().toString();
                    direccion = DIRECCION.getText().toString();
                    telefono = TELEFONO.getText().toString();

                    if(c!=null) {
                        Log.d("NO error", "if");
                        dbOp = new DatabaseOperations(c);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                        id=nombre+especialidad;

                        if (db != null) {

                            if((TELEFONO.getText().length()!=0)){
                                cursor = dbOp.cargarCursorContactos();
                                Contacto c;

                                final ArrayList<Contacto> cont = new ArrayList<Contacto>();

                                if (cursor.moveToFirst()) {
                                    do {
                                        String nombre = cursor.getString(0);
                                        String telefono = cursor.getString(1);

                                        cont.add(new Contacto(nombre, telefono));

                                    } while (cursor.moveToNext());
                                }

                                int i=0;
                                boolean existe=false;
                                while(i<cont.size()&& existe==false) {
                                    if(telefono.compareTo(cont.get(i).getPhone())==0){
                                        existe=true;
                                    }
                                    i++;
                                }
                                if(!existe){
                                    ContentValues cv = new ContentValues();

                                    cv.put(TableData.TableInfo.COLUMN_NAME_NOMBRE, nombre);
                                    cv.put(TableData.TableInfo.COLUMN_NAME_TELEFEONO, telefono);


                                    db.insert(TableData.TableInfo.TABLE_NAME_AGENDA, null, cv);
                                    Log.d("Operaciones bases de datos", "Insertada una fila");

                                }

                            }

                            //Mirar si en la base de datos existe un medico con ese nombre y especialidad

                            cursor = dbOp.cargarCursorMedicos();
                            Medico m;

                            final ArrayList<Medico> medicos = new ArrayList<Medico>();

                            if (cursor.moveToFirst()) {
                                do {
                                    String id = cursor.getString(0);
                                    String nombre = cursor.getString(1);
                                    String especialidad = cursor.getString(2);
                                    String direccion = cursor.getString(3);
                                    String telefono = cursor.getString(4);

                                    medicos.add(new Medico(nombre,especialidad, direccion, telefono, id));

                                } while (cursor.moveToNext());
                            }

                            int i=0;
                            boolean existe=false;
                            while(i<medicos.size()&& existe==false) {
                                if(id.compareTo(medicos.get(i).getId())==0){
                                    Toast.makeText(c, "Ya existe un medico con el mismo nombre y especialidad", Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_ID, id);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_DIRECCION, direccion);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_ESPECIALIDAD, especialidad);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_TELEFONO, telefono);

                                db.insert(TableData.TableInfoMedico.TABLE_NAME_MEDICO, null, cv);

                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA MEDICOS
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_medico())
                                        .commit();
                                
                                Toast.makeText(c, "Medicamento añadido correctamente", Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(c,"Error: El campo nombre o especialidad esta vacío", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
