package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
public class Anadir_medicamento extends Activity {
    EditText NOMBRE, CANTIDAD;
    String nombre;
    float cantidad;
    Button anadir;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;
    private final Context context = this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_medicamento);

        anadir = (Button) findViewById(R.id.btAnadirMed);
        NOMBRE = (EditText) findViewById(R.id.tbNomMedic);
        CANTIDAD = (EditText) findViewById(R.id.tbNumPasti);
        final Resources res = getResources();

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) && (CANTIDAD.getText().length()!=0)){
                    nombre = NOMBRE.getText().toString();
                    cantidad = Float.parseFloat(CANTIDAD.getText().toString());

                    if(context!=null) {
                        Log.d("NO error", "if");
                        dbOp = new DatabaseOperations(context);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                        if (db != null) {
                            //Mirar si en la base de datos existe un medicamento con ese nombre

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
                                    Toast.makeText(context, res.getString(R.string.ErrorRepe), Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfoMedic.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD, cantidad);

                                db.insert(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO, null, cv);
                                Log.d("Operaciones bases datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA MEDICAMENTOS
                              /*  FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_medicamento())
                                        .commit();*/
                                Intent inten = new Intent(context, Lista_medicamento.class);
                                startActivity(inten);

                                Toast.makeText(context, res.getString(R.string.Añadido), Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(context, res.getString(R.string.Error), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
