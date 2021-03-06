package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by USUARIO on 27/04/2015.
 */
public class Lista_citas extends Activity {
    private DatabaseOperations dbOp;
    Cursor cursor;
    private final Context context = this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lis_citas);
        dbOp = new DatabaseOperations(context);
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

        ListView list = (ListView)findViewById(R.id.listCitas);


        //Borrar citas pasadas
        Date fA = new Date();
        DateFormat df =  DateFormat.getDateInstance();
        String fechAct = df.format(fA);

        int dia, diaux, mes, mesaux,anio, anioaux;
        StringTokenizer st,staux;
        int auxil=0;
        for(int i=0; i<citas.size();i++){
            String f = citas.get(auxil).getFecha();
            st=new StringTokenizer(fechAct, "/");
            staux=new StringTokenizer(f, "/");

            dia=Integer.parseInt(st.nextToken());
            diaux=Integer.parseInt(staux.nextToken());
            mes=Integer.parseInt(st.nextToken());
            mesaux=Integer.parseInt(staux.nextToken());
            anio=Integer.parseInt(st.nextToken());
            anioaux=Integer.parseInt(staux.nextToken());

            if(anioaux<anio){
                citas.remove(auxil);
                auxil--;
            }
            else if(anioaux==anio){
                if(mesaux<mes){
                    citas.remove(auxil);
                    auxil--;
                }
                else  if(mesaux==mes){
                    if(diaux<dia){
                        citas.remove(auxil);
                        auxil--;
                    }
                }
            }
            auxil++;
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

        final ArrayAdapterCita adapter = new ArrayAdapterCita(context,citas);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) findViewById(R.id.anadirCita);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_cita_medica() )
                        .commit();*/
                Intent inten = new Intent(context, Anadir_cita_medica.class);
                startActivity(inten);
                finish();
            }
        });

    }



}
