package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Lista_medico extends Fragment {
    private DatabaseOperations dbOp;
    Cursor cursor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbOp = new DatabaseOperations(container.getContext());
        cursor = dbOp.cargarCursorMedicamentos();

        View view = inflater.inflate(R.layout.lis_medicamento, container, false);
        return view;
    }
}
