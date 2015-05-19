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
public class Editar_medicamento extends Activity {
    EditText NOMBRE, CANTIDAD;
    String nombre;
    float cantidad;
    Button editar;
    private final Context c = this;
    private DatabaseOperations dbOp;
    Cursor cursor;
    private String n;
    private String pas;
    private final Context context = this;

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_medicamento);

        editar = (Button) findViewById(R.id.bEdiMedi);
        NOMBRE = (EditText)findViewById(R.id.tbNomEdiMedi);
        CANTIDAD = (EditText)findViewById(R.id.tbPasEdiMedi);

        this.n=getIntent().getExtras().getString("nom");
        this.pas=getIntent().getExtras().getString("pas");

        final Resources res = getResources();

        NOMBRE.setText(this.n);
        CANTIDAD.setText(this.pas);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) && (CANTIDAD.getText().length()!=0)){
                    nombre = NOMBRE.getText().toString();
                    cantidad = Float.parseFloat(CANTIDAD.getText().toString());

                    if(c!=null) {
                        dbOp= new DatabaseOperations(c);
                        final SQLiteDatabase db = dbOp.getWritableDatabase();

                        if (db != null) {

                            final ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();

                            //Obtener medicamentos de la BD
                            cursor = dbOp.cargarCursorMedicamentos();
                            if (cursor.moveToFirst()) {
                                do {
                                    String nombre = cursor.getString(0);
                                    float cantidad = cursor.getFloat(1);

                                    medicamentos.add(new Medicamento(nombre, cantidad));

                                } while (cursor.moveToNext());
                            }

                            //Mirar si en la base de datos existe un medicamento con ese nombre
                            int i=0;
                            boolean existe=false;
                            if(n.compareTo(nombre)!=0) {
                                while (i < medicamentos.size() && existe == false) {
                                    if (nombre.compareTo(medicamentos.get(i).getNombre()) == 0) {
                                        Toast.makeText(context, res.getString(R.string.ErrorRepe), Toast.LENGTH_LONG).show();
                                        existe = true;
                                    }
                                    i++;
                                }
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfoMedic.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD, cantidad);

                                String col=TableData.TableInfoMedic.COLUMN_NAME_NOMBRE;
                                String val=n;
                                String aux=col+"='"+val+"'";

                                db.update(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA MEDICAMENTOS
                                /*FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_medicamento())
                                        .commit();*/

                                Intent inten = new Intent(context, Lista_medicamento.class);
                                startActivity(inten);

                                Toast.makeText(context, res.getString(R.string.Editado), Toast.LENGTH_LONG).show();
                                finish();
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
