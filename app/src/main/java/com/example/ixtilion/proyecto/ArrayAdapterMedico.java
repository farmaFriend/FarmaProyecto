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
 * Created by Alumno on 26/03/2015.
 */
public class ArrayAdapterMedico extends ArrayAdapter<Medico> {
    private final Context context;
    private final ArrayList<Medico> medicos;

    public ArrayAdapterMedico(Context context, ArrayList<Medico> medicos) {
        super(context, R.layout.linea_medico, medicos);
        this.context = context;
        this.medicos = medicos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final String nom = medicos.get(position).getNombre();
        final String espe = medicos.get(position).getEspecialidad();
        final String direc = medicos.get(position).getDireccion();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_medico, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.fLMedico);
        TextView linea2 = (TextView) rowView.findViewById(R.id.sLMedico);
        TextView linea3 = (TextView) rowView.findViewById(R.id.dirMedico);
        ImageView imageQuit = (ImageView) rowView.findViewById(R.id.ImQuitarMedico);
        ImageView imageEdit = (ImageView) rowView.findViewById(R.id.ImModificarMedico);
        linea1.setText(nom);
        linea2.setText(espe);
        linea3.setText(direc);

        imageQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations dbHelper = new DatabaseOperations(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (db != null) {
                    String col=TableData.TableInfoMedico.COLUMN_NAME_ID;
                    String val=medicos.get(pos).getId();
                    String aux=col+"='"+val+"'";
                    db.delete(TableData.TableInfoMedico.TABLE_NAME_MEDICO,aux,null);
                    Log.d("Operaciones bases de datos", "Eliminada una fila");
                    db.close();

                    Toast.makeText(context, "Medico eliminado correctamente", Toast.LENGTH_LONG).show();
                    medicos.remove(pos);
                    ArrayAdapterMedico.this.notifyDataSetChanged();
                }
            }
        });
        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((Activity)context).getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, new Edita_medico(nom, espe, direc))
                        .commit();
            }
        });

        return rowView;
    }
}
