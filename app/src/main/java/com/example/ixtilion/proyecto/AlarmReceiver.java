package com.example.ixtilion.proyecto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Alumno on 21/04/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";
    public static String medicamento = "noooo";
    public static float cantidad =0;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent inten = new Intent(context, Alarma.class);
        inten.putExtra("medicam", medicamento);
        inten.putExtra("canti", cantidad);
        inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(inten);
    }

    public void SetAlarm(Context context, String nomMed, float cant, int intervalo, int horaIni, int minIni, int anio, int mes, int dia)
    {
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        medicamento=nomMed;
        cantidad=cant;
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, anio);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.HOUR_OF_DAY, horaIni);
        calendar.set(Calendar.MINUTE, minIni);
        calendar.set(Calendar.SECOND, 0);

        //After after 30 seconds
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 3 * intervalo, pi);
    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}

