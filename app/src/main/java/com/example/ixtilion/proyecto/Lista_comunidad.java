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

import com.example.ixtilion.proyecto.dummy.Comunidad;

import java.util.ArrayList;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Lista_comunidad extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
        comunidades.add(new Comunidad("La Rioja","www.larioja.com" ,"larioja"));
        comunidades.add(new Comunidad("Navarra","www","navarra"));
        comunidades.add(new Comunidad("Cataluña","www","catalunya"));
        comunidades.add(new Comunidad("Castilla León","www","castillaleon"));
        comunidades.add(new Comunidad("Pais Vasco","www","paisvasco"));
        comunidades.add(new Comunidad("Asturias","www","asturias"));




        View view = inflater.inflate(R.layout.solicitar_cita, container, false);
        ListView list = (ListView)view.findViewById(R.id.listCitas);
        final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(view.getContext(), comunidades);
        list.setAdapter(adapter);

        return view;
    }

}
