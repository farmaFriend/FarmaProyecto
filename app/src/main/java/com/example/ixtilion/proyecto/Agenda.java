package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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
public class Agenda extends Activity{

    private DatabaseOperations dbOp;
    private final Context context=this;
    Cursor cursor;
    SimpleCursorAdapter adap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_agenda);

        dbOp = new DatabaseOperations(this);
        cursor = dbOp.cargarCursorContactos();

        final ArrayList<Contacto> contacts = new ArrayList<Contacto>();

        if(cursor.moveToFirst()){
            do{

                String nombre = cursor.getString(0);
                String telefono = cursor.getString(1);


                contacts.add(new Contacto(nombre, telefono));

            }while (cursor.moveToNext());
        }

        ListView list = (ListView)findViewById(R.id.listView);
        final CustomArrayAdapter adapter = new CustomArrayAdapter(this,contacts);
        list.setAdapter(adapter);
        View imageButton = (ImageButton)findViewById(R.id.anadirContacto);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Anadir_contacto() )
                        .commit();*/
                Intent inten = new Intent(context, Anadir_contacto.class);
                startActivity(inten);

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
                startActivity(intent);
            }
        });

    }

}
