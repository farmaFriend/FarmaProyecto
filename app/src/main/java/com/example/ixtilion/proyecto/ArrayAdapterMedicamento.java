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
 * Created by rok on 15/03/2015.
 */
public class ArrayAdapterMedicamento extends ArrayAdapter<Medicamento> {
    private final Context context;
    private final ArrayList<Medicamento> medicamentos;

    public ArrayAdapterMedicamento(Context context, ArrayList<Medicamento> medicamentos) {
        super(context, R.layout.linea_medicamento, medicamentos);
        this.context = context;
        this.medicamentos = medicamentos;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_medicamento, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.fLMed);
        TextView linea2 = (TextView) rowView.findViewById(R.id.sLMed);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.ImMed);
        linea1.setText(medicamentos.get(position).getNombre());
        linea2.setText(String.valueOf(medicamentos.get(position).getNum_pastillas()));
        imageView.setImageResource(R.drawable.pill);

        return rowView;
    }
}
