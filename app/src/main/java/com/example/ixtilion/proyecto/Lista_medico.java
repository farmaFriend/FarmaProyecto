package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
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
public class Lista_medico extends Fragment {
    private DatabaseOperations dbOp;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbOp = new DatabaseOperations(container.getContext());
        cursor = dbOp.cargarCursorMedicos();

        final ArrayList<Medico> medicos = new ArrayList<Medico>();

        if (cursor.moveToFirst()) {
            do {
                String id=cursor.getString(0);
                String nombre = cursor.getString(1);
                String especialidad = cursor.getString(2);
                String direccion = cursor.getString(3);
                String tlfn = cursor.getString(4);

                medicos.add(new Medico(nombre,especialidad,direccion,tlfn,id));

            } while (cursor.moveToNext());
        }

        View view = inflater.inflate(R.layout.lis_medico, container, false);
        ListView list = (ListView)view.findViewById(R.id.list_medicos);

        final ArrayAdapterMedico adapter = new ArrayAdapterMedico(view.getContext(),medicos);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) view.findViewById(R.id.anadirMedico);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_medico() )
                        .commit();

            }

        });


        return view;
    }
}
