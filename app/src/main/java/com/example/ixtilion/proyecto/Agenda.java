package com.example.ixtilion.proyecto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ixtilion.proyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ixtilion on 3/9/2015.
 */
public class Agenda extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Aqui se haria una query a la base de datos, se recorreria el puntero e iriamos construyendo y añadiendo contactos al array
        Contacto c1 = new Contacto("Pepe", "23424");
        Contacto c2 = new Contacto( "Juan", "4565");

       final ArrayList<Contacto> contacts = new ArrayList<Contacto>();
        contacts.add(c1);
        contacts.add(c2);

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
