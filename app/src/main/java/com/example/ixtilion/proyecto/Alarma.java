package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Alumno on 30/04/2015.
 */
public class Alarma extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarma);
        TextView med= (TextView) findViewById(R.id.tvToma);
        String nomMed=getIntent().getExtras().getString("medicam");
        med.setText("TOMAR: "+nomMed);
    }
}
