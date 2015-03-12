package com.example.ixtilion.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Contacto> {
    private final Context context;
    private final ArrayList<Contacto> contactos;
    public CustomArrayAdapter(Context context, ArrayList<Contacto> contactos) {
        super(context, R.layout.linea, contactos);
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.firstLine);
        TextView linea2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        linea1.setText(contactos.get(position).getName());
        linea2.setText(contactos.get(position).getPhone());
        imageView.setImageResource(R.drawable.agenda);

        return rowView;
    }
}
