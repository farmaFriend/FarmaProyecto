package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
 * Created by Alumno on 19/03/2015.
 */
public class Editar_contacto extends Fragment{
    EditText NOMBRE,TELEFONO;
    String nombre;
    String telefono;
    Button editar;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;
    private String n;
    private String pas;
    final Resources res = getResources();

    public Editar_contacto (String nombre, String telefono){
        this.n=nombre;
        this.pas=telefono;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.editar_contacto, container, false);

        editar = (Button) view.findViewById(R.id.buttonAñadir);
        NOMBRE = (EditText) view.findViewById(R.id.editTextNombre);
        TELEFONO = (EditText) view.findViewById(R.id.editTextTelf);

        NOMBRE.setText(this.n);
        TELEFONO.setText(this.pas);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) && (TELEFONO.getText().length()!=0)){
                    nombre = NOMBRE.getText().toString();
                    telefono = TELEFONO.getText().toString();

                    if(c!=null) {
                        dbOp= new DatabaseOperations(c);
                        final SQLiteDatabase db = dbOp.getWritableDatabase();

                        if (db != null) {

                            final ArrayList<Contacto> contactos = new ArrayList<Contacto>();

                            //Obtener contactos de la BD
                            cursor = dbOp.cargarCursorContactos();
                            if (cursor.moveToFirst()) {
                                do {
                                    String nombre = cursor.getString(0);
                                    String telefono = cursor.getString(1);

                                    contactos.add(new Contacto(nombre, telefono));

                                } while (cursor.moveToNext());
                            }

                            //Mirar si en la base de datos existe un contacto con ese numero
                            int i=0;
                            boolean existe=false;
                            if(pas.compareTo(telefono)!=0) {
                                while (i < contactos.size() && existe == false) {
                                    if (telefono.compareTo(contactos.get(i).getPhone()) == 0) {
                                        Toast.makeText(c, res.getString(R.string.ErrorRepe), Toast.LENGTH_LONG).show();
                                        existe = true;
                                    }
                                    i++;
                                }
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfo.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfo.COLUMN_NAME_TELEFEONO, telefono);

                                String col=TableData.TableInfo.COLUMN_NAME_TELEFEONO;
                                String val=pas;
                                String aux=col+"='"+val+"'";

                                db.update(TableData.TableInfo.TABLE_NAME_AGENDA, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA CONTACTOS
                                /*
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Agenda())
                                        .commit();
                                */
                                Intent inten = new Intent(c, Agenda.class);
                                startActivity(inten);

                                Toast.makeText(c, res.getString(R.string.Editado), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(c, res.getString(R.string.Error), Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
