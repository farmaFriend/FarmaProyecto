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
public class ArrayAdapterComunidad extends ArrayAdapter<Medicamento> {
    private final Context context;


    public ArrayAdapterComunidad(Context context) {
        super(context, R.layout.linea_comunidad);
        this.context = context;

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linea_comunidad, parent, false);
        return rowView;
    }
}
