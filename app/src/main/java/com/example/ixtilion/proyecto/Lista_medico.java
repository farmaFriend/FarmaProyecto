package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Lista_medico extends Activity {
    private final Context context = this;
    private DatabaseOperations dbOp;
    Cursor cursor;

    public  void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lis_medico);

        dbOp = new DatabaseOperations(context);
        cursor = dbOp.cargarCursorMedicos();

        final ArrayList<Medico> medicos = new ArrayList<Medico>();

        if (cursor.moveToFirst()) {
            do {
                String id=cursor.getString(0);
                String nombre = cursor.getString(1);
                String especialidad = cursor.getString(2);
                String direccion = cursor.getString(3);

                medicos.add(new Medico(nombre,especialidad,direccion,id));

            } while (cursor.moveToNext());
        }

        ListView list = (ListView)findViewById(R.id.list_medicos);
        final ArrayAdapterMedico adapter = new ArrayAdapterMedico(context,medicos);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) findViewById(R.id.anadirMedico);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_medico() )
                        .commit();*/
                Intent inten = new Intent(context, Anadir_medico.class);
                startActivity(inten);

            }

        });
    }
}
