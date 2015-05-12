package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
public class Anadir_contacto extends Activity{
    EditText ID, NOMBRE, TELF;
    String id, nombre, telf;
    Button anadir;
    private final Context context = this;
    private DatabaseOperations dbOp;
    Cursor cursor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_contacto);

        anadir = (Button)findViewById(R.id.buttonAñadir);

        NOMBRE = (EditText)findViewById(R.id.editTextNombre);
        TELF = (EditText)findViewById(R.id.editTextTelf);
        final Resources res = getResources();

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) && (TELF.getText().length()!=0)){

                    nombre = NOMBRE.getText().toString();
                    telf = TELF.getText().toString();

                    if(context!=null) {

                        Log.d("NO error","if");
                        dbOp = new DatabaseOperations(context);
                        SQLiteDatabase db = dbOp.getWritableDatabase();


                        if (db != null) {

                            //Mirar si existen dos contactos con el mismo numero de telefono
                            cursor = dbOp.cargarCursorContactos();

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
                                if(telf.compareTo(cont.get(i).getPhone())==0){
                                    Toast.makeText(context, res.getString(R.string.ErrorRepe), Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfo.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfo.COLUMN_NAME_TELEFEONO, telf);


                                db.insert(TableData.TableInfo.TABLE_NAME_AGENDA, null, cv);
                                Log.d("Operaciones bases datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA AGENDA
                                /*
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Agenda() )
                                        .commit();
                                */
                                Intent inten = new Intent(context, Agenda.class);
                                startActivity(inten);

                                Toast.makeText(context, res.getString(R.string.Añadido), Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
                else{
                    Toast.makeText(context, res.getString(R.string.Error), Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
