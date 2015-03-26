package com.example.ixtilion.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_medico, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.fLMedico);
        TextView linea2 = (TextView) rowView.findViewById(R.id.sLMedico);
        ImageView imageQuit = (ImageView) rowView.findViewById(R.id.ImQuitarMedico);
        ImageView imageEdit = (ImageView) rowView.findViewById(R.id.ImModificarMedico);
        linea1.setText(nom);
        linea2.setText(espe);

        return rowView;
    }
}
