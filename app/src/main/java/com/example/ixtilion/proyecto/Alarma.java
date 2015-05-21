package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Toast;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alumno on 30/04/2015.
 */
public class Alarma extends Activity {

    private Button aceptar;
    private final Context context=this;
    private Vibrator vibrator;
    private DatabaseOperations dbOp;
    private Cursor cursor;
    private Spinner sp;
    private ArrayList<Recordatorio_medicamento> recordatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Resources res = getResources();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarma);
        recordatorios = (ArrayList<Recordatorio_medicamento>)getIntent().getExtras().get("recors");

        //Poner todos los nombres de los medicamentos junto a la cantidad a tomar
        sp=(Spinner)findViewById(R.id.spRecorMed);
        List<String> list = new ArrayList<String>();
        for(Recordatorio_medicamento r : recordatorios){
            list.add(r.getMedicamento()+" - "+r.getCantidadToma());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);

        vibrator= (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        accionAlarm(vibrator);

        aceptar = (Button) findViewById(R.id.btAcepAla);

        dbOp= new DatabaseOperations(this);
        final SQLiteDatabase db = dbOp.getWritableDatabase();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();

                //editar cantidad medicamento
                for (Recordatorio_medicamento r : recordatorios) {
                    ContentValues cv = new ContentValues();

                    float cant=0;
                    String no=r.getMedicamento().toLowerCase();
                    if (db != null) {
                        cursor = dbOp.getNumPastillas(no);
                        if (cursor.moveToFirst()) {
                            cant = cursor.getFloat(0);
                        }
                    }
                    float caNue=cant - r.getCantidadToma();
                    if(cant>=r.getCantidadToma()) {
                        cv.put(TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD, caNue);
                        String col = TableData.TableInfoMedic.COLUMN_NAME_NOMBRE;
                        String aux = col + "='" + no + "'";

                        db.update(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO, cv, aux, null);
                    }
                    if(caNue<3){
                        Toast.makeText(context,res.getString(R.string.Menosde3)+ r.getMedicamento(), Toast.LENGTH_LONG).show();
                    }
                }
                db.close();
                finish();
            }
        });
    }
    protected void accionAlarm(Vibrator vibra){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        //Acquire the lock
        wl.acquire();

        vibra.vibrate(600000);//vibra durante 10 minutos

        //Release the lock
        wl.release();
    }
}
