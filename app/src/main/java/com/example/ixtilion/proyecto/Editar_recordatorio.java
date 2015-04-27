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
 * Created by Alumno on 19/03/2015.
 */
public class Editar_recordatorio extends Fragment {
    EditText NOMBRE, CANTIDAD, TIEMPO;
    int id;
    String nombre;
    float cantidad;
    String tiempo;
    Button editar;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;
    private String n;
    private String pas;
    private String tiem;

    public Editar_recordatorio(String n, String pas, String tiempo, int id){
        this.n=n;
        this.pas=pas;
        this.tiem = tiempo;
        this.id = id;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.editar_recor_pastilla, container, false);

        editar = (Button) view.findViewById(R.id.btEditar);
        NOMBRE = (EditText) view.findViewById(R.id.TbnomMedi);
        CANTIDAD = (EditText) view.findViewById(R.id.tbCantidad);
        TIEMPO = (EditText) view.findViewById(R.id.tbTiempo);

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
                                String val=String.valueOf(id);
                                String aux=col+"='"+val+"'";

                                db.update(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA recordatorios
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_recordatorio("all"))
                                        .commit();

                                Toast.makeText(c, "Recordatorio editado correctamente", Toast.LENGTH_LONG).show();
                            }

                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(c,"Error: Algún campo vacío", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
