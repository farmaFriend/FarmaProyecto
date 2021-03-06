package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rok on 28/03/2015.
 */
public class Edita_medico extends Activity {
    private final Context context = this;
    EditText NOMBRE, ESPECIALIDAD, DIRECCION;
    String nombre, especialidad, direccion, id;
    Button editar;
    private final Context c = this;
    private DatabaseOperations dbOp;
    Cursor cursor;
    String nom, esp, dir;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_medico);

        this.nom=getIntent().getExtras().getString("nom");
        this.esp=getIntent().getExtras().getString("espe");
        this.dir=getIntent().getExtras().getString("direc");

        editar = (Button) findViewById(R.id.EditbtMedico);
        NOMBRE = (EditText) findViewById(R.id.EdittbNombreMedico);
        ESPECIALIDAD = (EditText) findViewById(R.id.EdittbEspecialidad);
        DIRECCION = (EditText) findViewById(R.id.EdittbDireccion);

        NOMBRE.setText(this.nom);
        ESPECIALIDAD.setText(this.esp);
        DIRECCION.setText(this.dir);

        final Resources res = getResources();

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((NOMBRE.getText().length()!=0) &&
                        (ESPECIALIDAD.getText().length()!=0)){


                    nombre = NOMBRE.getText().toString();
                    especialidad = ESPECIALIDAD.getText().toString();
                    direccion = DIRECCION.getText().toString();

                    if(c!=null) {
                        dbOp = new DatabaseOperations(c);
                        SQLiteDatabase db = dbOp.getWritableDatabase();

                        id=nombre+especialidad;

                        if (db != null) {

                            final ArrayList<Medico> medicos = new ArrayList<Medico>();

                            //Obtener medicos de la BD
                            cursor = dbOp.cargarCursorMedicos();
                            if (cursor.moveToFirst()) {
                                do {
                                    String i = cursor.getString(0);
                                    String n = cursor.getString(1);
                                    String e = cursor.getString(2);
                                    String d = cursor.getString(3);

                                    medicos.add(new Medico(n,e,d,i));

                                } while (cursor.moveToNext());
                            }
                            //Mirar si en la base de datos existe un medico con ese id
                            int i=0;
                            boolean existe=false;
                            String idAnt=nom+esp;
                            if(idAnt.compareTo(id)!=0) {
                                while (i < medicos.size() && existe == false) {
                                    if (id.compareTo(medicos.get(i).getId()) == 0) {
                                        Toast.makeText(context, res.getString(R.string.ErrorRepe), Toast.LENGTH_LONG).show();                                    existe=true;
                                        existe = true;
                                    }
                                    i++;
                                }
                            }
                            if(!existe){
                                ContentValues cv = new ContentValues();

                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_ID, id);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_NOMBRE, nombre);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_DIRECCION, direccion);
                                cv.put(TableData.TableInfoMedico.COLUMN_NAME_ESPECIALIDAD, especialidad);

                                String col=TableData.TableInfoMedico.COLUMN_NAME_ID;
                                String aux=col+"='"+idAnt+"'";

                                db.update(TableData.TableInfoMedico.TABLE_NAME_MEDICO, cv, aux, null);
                                Log.d("Operaciones bases de datos", "Actualizada una fila");

                                db.close();

                                //CODIGO QUE MANDA A VISTA LISTA MEDICOS
                                /*FragmentManager fm = getFragmentManager();
                                fm.beginTransaction()
                                        .replace(R.id.container, new Lista_medico())
                                        .commit();*/
                                Intent inten = new Intent(context, Lista_medico.class);
                                startActivity(inten);

                                Toast.makeText(context, res.getString(R.string.Editado), Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    }
                    else{
                        Log.d("error", "else");
                    }
                }
                else{
                    Toast.makeText(context, res.getString(R.string.Error), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
