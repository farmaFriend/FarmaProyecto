package com.example.ixtilion.proyecto;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by USUARIO on 24/04/2015.
 */
public class Anadir_cita_medica extends Fragment {
    EditText DESCRIPCION, FECHA, HORA;
    String medico,descripcion,fecha, hora, id;
    Button anadir;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursorMedico;
    Cursor cursor;
    private Spinner medicos;
    Calendar cal=Calendar.getInstance();
    DateFormat datfor=DateFormat.getDateInstance();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        c = container.getContext();
        View view = inflater.inflate(R.layout.agreagar_cita_mdedico, container, false);

        medicos = (Spinner) view.findViewById(R.id.spMedico);
        List<String> list = new ArrayList<>();

        dbOp = new DatabaseOperations(c);
        cursorMedico = dbOp.cargarCursorMedicos();
        list.add("---Elige un médico---");

        if (cursorMedico.moveToFirst()) {
            do {
                String nombre = cursorMedico.getString(1).toUpperCase();

                list.add(nombre);

            } while (cursorMedico.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicos.setAdapter(dataAdapter);

        anadir = (Button) view.findViewById(R.id.btAñadirCita);
        DESCRIPCION =   (EditText) view.findViewById(R.id.tbDescrpcion);
        FECHA = (EditText) view.findViewById(R.id.tbFecha);
        HORA = (EditText) view.findViewById(R.id.tbHora);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medico = medicos.getSelectedItem().toString();

                if ((DESCRIPCION.getText().length() != 0) && (HORA.getText().length() != 0) && (FECHA.getText().length() !=0)){

                    descripcion = DESCRIPCION.getText().toString();
                    fecha = FECHA.getText().toString();
                    hora = HORA.getText().toString();

                    if (c != null) {
                        dbOp = new DatabaseOperations(c);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                        id=descripcion+fecha+hora;

                        ContentValues cv = new ContentValues();
                        cv.put(TableData.TableCitaMedico.COLUMN_NAME_ID, id);
                        cv.put(TableData.TableCitaMedico.COLUMN_NAME_DESCRIPCION, descripcion);
                        cv.put(TableData.TableCitaMedico.COLUMN_NAME_FECHA, fecha);
                        cv.put(TableData.TableCitaMedico.COLUMN_NAME_HORA, hora);
                        cv.put(TableData.TableCitaMedico.COLUMN_NAME_MEDICO, medico);

                        db.insert(TableData.TableCitaMedico.TABLE_NAME_CITEMEDICO,null, cv);
                        Log.d("Operaciones bases de datos", "Insertada una fila");

                        db.close();

                        //CODIGO QUE MANDA A VISTA LISTA CITAS
                        FragmentManager fm = getFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.container, new Lista_recordatorio())
                                .commit();

                        Toast.makeText(c, "Cita médico añadida correctamente", Toast.LENGTH_LONG).show();

                    }else{
                        Log.d("error", "else");
                    }

                }else{
                    Toast.makeText(c, "Error: Algún campo vacío", Toast.LENGTH_LONG).show();
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

        return view;
    }
}
