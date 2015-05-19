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
public class Editar_recordatorio extends Activity {
    EditText NOMBRE, CANTIDAD, TIEMPO;
    String nombre;
    float cantidad;
    String tiempo;
    Button editar;
    private final Context c=this;
    private DatabaseOperations dbOp;
    Cursor cursor;
    private String n, pas, tiem, id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_recor_pastilla);

        this.n=getIntent().getExtras().getString("nom");
        this.pas=getIntent().getExtras().getString("pas");
        this.tiem=getIntent().getExtras().getString("tiemp");
        this.id=getIntent().getExtras().getString("id");

        final Resources res = getResources();

        editar = (Button) findViewById(R.id.btEditar);
        NOMBRE = (EditText)findViewById(R.id.TbnomMedi);
        CANTIDAD = (EditText)findViewById(R.id.tbCantidad);
        TIEMPO = (EditText) findViewById(R.id.tbTiempo);

        NOMBRE.setText(this.n);
        CANTIDAD.setText(this.pas);
        TIEMPO.setText(this.tiem);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) && (CANTIDAD.getText().length()!=0)){
                    nombre = NOMBRE.getText().toString();
                    cantidad = Float.parseFloat(CANTIDAD.getText().toString());
                    tiempo = TIEMPO.getText().toString();

                    if(c!=null) {
                        dbOp= new DatabaseOperations(c);
                        final SQLiteDatabase db = dbOp.getWritableDatabase();

                        if (db != null) {

                            final ArrayList<Recordatorio_medicamento> recordatorios = new ArrayList<Recordatorio_medicamento>();



                                ContentValues cv = new ContentValues();
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_MEDICAMENTO, nombre);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_CANTIDADTOMA, cantidad);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_INTERVALO, tiempo);

                                String col=TableData.TableInfoRecordatorio.COLUMN_NAME_ID;
                                String aux=col+"='"+id+"'";

                                db.update(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                            Intent inten = new Intent(c, Lista_recordatorio.class);
                            startActivity(inten);
                            Toast.makeText(c, res.getString(R.string.Anadido), Toast.LENGTH_LONG).show();


                            Toast.makeText(c, res.getString(R.string.Editado), Toast.LENGTH_LONG).show();
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

    }
}
