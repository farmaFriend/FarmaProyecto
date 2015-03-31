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
 * Created by rok on 15/03/2015.
 */
public class Lista_recordatorio extends Fragment {
    private DatabaseOperations dbOp;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbOp = new DatabaseOperations(container.getContext());
        cursor = dbOp.cargarCursorRecordatorios();

        final ArrayList<Recordatorio_medicamento> recordatorios = new ArrayList<Recordatorio_medicamento>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(9);
                float cantidad = cursor.getFloat(4);
                String fechaIni = cursor.getString(2);
                String fechaFin = cursor.getString(3);
                int intervalo = cursor.getInt(5);


                recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id));

            } while (cursor.moveToNext());
        }

        View view = inflater.inflate(R.layout.lis_recordatorio, container, false);
        ListView list = (ListView)view.findViewById(R.id.list_rec);

        final ArrayAdapterRecordatorio adapter = new ArrayAdapterRecordatorio(view.getContext(), recordatorios);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) view.findViewById(R.id.anadirRecordatorio);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_recordatorio() )
                        .commit();

            }

        });

        return view;
    }
}
