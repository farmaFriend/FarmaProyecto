package com.example.ixtilion.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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
                Intent inten = new Intent(c, Lista_recordatorio.class);
                inten.putExtra("m","m");
                startActivity(inten);
            }
        });

        tarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(c, Lista_recordatorio.class);
                inten.putExtra("t","t");
                startActivity(inten);
            }
        });

        noche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(c, Lista_recordatorio.class);
                inten.putExtra("n","n");
                startActivity(inten);
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(c, Anadir_recordatorio.class);
                startActivity(inten); }
        });

        return view;
    }
}
