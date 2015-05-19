package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alumno on 19/03/2015.
 */
public class Editar_recordatorio extends Activity {
    EditText CANTIDAD, TIEMPO, FECIN, FECFI, nomMed, HORA;
    String nombre,fechaIni,fechaFin;
    float cantidad;
    int tiempo, horaIni, minIni;
    Button editar;
    private final Context c=this;
    private DatabaseOperations dbOp;
    private String n, pas, tiem, id, fecIn, fecFi;
    Calendar cal=Calendar.getInstance();
    DateFormat datfor=DateFormat.getDateInstance();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_recor_pastilla);

        this.n=getIntent().getExtras().getString("nom");
        this.pas=getIntent().getExtras().getString("pas");
        this.tiem=getIntent().getExtras().getString("tiemp");
        this.id=getIntent().getExtras().getString("id");
        this.fecIn=getIntent().getExtras().getString("fecIn");
        this.fecFi=getIntent().getExtras().getString("fecFi");
        this.horaIni=getIntent().getExtras().getInt("horI");
        this.minIni=getIntent().getExtras().getInt("minI");

        final Resources res = getResources();

        editar = (Button) findViewById(R.id.btEditar);
        CANTIDAD = (EditText)findViewById(R.id.tbCantidad);
        TIEMPO = (EditText) findViewById(R.id.tbTiempo);
        FECIN = (EditText)findViewById(R.id.TbfechaIni);
        FECFI = (EditText) findViewById(R.id.Tbfechafin);
        nomMed = (EditText) findViewById(R.id.TbnomMedi);
        HORA= (EditText) findViewById(R.id.tbHorarec);

        nomMed.setText(this.n);
        nomMed.setEnabled(false);
        CANTIDAD.setText(this.pas);
        TIEMPO.setText(this.tiem);
        FECIN.setText(this.fecIn);
        FECFI.setText(this.fecFi);
        HORA.setText(this.horaIni+":"+this.minIni);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((CANTIDAD.getText().length() != 0) && (FECIN.getText().length() != 0) && (FECFI.getText().length() != 0) && (TIEMPO.getText().length() != 0) && (HORA.getText().length() != 0)) {
                    cantidad = Float.parseFloat(CANTIDAD.getText().toString());
                    fechaIni = FECIN.getText().toString();
                    fechaFin = FECFI.getText().toString();
                    tiempo = Integer.parseInt(TIEMPO.getText().toString());

                    if(c!=null) {
                        dbOp= new DatabaseOperations(c);
                        final SQLiteDatabase db = dbOp.getWritableDatabase();

                        if (db != null) {

                            final ArrayList<Recordatorio_medicamento> recordatorios = new ArrayList<Recordatorio_medicamento>();

                                ContentValues cv = new ContentValues();
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_CANTIDADTOMA, cantidad);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAINICIO, fechaIni);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_FECHAFIN, fechaFin);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_INTERVALO, tiempo);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_HORA, horaIni);
                                cv.put(TableData.TableInfoRecordatorio.COLUMN_NAME_MIN, minIni);

                                String col=TableData.TableInfoRecordatorio.COLUMN_NAME_ID;
                                String aux=col+"='"+id+"'";

                                db.update(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                            Intent inten = new Intent(c, Lista_recordatorio.class);
                            startActivity(inten);
                            Toast.makeText(c, res.getString(R.string.Editado), Toast.LENGTH_LONG).show();
                            finish();

                        }

                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(c, res.getString(R.string.Error), Toast.LENGTH_LONG).show();
                }
            }
        });

        HORA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener tdp = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaIni = hourOfDay;
                        minIni=minute;
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
                            minIni=minute;
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


        FECIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECIN.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        FECIN.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(FECIN.isFocused()){
                    DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet (DatePicker fec, int y, int m, int d){
                            cal.set(Calendar.YEAR, y);
                            cal.set(Calendar.MONTH, m);
                            cal.set(Calendar.DAY_OF_MONTH, d);
                            FECIN.setText(datfor.format(cal.getTime()));
                        }
                    };
                    new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        FECFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECFI.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        FECFI.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(FECFI.isFocused()){
                    DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet (DatePicker fec, int y, int m, int d){
                            cal.set(Calendar.YEAR, y);
                            cal.set(Calendar.MONTH, m);
                            cal.set(Calendar.DAY_OF_MONTH, d);
                            FECFI.setText(datfor.format(cal.getTime()));
                        }
                    };
                    new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
    }
}
