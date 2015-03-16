package com.example.ixtilion.proyecto;

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
public class ArrayAdapterMedicamento extends ArrayAdapter<Medicamento> {
    private final Context context;
    private final ArrayList<Medicamento> medicamentos;

    public ArrayAdapterMedicamento(Context context, ArrayList<Medicamento> medicamentos) {
        super(context, R.layout.linea_medicamento, medicamentos);
        this.context = context;
        this.medicamentos = medicamentos;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_medicamento, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.fLMed);
        TextView linea2 = (TextView) rowView.findViewById(R.id.sLMed);
        ImageView imageQuit = (ImageView) rowView.findViewById(R.id.ImQuitar);
        ImageView imageEdit = (ImageView) rowView.findViewById(R.id.ImModificar);
        linea1.setText(medicamentos.get(position).getNombre());
        linea2.setText(String.valueOf(medicamentos.get(position).getNum_pastillas()));

        imageQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations dbHelper = new DatabaseOperations(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (db != null) {
                    String col=TableData.TableInfoMedic.COLUMN_NAME_NOMBRE;
                    String val=medicamentos.get(pos).getNombre();
                    String aux=col+"='"+val+"'";
                    db.delete(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO,aux,null);
                    Log.d("Operaciones bases de datos", "Eliminada una fila");
                    db.close();

                    Toast.makeText(context, "Medicamento eliminado correctamente", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rowView;
    }
}
