package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ixtilion.proyecto.dummy.Comunidad;

import java.util.ArrayList;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Lista_comunidad extends Fragment {
    EditText COMUNIDAD;
    String com;
    Context c;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        c = container.getContext();



        final ArrayList<Comunidad> comu = new ArrayList<Comunidad>();
        comu.add(new Comunidad("La Rioja","https://www.riojasalud.es/ciudadanos/informacion-general-a-ciudadanos/3756-pedir-cita-previa" ,"larioja"));
        comu.add(new Comunidad("Navarra","http://www.larioja.com","navarra"));
        comu.add(new Comunidad("Cataluña","http://www.larioja.com","catalunya"));
        comu.add(new Comunidad("Castilla León","http://www.larioja.com","paisvasco"));
        comu.add(new Comunidad("Asturias","http://www.larioja.com","asturias"));


        final ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
        comunidades.add(new Comunidad("La Rioja","https://www.riojasalud.es/ciudadanos/informacion-general-a-ciudadanos/3756-pedir-cita-previa" ,"larioja"));
        comunidades.add(new Comunidad("Navarra","http://www.larioja.com","navarra"));
        comunidades.add(new Comunidad("Cataluña","http://www.larioja.com","catalunya"));
        comunidades.add(new Comunidad("Castilla León","http://www.larioja.com","paisvasco"));
        comunidades.add(new Comunidad("Asturias","http://www.larioja.com","asturias"));




        view = inflater.inflate(R.layout.solicitar_cita, container, false);

        COMUNIDAD = (EditText) view.findViewById(R.id.tbComunidad);





        ListView list = (ListView)view.findViewById(R.id.listCitas);
        final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(view.getContext(), comu);
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


        COMUNIDAD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                comu.clear();

                for (int i=0; i<comunidades.size(); i++){
                    String c1 = comunidades.get(i).getName().toUpperCase();
                    String c2 = COMUNIDAD.getText().toString().toUpperCase();
                    if(c1.indexOf(c2)!=-1){
                        comu.add(new Comunidad(comunidades.get(i).getName(),comunidades.get(i).getLink() ,comunidades.get(i).getIco()));
                    }
                }


                ListView list = (ListView)view.findViewById(R.id.listCitas);
                final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(view.getContext(), comu);
                list.setAdapter(adapter);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        return view;
    }

}
