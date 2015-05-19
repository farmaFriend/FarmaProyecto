package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by rok on 15/03/2015.
 */
public class Lista_medicamento extends Activity {
    private DatabaseOperations dbOp;
    Cursor cursor;
    private final Context context=this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lis_medicamento);
        dbOp = new DatabaseOperations(this);
        cursor = dbOp.cargarCursorMedicamentos();

        final ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                float cantidad = cursor.getFloat(1);

                medicamentos.add(new Medicamento(nombre, cantidad));

            } while (cursor.moveToNext());
        }


        ListView list = (ListView)findViewById(R.id.list_medic);

        final ArrayAdapterMedicamento adapter = new ArrayAdapterMedicamento(this,medicamentos);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) findViewById(R.id.anadirMedicamento);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent inten = new Intent(context, Anadir_medicamento.class);
                startActivity(inten);
                finish();
            }

        });
    }
}
