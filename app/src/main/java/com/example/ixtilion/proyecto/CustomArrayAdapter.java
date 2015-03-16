package com.example.ixtilion.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        final Contacto rowItem= getItem(position);
        TextView linea1 = (TextView) rowView.findViewById(R.id.firstLine);
        TextView linea2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.imageView2);

        linea1.setText(contactos.get(position).getName());
        linea2.setText(contactos.get(position).getPhone());
        imageView.setImageResource(R.drawable.agenda);
        imageView2.setImageResource(R.drawable.delete1);

        imageView2.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
         Toast.makeText(v.getContext(), "borrar", Toast.LENGTH_SHORT).show();
            contactos.remove(rowItem);
            // here we refresh the adapter
            CustomArrayAdapter.this.notifyDataSetChanged();
        }
        });

        return rowView;
    }
}
