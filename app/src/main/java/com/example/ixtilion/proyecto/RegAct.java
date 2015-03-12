package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by USUARIO on 12/03/2015.
 */
public class RegAct extends Fragment {
    EditText ID, NOMBRE, TELF;
    String id, nombre, telf;
    Button anadir;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.agregar_contacto, container, false);

        NOMBRE = (EditText) getView().findViewById(R.id.editTextNombre);
        TELF = (EditText) getView().findViewById(R.id.editTextTelf);
        ID = (EditText) getView().findViewById(R.id.editTextId);

        anadir = (Button) getView().findViewById(R.id.buttonAñadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = ID.getText().toString();
                nombre = NOMBRE.getText().toString();
                telf = TELF.getText().toString();
            }
        });

        return view;

    }
}
