package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
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



        anadir = (Button) view.findViewById(R.id.buttonAñadir);

        NOMBRE = (EditText)view.findViewById(R.id.editTextNombre);
        TELF = (EditText) view.findViewById(R.id.editTextTelf);
        ID = (EditText) view.findViewById(R.id.editTextId);

        anadir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                id = ID.getText().toString();
                nombre = NOMBRE.getText().toString();
                telf = TELF.getText().toString();
            }
        });

        return view;

    }
}
