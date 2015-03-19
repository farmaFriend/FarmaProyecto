package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by USUARIO on 12/03/2015.
 */
public class Anadir_contacto extends Fragment {
    EditText ID, NOMBRE, TELF;
    String id, nombre, telf;
    Button anadir;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;


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

                if((NOMBRE.getText().length()!=0) && (TELF.getText().length()!=0)){


                    nombre = NOMBRE.getText().toString();
                    telf = TELF.getText().toString();


                    if(c!=null) {

                        Log.d("NO error","if");
                        DatabaseOperations dbHelper = new DatabaseOperations(c);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();


                        if (db != null) {
                            ContentValues cv = new ContentValues();


                            cv.put(TableData.TableInfo.COLUMN_NAME_NOMBRE, nombre);
                            cv.put(TableData.TableInfo.COLUMN_NAME_TELEFEONO, telf);


                            //Mirar si en la base de datos exite un contacto con el mismo telefono

                            dbOp = new DatabaseOperations(c);
                            cursor = dbOp.cargarCursorContactos();

                            final ArrayList<Contacto> contacts = new ArrayList<Contacto>();

                            if(cursor.moveToFirst()){
                                do{

                                    String nombre = cursor.getString(0);
                                    String telefono = cursor.getString(1);


                                    contacts.add(new Contacto(nombre, telefono));

                                }while (cursor.moveToNext());
                            }

                            int i=0;
                            boolean existe=false;
                            while(i<contacts.size()&& existe==false) {
                                if(telf.compareTo(contacts.get(i).getPhone())==0){
                                    Toast.makeText(c, "Ya existe un contacto con ese número de teléfono", Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(existe==false){
                                db.insert(TableData.TableInfo.TABLE_NAME_AGENDA, null, cv);
                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();

                                Toast.makeText(c, "Se ha añadido el contacto correctamente", Toast.LENGTH_LONG).show();


                                //CODIGO QUE MANDA A VISTA AGENDA
                                FragmentManager fm = getFragmentManager();
                                Fragment fragmento = new Agenda();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Agenda() )
                                        .commit();
                            }



                        }
                    }
                    NOMBRE.setText("");
                    TELF.setText("");
                }
                else{
                    Toast.makeText(c,"Error: Algún campo vacío", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }



}
