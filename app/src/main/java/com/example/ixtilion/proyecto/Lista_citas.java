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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
                String id = cursor.getString(4);

                citas.add(new Cita(medico, descripcion, fecha,hora,id));

            } while (cursor.moveToNext());
        }
        View view = inflater.inflate(R.layout.lis_citas, container, false);
        ListView list = (ListView)view.findViewById(R.id.listCitas);


        //Borrar citas pasadas
        Date fA = new Date();
        DateFormat df =  DateFormat.getDateInstance();
        String fechAct = df.format(fA);

        Long l = Date.parse(fechAct);
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTimeInMillis(l);


        for(int i=0; i<citas.size();i++){
            String f = citas.get(i).getFecha();
            Long l2 = Date.parse(f);
            Calendar fecha = Calendar.getInstance();
            fecha.setTimeInMillis(l2);
            if((fechaActual.after(fecha))){
                citas.remove(i);
            }
        }



        int i,j;
        Cita aux;
        for (i=0; i<citas.size()-1; i++){
            for(j=0 ;j <citas.size()-i-1 ; j++){
                String f1 = citas.get(j).getFecha();
                String f2 = citas.get(j+1).getFecha();

                Long l1 = Date.parse(f1);
                Calendar fech1 = Calendar.getInstance();
                fech1.setTimeInMillis(l1);

                Long l2 = Date.parse(f2);
                Calendar fech2 = Calendar.getInstance();
                fech2.setTimeInMillis(l2);


                if(fech1.after(fech2)){
                    aux = citas.get(j+1);
                    citas.set(j+1,citas.get(j));
                    citas.set(j,aux);
                }
            }
        }

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
