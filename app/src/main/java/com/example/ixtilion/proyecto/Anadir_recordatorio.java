package com.example.ixtilion.proyecto;

import android.app.DatePickerDialog;

import android.app.AlarmManager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.text.DateFormat;
import java.util.List;


/**
 * Created by rok on 14/03/2015.
 */
public class Anadir_recordatorio extends Fragment {
    EditText NOMBRE, CANTIDADTOMA, FECHAINICIO, FECHAFIN, INTERVALO, HORA;
    String nombre,fechaIni,fechaFin;
    int intervalo, horaIni;
    float cantidad;
    Button anadir;
    ImageView config;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;
    private Spinner medicamentos;
    Cursor cursorMedicamentos;

    Calendar cal=Calendar.getInstance();
    DateFormat datfor=DateFormat.getDateInstance();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.agregar_recor_pastilla, container, false);

        medicamentos = (Spinner) view.findViewById(R.id.spMedicamentos);
        List<String> list = new ArrayList<>();

        dbOp = new DatabaseOperations(c);
        cursorMedicamentos = dbOp.cargarCursorMedicamentos();
        final Resources res = getResources();
        list.add(res.getString(R.string.EligeMedicamento));


        if (cursorMedicamentos.moveToFirst()) {
            do {
                String nombre = cursorMedicamentos.getString(0).toUpperCase();

                list.add(nombre);

            } while (cursorMedicamentos.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicamentos.setAdapter(dataAdapter);

        //ESTO NO VA AQUI VA EN EDITAR RECORDATORIO PARA DEJAR SELECIONADO EL MEDICAMENTO QUE YA ESTABA
        //medicamentos.setSelection (dataAdapter.getPosition ("IBUPROFENO"));

        anadir = (Button) view.findViewById(R.id.btAnadirRec);
        config = (ImageView) view.findViewById(R.id.Config);
        CANTIDADTOMA =   (EditText) view.findViewById(R.id.tbCantidad);
        FECHAINICIO = (EditText) view.findViewById(R.id.TbfechaIni);
        FECHAFIN = (EditText) view.findViewById(R.id.Tbfechafin);
        INTERVALO = (EditText) view.findViewById(R.id.tbTiempo);
        HORA= (EditText) view.findViewById(R.id.tbHora);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = medicamentos.getSelectedItem().toString();

                //COMPROBAMOS QUE EXISTE EL MEDICAMENTO
                if ((nombre.compareTo(res.getString(R.string.EligeMedicamento))!=0) && (CANTIDADTOMA.getText().length() != 0) &&
                        (FECHAINICIO.getText().length() != 0) && (FECHAFIN.getText().length() != 0) && (INTERVALO.getText().length() != 0)) {

                    cantidad = Float.parseFloat(CANTIDADTOMA.getText().toString());
                    fechaIni = FECHAINICIO.getText().toString();
                    fechaFin = FECHAFIN.getText().toString();
                    intervalo = Integer.parseInt(INTERVALO.getText().toString());


                    if (c != null) {

                        dbOp = new DatabaseOperations(c);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                                int id=0; //Id temporal


                                id = nombre.length()+intervalo;

                                ContentValues cv = new ContentValues();
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_ID, id);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_MEDICAMENTO, nombre);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_DESCRIPCION,"");
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_DIASTOMASMES,30);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_DIASDESCANSOMES,0);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_CANTIDADTOMA, cantidad);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAINICIO, fechaIni);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAFIN, fechaFin);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_INTERVALO, intervalo);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_HORA, horaIni);

                                db.insert(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO, null, cv);
                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA RECORDATORIOS
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                      .replace(R.id.container, new Lista_recordatorio("all"))
                                    .commit();

                                Toast.makeText(c, "Recordatorio añadido correctamente", Toast.LENGTH_LONG).show();


                    } else {
                        Log.d("error", "else");
                    }
                } else {
                    Toast.makeText(c, "Error: Algún campo vacío", Toast.LENGTH_LONG).show();
                }
                //Alarma
                AlarmReceiver alarm=new AlarmReceiver();
                alarm.SetAlarm(c);
                //alarm.setOnetimeTimer(c);


            }
        });

        HORA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener tdp = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaIni = hourOfDay;
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);
                        long milisegundos = cal.getTimeInMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                        String dateString = sdf.format(milisegundos);
                        HORA.setText(dateString);
                    }
                };

                new TimePickerDialog(c, tdp, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false).show();
            }
        });


        HORA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(HORA.isFocused()){
                    TimePickerDialog.OnTimeSetListener tdp = new TimePickerDialog.OnTimeSetListener(){
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaIni = hourOfDay;
                            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            cal.set(Calendar.MINUTE, minute);
                            long milisegundos = cal.getTimeInMillis();
                            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                            String dateString = sdf.format(milisegundos);
                            HORA.setText(dateString);
                        }
                    };
                    new TimePickerDialog(c, tdp, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false).show();
                }
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config.setVisibility(View.INVISIBLE);
                EditText textView=new EditText(c); //no se ve
                textView.setText("Ave yo");
            }
        });

        FECHAINICIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECHAINICIO.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        FECHAINICIO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(FECHAINICIO.isFocused()){
                    DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet (DatePicker fec, int y, int m, int d){
                            cal.set(Calendar.YEAR, y);
                            cal.set(Calendar.MONTH, m);
                            cal.set(Calendar.DAY_OF_MONTH, d);
                            FECHAINICIO.setText(datfor.format(cal.getTime()));
                        }
                    };
                    new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        FECHAFIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECHAFIN.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        FECHAFIN.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(FECHAFIN.isFocused()){
                    DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet (DatePicker fec, int y, int m, int d){
                            cal.set(Calendar.YEAR, y);
                            cal.set(Calendar.MONTH, m);
                            cal.set(Calendar.DAY_OF_MONTH, d);
                            FECHAFIN.setText(datfor.format(cal.getTime()));
                        }
                    };
                    new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });


        return view;
    }
}
