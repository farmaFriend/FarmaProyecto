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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;

/**
 * Created by rok on 14/03/2015.
 */
public class Anadir_recordatorio extends Fragment {
    EditText NOMBRE, CANTIDADTOMA, FECHAINICIO, FECHAFIN, INTERVALO;
    String nombre,fechaIni,fechaFin;
    int intervalo;
    float cantidad;
    Button anadir;
    ImageView config;
    Context c;
    private DatabaseOperations dbOp;
    Cursor cursor;
    Calendar cal=Calendar.getInstance();
    DateFormat datfor=DateFormat.getDateInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        c = container.getContext();
        View view = inflater.inflate(R.layout.agregar_recor_pastilla, container, false);
        anadir = (Button) view.findViewById(R.id.btAnadirRec);
        config = (ImageView) view.findViewById(R.id.Config);
        NOMBRE = (EditText) view.findViewById(R.id.TbnomMedi);
        CANTIDADTOMA =   (EditText) view.findViewById(R.id.tbCantidad);
        FECHAINICIO = (EditText) view.findViewById(R.id.TbfechaIni);
        FECHAFIN = (EditText) view.findViewById(R.id.Tbfechafin);
        INTERVALO = (EditText) view.findViewById(R.id.tbTiempo);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //COMPROBAMOS QUE EXISTE EL MEDICAMENTO
                if ((NOMBRE.getText().length() != 0) && (CANTIDADTOMA.getText().length() != 0)) {
                    nombre = NOMBRE.getText().toString();
                    cantidad = Float.parseFloat(CANTIDADTOMA.getText().toString());
                    fechaIni = FECHAINICIO.getText().toString();
                    fechaFin = FECHAFIN.getText().toString();
                    intervalo = Integer.parseInt(INTERVALO.getText().toString());

                    if (c != null) {
                        dbOp = new DatabaseOperations(c);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                        if (db != null) {
                            cursor = dbOp.cargarCursorMedicamentos();
                            Medicamento m;

                            final ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();

                            if (cursor.moveToFirst()) {
                                do {
                                    String nombre = cursor.getString(0);
                                    float cantidad = cursor.getFloat(1);

                                    medicamentos.add(new Medicamento(nombre, cantidad));

                                } while (cursor.moveToNext());
                            }

                            int i = 0;
                            boolean existe = false;
                            while (i < medicamentos.size() && existe == false) {
                                if (nombre.compareTo(medicamentos.get(i).getNombre()) == 0) {
                                    existe = true;
                                }
                                i++;
                            }

                            if (!existe) {
                                Toast.makeText(c, "Introduce un medicamento existente", Toast.LENGTH_LONG).show();
                            }

                            if (existe) {

                                int id=0; //Id temporal
                                /*Cursor c1 = dbOp.cargarCursorRecordatorios();
                                if (c1.moveToFirst()) {
                                        id = cursor.getInt(0);
                                        medicamentos.add(new Medicamento(nombre, cantidad));
                                }*/

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


                                db.insert(TableData.TableInfoRecordatorio.TABLE_NAME_RECORDATORIO, null, cv);
                                Log.d("Operaciones bases de datos", "Insertada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA RECORDATORIOS
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                      .replace(R.id.container, new Lista_recordatorio())
                                    .commit();

                                Toast.makeText(c, "Recordatorio añadido correctamente", Toast.LENGTH_LONG).show();

                            }

                        }
                    } else {
                        Log.d("error", "else");
                    }
                } else {
                    Toast.makeText(c, "Error: Algún campo vacío", Toast.LENGTH_LONG).show();
                }
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //poner
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
        /*FECHAFIN.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECHAFIN.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                return false;
            }
        });*/
        /*FECHAFIN.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                DatePickerDialog.OnDateSetListener dpd= new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet (DatePicker fec, int y, int m, int d){
                        cal.set(Calendar.YEAR, y);
                        cal.set(Calendar.MONTH, m);
                        cal.set(Calendar.DAY_OF_MONTH, d);
                        FECHAFIN.setText(datfor.format(cal.getTime()));
                    }
                };
                new DatePickerDialog(c,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                return false;
            }
        });*/

        return view;
    }
}
