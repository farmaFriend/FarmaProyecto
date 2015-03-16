package com.example.ixtilion.proyecto;

import android.app.Fragment;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
        Contacto c;

        final ArrayList<Contacto> contacts = new ArrayList<Contacto>();

        if(cursor.moveToFirst()){
            do{
                String nombre = cursor.getString(0);
                String telefono = cursor.getString(1);


                contacts.add(new Contacto(nombre, telefono));

            }while (cursor.moveToNext());
        }

        String tam = String.valueOf(contacts.size());
        Log.d("tamaño",tam);




        //Aqui se haria una query a la base de datos, se recorreria el puntero e iriamos construyendo y añadiendo contactos al array



        View view = inflater.inflate(R.layout.lay_agenda, container, false);
        ListView list = (ListView)view.findViewById(R.id.listView);

        final CustomArrayAdapter adapter = new CustomArrayAdapter(view.getContext(),contacts);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Contacto item = (Contacto) parent.getItemAtPosition(position);
                String tfno = item.getPhone();
                String url = "tel:"+ tfno;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                getActivity().startActivity(intent);

                //view.animate().setDuration(2000).alpha(0)
                //     .withEndAction(new Runnable() {
                ////          public void run() {
                //             contacts.remove(item);
                //           adapter.notifyDataSetChanged();
                //         view.setAlpha(1);
                //   }
                //});
            }

        });



        return view;
    }

}
