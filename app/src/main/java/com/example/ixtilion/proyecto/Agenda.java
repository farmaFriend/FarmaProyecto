package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USUARIO on 13/03/2015.
 */
public class Agenda extends Fragment{

    private DatabaseOperations dbOp;
    Cursor cursor;
    SimpleCursorAdapter adap;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        dbOp = new DatabaseOperations(container.getContext());
        cursor = dbOp.cargarCursorContactos();

        //Aqui se haria una query a la base de datos, se recorreria el puntero e iriamos construyendo y añadiendo contactos al array
       // Contacto c1 = new Contacto("Pepe", "23424");
        // Contacto c2 = new Contacto( "Juan", "3356");

        final ArrayList<Contacto> contacts = new ArrayList<Contacto>();

        //contacts.add(c1);
        //contacts.add(c2);

        if(cursor.moveToFirst()){
            do{

                String nombre = cursor.getString(0);
                String telefono = cursor.getString(1);


                contacts.add(new Contacto(nombre, telefono));

            }while (cursor.moveToNext());
        }

        View view = inflater.inflate(R.layout.lay_agenda, container, false);
        ListView list = (ListView)view.findViewById(R.id.listView);
        final CustomArrayAdapter adapter = new CustomArrayAdapter(view.getContext(),contacts);
        list.setAdapter(adapter);
        View imageButton = (ImageButton) view.findViewById(R.id.anadirContacto);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                Fragment fragmento = new Agenda();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_contacto() )
                        .commit();

            }

        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Contacto item = (Contacto) parent.getItemAtPosition(position);
                String tfno = item.getPhone();
                String url = "tel:"+ tfno;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}
