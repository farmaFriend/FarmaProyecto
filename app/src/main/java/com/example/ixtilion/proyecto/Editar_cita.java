package com.example.ixtilion.proyecto;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by USUARIO on 27/04/2015.
 */
public class Editar_cita extends Fragment{

    private String medico, descripcion, fecha, hora,id, id2;
    Context c;
    EditText  DESCRIPCION, FECHA, HORA;
    Button editar;
    private Spinner medicos;
    private DatabaseOperations dbOp;
    Cursor cursorMedico;
    Cursor cursor;
    Calendar cal=Calendar.getInstance();
    DateFormat datfor=DateFormat.getDateInstance();

    public Editar_cita (String med, String des, String f, String h){
        this.medico=med;
        this.descripcion=des;
        this.fecha=f;
        this.hora=h;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.editar_cita, container, false);

        medicos = (Spinner) view.findViewById(R.id.spMedico);

        List<String> list = new ArrayList<>();

        dbOp = new DatabaseOperations(c);
        cursorMedico = dbOp.cargarCursorMedicos();
        list.add("---Elige un médico---");

        if (cursorMedico.moveToFirst()) {
            do {
                String nombre = cursorMedico.getString(1).toUpperCase();
                String especialidad = cursorMedico.getString(2).toUpperCase();

                list.add(nombre+" - "+especialidad);

            } while (cursorMedico.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicos.setAdapter(dataAdapter);

        medicos.setSelection(dataAdapter.getPosition(medico));

        editar = (Button) view.findViewById(R.id.btEditarCita);

        DESCRIPCION =   (EditText) view.findViewById(R.id.tbDescrpcion);
        FECHA = (EditText) view.findViewById(R.id.tbFecha);
        HORA = (EditText) view.findViewById(R.id.tbHora);

        DESCRIPCION.setText(this.descripcion);
        FECHA.setText(this.fecha);
        HORA.setText(this.hora);



        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((DESCRIPCION.getText().length() != 0) && (HORA.getText().length() != 0) && (FECHA.getText().length() !=0)){
                    descripcion = DESCRIPCION.getText().toString();
                    fecha = FECHA.getText().toString();
                    hora = HORA.getText().toString();

                    if(c!=null) {

                        dbOp= new DatabaseOperations(c);
                        final SQLiteDatabase db = dbOp.getWritableDatabase();

                        id2=descripcion+fecha+hora;

                        if (db != null) {

                            final ArrayList<Cita> citas = new ArrayList<Cita>();
                            cursor = dbOp.cargarCursorCitasMedico();

                            if (cursor.moveToFirst()) {
                                do {
                                    id = cursor.getString(4);
                                    String descripcion = cursor.getString(1);
                                    String fecha = cursor.getString(2);
                                    String hora = cursor.getString(3);
                                    String medico = cursor.getString(0);

                                    citas.add(new Cita(medico, descripcion, fecha,hora,id));


                                } while (cursor.moveToNext());
                            }

                            int i=0;
                            boolean existe=false;
                            while(i<citas.size()&& existe==false) {
                                if(id2.compareTo(citas.get(i).getId())==0){
                                    Toast.makeText(c, "Ya existe una cita con la misma descripcion, fecha y hora", Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_ID, id2);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_DESCRIPCION, descripcion);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_FECHA, fecha);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_HORA, hora);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_MEDICO, medico);

                                String col=TableData.TableCitaMedico.COLUMN_NAME_ID;
                                String val=id2;

                                String aux=col+"='"+val+"'";



                                db.update(TableData.TableCitaMedico.TABLE_NAME_CITEMEDICO, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA CITAS
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
<<<<<<< HEAD
                                        .replace(R.id.container, new Citas_medico(fecha))
=======
                                        .replace(R.id.container, new Lista_citas())
>>>>>>> b85b1851410fc4706af7b345a1e95c3c845d0e8e
                                        .commit();



                                Toast.makeText(c, "Cita editada correctamente", Toast.LENGTH_LONG).show();

                            }


                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(c,"Error: Algún campo vacío", Toast.LENGTH_LONG).show();
                }
            }
        });

        FECHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECHA.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        FECHA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(FECHA.isFocused()){
                    DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet (DatePicker fec, int y, int m, int d){
                            cal.set(Calendar.YEAR, y);
                            cal.set(Calendar.MONTH, m);
                            cal.set(Calendar.DAY_OF_MONTH, d);
                            FECHA.setText(datfor.format(cal.getTime()));
                        }
                    };
                    new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        HORA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener tdp = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
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

        return view;

    }
}
