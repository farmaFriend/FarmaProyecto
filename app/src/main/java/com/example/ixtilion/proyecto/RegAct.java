package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by USUARIO on 12/03/2015.
 */
public class RegAct extends Activity {
    EditText ID, NOMBRE, TELF;
    String id, nombre, telf;
    Button anadir;


    protected void onCreate (Bundle b){
        super.onCreate(b);
        setContentView(R.layout.agregar_contacto);

        NOMBRE = (EditText) findViewById(R.id.editTextNombre);
        TELF = (EditText) findViewById(R.id.editTextTelf);
        ID = (EditText) findViewById(R.id.editTextId);

        anadir = (Button) findViewById(R.id.buttonAñadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = ID.getText().toString();
                nombre = NOMBRE.getText().toString();
                telf = TELF.getText().toString();
            }
        });

    }
}
