package com.example.ixtilion.proyecto;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by Alumno on 21/04/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //comprobar fecha y hora
        Calendar calendar = Calendar.getInstance();
        int hora=calendar.get(Calendar.HOUR_OF_DAY);
        int min=calendar.get(Calendar.MINUTE);
        int anio=calendar.get(Calendar.YEAR);
        int mes=calendar.get(Calendar.MONTH)+1;
        int dia=calendar.get(Calendar.DAY_OF_MONTH);

        //Sacar de la base de datos
        DatabaseOperations dbOp= new DatabaseOperations(context);
        int tam=0;
        final SQLiteDatabase db = dbOp.getWritableDatabase();
        if (db != null) {
            Cursor cursor = dbOp.cargarCursorRecordatoriosCount();
            if (cursor.moveToFirst()) {
                tam = cursor.getInt(0);
            }
        }
        //Si no hay recordatorios cancelo la alarma
        if(tam==0){
            CancelAlarm(context);
        }
        else {
            Cursor cursor = dbOp.cargarCursorRecordatorios();

            ArrayList<Recordatorio_medicamento> recordatorios = new ArrayList<Recordatorio_medicamento>();

            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(0);
                    String nombre = cursor.getString(5);
                    float cantidad = cursor.getFloat(3);
                    String fechaIni = cursor.getString(1);
                    String fechaFin = cursor.getString(2);
                    int intervalo = cursor.getInt(4);
                    int horaIni = cursor.getInt(6);
                    int minIni =cursor.getInt(7);
                    recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni, minIni));
                }while (cursor.moveToNext());
            }

            //Si la fecha fin ya es la correspondiente borro el recordatorio
            int diaux, mesaux, anioaux;
            StringTokenizer st;
            int auxil=0;
            for(int i=0; i<recordatorios.size();i++){
                String f = recordatorios.get(auxil).getFecha_fin();
                st=new StringTokenizer(f, "/");

                diaux=Integer.parseInt(st.nextToken());
                mesaux=Integer.parseInt(st.nextToken());
                anioaux=Integer.parseInt(st.nextToken());

                if(anioaux<anio){
                    borrar(dbOp, context, recordatorios.get(auxil).getId());
                    recordatorios.remove(auxil);
                    auxil--;
                }
                else if(anioaux==anio){
                    if(mesaux<mes){
                        borrar(dbOp, context, recordatorios.get(auxil).getId());
                        recordatorios.remove(auxil);
                        auxil--;
                    }
                    else  if(mesaux==mes){
                        if(diaux<=dia){
                            borrar(dbOp, context, recordatorios.get(auxil).getId());
                            recordatorios.remove(auxil);
                            auxil--;
                        }
                    }
                }
                auxil++;
            }
            //Ahora sí saco los recordatorios correspondientes
            cursor = dbOp.cargarCursorRecordatorios();
            recordatorios = new ArrayList<Recordatorio_medicamento>();

            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(0);
                    String nombre = cursor.getString(5);
                    float cantidad = cursor.getFloat(3);
                    String fechaIni = cursor.getString(1);
                    String fechaFin = cursor.getString(2);
                    int intervalo = cursor.getInt(4);
                    int horaIni = cursor.getInt(6);
                    int minIni =cursor.getInt(7);
                    if((hora==horaIni)&&(min==minIni)){
                        recordatorios.add(new Recordatorio_medicamento(nombre, fechaIni, fechaFin, intervalo, cantidad, id, horaIni, minIni));
                    }
                }while (cursor.moveToNext());
            }
            if(!recordatorios.isEmpty()) {
                final SQLiteDatabase dbaux = dbOp.getWritableDatabase();
                for (Recordatorio_medicamento r : recordatorios) {
                    ContentValues cv = new ContentValues();
                    int aux = r.getHoraIni() + r.getIntervalo();
                    while (aux>=24){
                        aux-=24;
                    }
                    cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_HORA, aux);

                    String col = TableData.TableInfoRecordatorio.COLUMN_NAME_ID;
                    String s = col +"='"+r.getId()+"'";

                    dbaux.update(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO, cv, s, null);
                }
                dbaux.close();

                Intent inten = new Intent(context, Alarma.class);
                inten.putExtra("recors", recordatorios);
                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(inten);
            }
        }
    }

    public void SetAlarm(Context context)
    {
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), 1000 * 60, pi);
    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
    public void borrar(DatabaseOperations dbHelper, Context context, String id){
        dbHelper = new DatabaseOperations(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            String col=TableData.TableInfoRecordatorio.COLUMN_NAME_ID;
            String aux=col+"='"+id+"'";
            db.delete(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO,aux,null);
            Log.d("Operaciones bases de datos", "Eliminada una fila");
            db.close();
        }
    }
}

