package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Toast;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alumno on 30/04/2015.
 */
public class Alarma extends Activity {

    private Button aceptar;
    private final Context context=this;
    private static String nomMed;
    private Vibrator vibrator;
    private DatabaseOperations dbOp;
    private Cursor cursor;
    private static float cant, cantiToma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarma);
        TextView med= (TextView) findViewById(R.id.tvToma);
        nomMed=getIntent().getExtras().getString("medicam");
        cantiToma=getIntent().getExtras().getFloat("canti");
        med.setText("TOMAR: "+nomMed+"\n"+"CANTIDAD: "+String.valueOf(cantiToma));

        vibrator= (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        accionAlarm(vibrator);

        aceptar = (Button) findViewById(R.id.btAcepAla);

        dbOp= new DatabaseOperations(this);
        final SQLiteDatabase db = dbOp.getWritableDatabase();

        if (db != null) {
            cursor = dbOp.getNumPastillas(nomMed);
            if (cursor.moveToFirst()) {
                cant = cursor.getFloat(0);
            }
        }
        Toast.makeText(context, String.valueOf(cant), Toast.LENGTH_LONG).show();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esto hay que hacerlo para cancelarla el dia que corresponda
                //AlarmReceiver alarm=new AlarmReceiver();
                //alarm.CancelAlarm(context);
                vibrator.cancel();

                //editar cantidad medicamento
                ContentValues cv = new ContentValues();

                cv.put(TableData.TableInfoMedic.COLUMN_NAME_NOMBRE, nomMed);
                cv.put(TableData.TableInfoMedic.COLUMN_NAME_CANTIDAD, cant-1);

                String col=TableData.TableInfoMedic.COLUMN_NAME_NOMBRE;
                String val=nomMed;
                String aux=col+"='"+val+"'";

                db.update(TableData.TableInfoMedic.TABLE_NAME_MEDICAMENTO, cv, aux, null);
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

        Toast.makeText(context, nomMed, Toast.LENGTH_LONG).show();

        vibra.vibrate(60000);//vibra durante un minuto

        //Release the lock
        wl.release();
    }
}
