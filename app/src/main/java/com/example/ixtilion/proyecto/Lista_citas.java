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
 * Created by USUARIO on 27/04/2015.
 */
public class Lista_citas extends Fragment {
    private DatabaseOperations dbOp;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbOp = new DatabaseOperations(container.getContext());
        cursor = dbOp.cargarCursorCitasMedico();

        final ArrayList<Cita> citas = new ArrayList<Cita>();

        if (cursor.moveToFirst()) {
            do {
                String descripcion = cursor.getString(1);
                String fecha = cursor.getString(2);
                String hora = cursor.getString(3);
                String medico = cursor.getString(0);

                citas.add(new Cita(medico, descripcion, fecha,hora));

            } while (cursor.moveToNext());
        }
        View view = inflater.inflate(R.layout.lis_citas, container, false);
        ListView list = (ListView)view.findViewById(R.id.listCitas);


        final ArrayAdapterCita adapter = new ArrayAdapterCita(view.getContext(),citas);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) view.findViewById(R.id.anadirCita);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_cita_medica() )
                        .commit();

            }

        });

        return view;


    }



}
