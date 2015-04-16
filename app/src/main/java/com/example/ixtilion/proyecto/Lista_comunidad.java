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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Lista_comunidad extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.solicitar_cita, container, false);
        ListView list = (ListView)view.findViewById(R.id.listCitas);
        final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(view.getContext());
        list.setAdapter(adapter);
        ((TextView)list.getChildAt(0).findViewById(R.id.textComunidad)).setText("La Rioja");
        ((TextView)list.getChildAt(1).findViewById(R.id.textComunidad)).setText("Navarra");
        ((TextView)list.getChildAt(2).findViewById(R.id.textComunidad)).setText("Santander");
        ((TextView)list.getChildAt(3).findViewById(R.id.textComunidad)).setText("Madrid");
        ((TextView)list.getChildAt(4).findViewById(R.id.textComunidad)).setText("Barcelona");

        return view;
    }
}
