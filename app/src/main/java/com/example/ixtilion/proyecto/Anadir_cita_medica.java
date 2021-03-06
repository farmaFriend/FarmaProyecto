package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by USUARIO on 24/04/2015.
 */
public class Anadir_cita_medica extends Activity {
    private final Context context = this;
    EditText DESCRIPCION, FECHA, HORA;
    String medico,descripcion,fecha, hora, id;
    Button anadir;
    private final Context c = this;
    private DatabaseOperations dbOp;
    Cursor cursorMedico;
    Cursor cursor;
    private Spinner medicos;
    Calendar cal=Calendar.getInstance();
    DateFormat datfor=DateFormat.getDateInstance();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agreagar_cita_mdedico);
        final Resources res = getResources();

        medicos = (Spinner) findViewById(R.id.spMedico);
        List<String> list = new ArrayList<>();

        dbOp = new DatabaseOperations(context);
        cursorMedico = dbOp.cargarCursorMedicos();
        list.add(res.getString(R.string.ElegDoctor));

        if (cursorMedico.moveToFirst()) {
            do {
                String nombre = cursorMedico.getString(1).toUpperCase();
                String especialidad = cursorMedico.getString(2).toUpperCase();

                list.add(nombre+" - "+especialidad);

            } while (cursorMedico.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicos.setAdapter(dataAdapter);

        anadir = (Button) findViewById(R.id.btAñadirCita);
        DESCRIPCION =   (EditText) findViewById(R.id.tbDescrpcion);
        FECHA = (EditText) findViewById(R.id.tbFecha);
        HORA = (EditText)findViewById(R.id.tbHora);

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

                        if(db!=null){
                            //Mirar si en la base de datos existe un medico con ese nombre y especialidad

                            cursor = dbOp.cargarCursorCitasMedico();

                            final ArrayList<Cita> citas = new ArrayList<Cita>();

                            if (cursor.moveToFirst()) {
                                do {
                                    String id = cursor.getString(4);
                                    String descripcion = cursor.getString(1);
                                    String fecha = cursor.getString(2);
                                    String hora = cursor.getString(3);
                                    String medico = cursor.getString(0);

                                    citas.add(new Cita(medico, descripcion, fecha,hora,id));


                                } while (cursor.moveToNext());
                            }

                            //No poner citas pasadas
                            Date fA = new Date();
                            DateFormat df =  DateFormat.getDateInstance();
                            String fechAct = df.format(fA);
                            StringTokenizer st=new StringTokenizer(fechAct, "/");
                            StringTokenizer staux=new StringTokenizer(fecha, "/");

                            boolean fechaCorrecta=true;
                            int dia=Integer.parseInt(st.nextToken());
                            int diaux=Integer.parseInt(staux.nextToken());
                            int mes=Integer.parseInt(st.nextToken());
                            int mesaux=Integer.parseInt(staux.nextToken());
                            int anio=Integer.parseInt(st.nextToken());
                            int anioaux=Integer.parseInt(staux.nextToken());
                            final Resources res = getResources();

                            if(anioaux<anio){
                                fechaCorrecta=false;
                                Toast.makeText(c, res.getString(R.string.ErrorAnt), Toast.LENGTH_LONG).show();                            }
                            else if(anioaux==anio){
                                if(mesaux<mes){
                                    fechaCorrecta=false;
                                    Toast.makeText(c, res.getString(R.string.ErrorAnt), Toast.LENGTH_LONG).show();                                }
                                else if(mesaux==mes){
                                    if(diaux<dia){
                                        fechaCorrecta=false;
                                        Toast.makeText(c, res.getString(R.string.ErrorAnt), Toast.LENGTH_LONG).show();                                    }
                                }
                            }



                            int i=0;
                            boolean existe=false;
                            while(i<citas.size()&& existe==false) {
                                if(id.compareTo(citas.get(i).getId())==0){
                                    Toast.makeText(context, "Ya existe una cita con la misma descripcion, fecha y hora", Toast.LENGTH_LONG).show();
                                    existe=true;
                                }
                                i++;
                            }
                            if(!existe && fechaCorrecta){
                                ContentValues cv = new ContentValues();
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_ID, id);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_DESCRIPCION, descripcion);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_FECHA, fecha);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_HORA, hora);
                                cv.put(TableData.TableCitaMedico.COLUMN_NAME_MEDICO, medico);


                        //CODIGO QUE MANDA A VISTA LISTA CITAS
                        /*FragmentManager fm = getFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.container, new Lista_citas())
                                .commit();*/
                                Intent inten = new Intent(context, Lista_citas.class);
                                startActivity(inten);

                                db.insert(TableData.TableCitaMedico.TABLE_NAME_CITEMEDICO,null, cv);

                                Toast.makeText(context, "Cita médico añadida correctamente", Toast.LENGTH_LONG).show();

                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();
                                finish();

                            }

                        }

                    }else{
                        Log.d("error", "else");
                    }

                }else{
                    Toast.makeText(context, "Error: Algún campo vacío", Toast.LENGTH_LONG).show();
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

    }
}
