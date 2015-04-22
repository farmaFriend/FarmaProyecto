package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        comunidades.add(new Comunidad("La Rioja","http://www.larioja.com" ,"larioja"));
        comunidades.add(new Comunidad("Navarra","http://www.larioja.com","navarra"));
        comunidades.add(new Comunidad("Cataluña","http://www.larioja.com","catalunya"));
        comunidades.add(new Comunidad("Castilla León","http://www.larioja.com","paisvasco"));
        comunidades.add(new Comunidad("Asturias","http://www.larioja.com","asturias"));




        View view = inflater.inflate(R.layout.solicitar_cita, container, false);
        ListView list = (ListView)view.findViewById(R.id.listCitas);
        final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(view.getContext(), comunidades);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Comunidad item = (Comunidad) parent.getItemAtPosition(position);
                String link = item.getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
