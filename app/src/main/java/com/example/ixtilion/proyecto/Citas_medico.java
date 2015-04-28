package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by USUARIO on 24/04/2015.
 */
public class Citas_medico extends Fragment{

    Context c;
    CalendarView calendario;
    private DatabaseOperations dbOp;
    private Cursor cursor;
    private String fecha;

    public Citas_medico(String fecha){
        this.fecha=fecha;
    }


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

        c = container.getContext();
        View view = inflater.inflate(R.layout.citas_medicas, container, false);

        calendario = (CalendarView) view.findViewById(R.id.calendarView);
        calendario.setFirstDayOfWeek(Calendar.MONDAY);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                Toast.makeText(c, "Selected Date is\n\n"
                                + dayOfMonth + " : " + month + " : " + year,
                        Toast.LENGTH_LONG).show();
            }
        });




        return view;
    }

}
