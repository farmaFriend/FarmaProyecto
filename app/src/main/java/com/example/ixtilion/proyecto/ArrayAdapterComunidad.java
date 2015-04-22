package com.example.ixtilion.proyecto;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ixtilion.proyecto.dummy.Comunidad;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by rok on 15/03/2015.
 */
public class ArrayAdapterComunidad extends ArrayAdapter<Comunidad> {
    private final Context context;
    private final ArrayList<Comunidad> comunidades;

    public ArrayAdapterComunidad(Context context, ArrayList<Comunidad> comunidades) {
        super(context, R.layout.linea_comunidad, comunidades);
        this.context = context;
        this.comunidades = comunidades;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final String nom = comunidades.get(position).getName();
        final String ico = comunidades.get(position).getIco();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linea_comunidad, parent, false);
        TextView linea1 = (TextView) rowView.findViewById(R.id.textComunidad);
        ImageView bandera = (ImageView) rowView.findViewById(R.id.bandera);
        linea1.setText(nom);

        Resources res = context.getResources();
        String mDrawableName = ico;
        Log.d("ico", ico);

        try{
            int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
            Drawable drawable = res.getDrawable(resID);
            bandera.setImageDrawable(drawable);
        }

        catch(Resources.NotFoundException e){}

        return rowView;
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
