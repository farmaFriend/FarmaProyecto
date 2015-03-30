package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by rok on 30/03/2015.
 */
public class Principal extends Fragment {
    Context c;
    ImageView manana, tarde, noche, mas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.principal_pastillero, container, false);
        manana= (ImageView) view.findViewById(R.id.manana);
        tarde= (ImageView) view.findViewById(R.id.tarde);
        noche= (ImageView) view.findViewById(R.id.noche);
        mas=(ImageView) view.findViewById(R.id.imMas);

        manana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Mañana ha sido pulsado", Toast.LENGTH_LONG).show();
            }
        });

        tarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Tarde ha sido pulsado", Toast.LENGTH_LONG).show();
            }
        });

        noche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Noche ha sido pulsado", Toast.LENGTH_LONG).show();
            }
        });
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Quieres añadir un recordatorio", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}