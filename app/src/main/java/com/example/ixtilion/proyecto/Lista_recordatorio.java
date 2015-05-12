package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rok on 15/03/2015.
 */
public class Lista_recordatorio extends Activity {
    private DatabaseOperations dbOp;
    Cursor cursor;
    String momento;
    private final Context c = this;
    public Lista_recordatorio(String m){
        this.momento = m;
    }

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lis_recordatorio);
        dbOp = new DatabaseOperations(c);
        cursor = dbOp.cargarCursorRecordatorios();

        final ArrayList<Recordatorio_medicamento> recordatorios = new ArrayList<Recordatorio_medicamento>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(8);
                float cantidad = cursor.getFloat(4);
                String fechaIni = cursor.getString(2);
                String fechaFin = cursor.getString(3);
                int intervalo = cursor.getInt(5);
                int horaIni = cursor.getInt(9);

                if(this.momento =="m") {
                    if(((horaIni + intervalo)%24) >0 &&((horaIni + intervalo)%24) <8){recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni));}
                   if(intervalo <=8) {
                       if (((horaIni + intervalo * 2) % 24) > 0 && ((horaIni + intervalo * 2) % 24) < 8) {
                           recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni));
                       }

                       if (((horaIni + intervalo * 3) % 24) > 0 && ((horaIni + intervalo * 3) % 24) < 8) {
                           recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni));
                       }
                   }
                }

                if(this.momento =="t") {
                    if(((horaIni + intervalo)%24) >8 &&((horaIni + intervalo)%24) <16){recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni));}
                    if(intervalo <=8) {
                        if (((horaIni + intervalo * 2) % 24) > 8 && ((horaIni + intervalo * 2) % 24) < 16) {
                            recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni));
                        }

                        if (((horaIni + intervalo * 3) % 24) > 8 && ((horaIni + intervalo * 3) % 24) < 16) {
                            recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni));
                        }
                    }
                }

                if(this.momento =="n"){
                    if(((horaIni + intervalo)%24) >16 &&((horaIni + intervalo)%24) <24){recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni));}
                }
                if(intervalo <=8) {
                    if (((horaIni + intervalo * 2) % 24) > 16 && ((horaIni + intervalo * 2) % 24) < 8) {
                        recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni));
                    }

                    if (((horaIni + intervalo * 3) % 24) > 16 && ((horaIni + intervalo * 3) % 24) < 8) {
                        recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni));
                    }
                }

                if(this.momento =="all") {
                    recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni));
                }

            } while (cursor.moveToNext());
        }

        ListView list = (ListView)findViewById(R.id.list_rec);

        final ArrayAdapterRecordatorio adapter = new ArrayAdapterRecordatorio(c, recordatorios);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) findViewById(R.id.anadirRecordatorio);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_recordatorio() )
                        .commit();*/
                Intent inten = new Intent(c, Lista_medico.class);
                startActivity(inten);

            }

        });


    }
}
