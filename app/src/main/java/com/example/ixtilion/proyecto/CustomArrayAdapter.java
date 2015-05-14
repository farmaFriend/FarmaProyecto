package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
        final int pos = position;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea, parent, false);

        final Contacto rowItem= getItem(position);
        final String nom=contactos.get(position).getName();
        final String tfno=contactos.get(position).getPhone();
        TextView linea1 = (TextView) rowView.findViewById(R.id.firstLine);
        TextView linea2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) rowView.findViewById(R.id.ImModificar);

        linea1.setText(contactos.get(position).getName());
        linea2.setText(contactos.get(position).getPhone());
        imageView.setImageResource(R.drawable.personal);

        imageView2.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            DatabaseOperations dbHelper = new DatabaseOperations(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (db != null) {
                String col=TableData.TableInfo.COLUMN_NAME_TELEFEONO;
                String val=contactos.get(pos).getPhone();
                String aux=col+"='"+val+"'";
                db.delete(TableData.TableInfo.TABLE_NAME_AGENDA,aux,null);
                Log.d("Operaciones bases de datos", "Eliminada una fila");
                db.close();

            }

            contactos.remove(rowItem);
            CustomArrayAdapter.this.notifyDataSetChanged();
            Toast.makeText(v.getContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();

             }
        });


        imageView3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*FragmentManager fm = ((Activity)context).getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Editar_contacto(nom,tfno) )
                        .commit();*/
                Intent inten = new Intent(context, Editar_contacto.class);
                inten.putExtra("nom", nom);
                inten.putExtra("tlfn", tfno);
                ((Activity)context).startActivity(inten);
            }
        });


        return rowView;
    }
}
