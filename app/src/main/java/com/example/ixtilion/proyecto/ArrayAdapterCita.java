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
 * Created by USUARIO on 27/04/2015.
 */
public class ArrayAdapterCita extends ArrayAdapter<Cita> {
    private final Context context;
    private final ArrayList<Cita> citas;

    public ArrayAdapterCita(Context context, ArrayList<Cita> citas) {
        super(context, R.layout.linea_cita, citas);
        this.context = context;
        this.citas = citas;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final String medico=citas.get(position).getMedico();
        final String descripcion = citas.get(position).getDescripcion();
        final String fecha = citas.get(position).getFecha();
        final String hora = citas.get(position).getHora();


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_cita, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.textEspecialidad);
        TextView linea2 = (TextView) rowView.findViewById(R.id.textFecha);
        TextView linea3 = (TextView) rowView.findViewById(R.id.textHora);
        TextView linea4 = (TextView) rowView.findViewById(R.id.textDescr);

        ImageView imageQuit = (ImageView) rowView.findViewById(R.id.ImQuitar);
        ImageView imageEdit = (ImageView) rowView.findViewById(R.id.ImModificar);

        if(medico.compareTo("---Elige un médico---")==0){
            linea1.setText(descripcion);
            linea4.setText(fecha);
            linea2.setText(hora);
            linea3.setText("");
        }
        else{
            linea1.setText(medico);
            linea2.setText(fecha);
            linea3.setText(hora);
            linea4.setText(descripcion);
        }

/*
        imageQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations dbHelper = new DatabaseOperations(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (db != null) {
                    String col=TableData.TableCitaMedico.COLUMN_NAME_DESCRIPCION;
                    String val=citas.get(pos).getDescripcion();
                    String aux=col+"='"+val+"'";
                    db.delete(TableData.TableCitaMedico.TABLE_NAME_CITEMEDICO,aux,null);
                    Log.d("Operaciones bases de datos", "Eliminada una fila");
                    db.close();

                    Toast.makeText(context, "Cita eliminada correctamente", Toast.LENGTH_LONG).show();
                    citas.remove(pos);
                    ArrayAdapterCita.this.notifyDataSetChanged();
                }
            }
        });
        /*
        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((Activity)context).getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Editar_medicamento(nom, pas))
                        .commit();
            }
        });
        */
        return rowView;
    }
}
