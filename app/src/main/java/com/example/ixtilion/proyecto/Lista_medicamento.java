package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by rok on 15/03/2015.
 */
public class Lista_medicamento extends Fragment {
    private DatabaseOperations dbOp;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbOp = new DatabaseOperations(container.getContext());
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

        String tam = String.valueOf(medicamentos.size());
        Log.d("tamaño", tam);

        View view = inflater.inflate(R.layout.lis_medicamento, container, false);
        ListView list = (ListView)view.findViewById(R.id.list_medic);

        final ArrayAdapterMedicamento adapter = new ArrayAdapterMedicamento(view.getContext(),medicamentos);
        list.setAdapter(adapter);

        return view;
    }
}
