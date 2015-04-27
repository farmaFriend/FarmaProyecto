package com.example.ixtilion.proyecto;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rok on 15/03/2015.
 */
public class ArrayAdapterRecordatorio extends ArrayAdapter<Recordatorio_medicamento> {
    private final Context context;
    private final ArrayList<Recordatorio_medicamento> recordatorios;

    public ArrayAdapterRecordatorio(Context context, ArrayList<Recordatorio_medicamento>recordatorios) {
        super(context, R.layout.linea_recordatorio, recordatorios);
        this.context = context;
        this.recordatorios = recordatorios;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;

        final String nom=recordatorios.get(position).getMedicamento();
        final String pas=String.valueOf(recordatorios.get(position).getCantidadToma());
        final String tiemp = String.valueOf(recordatorios.get(position).getIntervalo());
        final int id = recordatorios.get(position).getId();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_recordatorio, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.fLRec);
        TextView linea2 = (TextView) rowView.findViewById(R.id.sLRec);
        ImageView imageQuit = (ImageView) rowView.findViewById(R.id.ImQuitarRec);
        ImageView imageEdit = (ImageView) rowView.findViewById(R.id.ImModificarRec);
        linea1.setText(nom);
        linea2.setText(pas);

        imageQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations dbHelper = new DatabaseOperations(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (db != null) {
                    String col=TableData.TableInfoRecordatorio.COLUMN_NAME_ID;
                    int val=recordatorios.get(pos).getId();
                    String aux=col+"='"+val+"'";
                    db.delete(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO,aux,null);
                    Log.d("Operaciones bases de datos", "Eliminada una fila");
                    db.close();

                    Toast.makeText(context, "Recordatorio eliminado correctamente", Toast.LENGTH_LONG).show();
                    recordatorios.remove(pos);
                    ArrayAdapterRecordatorio.this.notifyDataSetChanged();
                }
            }
        });

        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((Activity)context).getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Editar_recordatorio(nom, pas, tiemp, id))
                        .commit();
            }
        });

        //imageEdit.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View v) {
          //      FragmentManager fm = ((Activity)context).getFragmentManager();
            //    fm.beginTransaction()
              //          .replace(R.id.container, new Editar_medicamento(nom, pas))
                //        .commit();
           // }
        //});
        return rowView;
    }
}
