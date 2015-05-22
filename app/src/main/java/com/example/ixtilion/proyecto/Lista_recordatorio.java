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
    public Lista_recordatorio(){
        this.momento = "all";
    }

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lis_recordatorio);
        this.momento = getIntent().getExtras().getString("momen");
        dbOp = new DatabaseOperations(c);
        cursor = dbOp.cargarCursorRecordatorios();

        final ArrayList<Recordatorio_medicamento> recordatorios = new ArrayList<Recordatorio_medicamento>();

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(5);
                float cantidad = cursor.getFloat(3);
                String fechaIni = cursor.getString(1);
                String fechaFin = cursor.getString(2);
                int intervalo = cursor.getInt(4);
                int horaIni = cursor.getInt(6);
                int minIni =cursor.getInt(7);

                if(this.momento.compareTo("m")==0) {
                    if((((horaIni + intervalo)%24) >8) &&(((horaIni + intervalo)%24) <16)){
                        recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni, minIni));
                    }

                }

                if(this.momento.compareTo("t")==0) {
                    if(((horaIni + intervalo)%24) >16 &&((horaIni + intervalo)%24) <24){
                        recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni, minIni));
                    }

                }

                if(this.momento.compareTo("n")==0){
                    Toast.makeText(c,String.valueOf((horaIni + intervalo)%24), Toast.LENGTH_LONG).show();
                    if(((horaIni + intervalo)%24) >0 &&((horaIni + intervalo)%24) <8){
                        recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni, minIni));

                    }
                }


                if(this.momento.compareTo("all")==0) {
                    recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id,horaIni, minIni));
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
                Intent inten = new Intent(c, Anadir_recordatorio.class);
                startActivity(inten);
                finish();

            }

        });


    }
}
