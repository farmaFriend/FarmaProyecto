package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 27/04/2015.
 */
public class Editar_cita extends Fragment{

    private String medico, descripcion, fecha, hora;
    Context c;
    EditText MEDICO, DESCRIPCION, FECHA, HORA;
    Button editar;
    private Spinner medicos;
    private DatabaseOperations dbOp;
    Cursor cursorMedico;

    public Editar_cita (String med, String des, String f, String h){
        this.medico=med;
        this.descripcion=des;
        this.fecha=f;
        this.hora=h;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.editar_cita, container, false);

        medicos = (Spinner) view.findViewById(R.id.spMedico);

        List<String> list = new ArrayList<>();

        dbOp = new DatabaseOperations(c);
        cursorMedico = dbOp.cargarCursorMedicos();
        list.add("---Elige un médico---");

        if (cursorMedico.moveToFirst()) {
            do {
                String nombre = cursorMedico.getString(1).toUpperCase();
                String especialidad = cursorMedico.getString(2).toUpperCase();

                list.add(nombre+" - "+especialidad);

            } while (cursorMedico.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicos.setAdapter(dataAdapter);

        medicos.setSelection (dataAdapter.getPosition (medico));

        editar = (Button) view.findViewById(R.id.btEditarCita);

        DESCRIPCION =   (EditText) view.findViewById(R.id.tbDescrpcion);
        FECHA = (EditText) view.findViewById(R.id.tbFecha);
        HORA = (EditText) view.findViewById(R.id.tbHora);

        DESCRIPCION.setText(this.descripcion);
        FECHA.setText(this.fecha);
        HORA.setText(this.hora);

        return view;

    }

}
