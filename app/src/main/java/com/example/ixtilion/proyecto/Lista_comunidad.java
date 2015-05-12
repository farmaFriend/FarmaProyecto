package com.example.ixtilion.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alumno on 26/03/2015.
 */
public class Lista_comunidad extends Activity {
    EditText COMUNIDAD;
    String com;
    private final Context c = this;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitar_cita);


        final ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
        comunidades.add(new Comunidad("La Rioja","https://www.riojasalud.es/ciudadanos/informacion-general-a-ciudadanos/3756-pedir-cita-previa" ,"larioja"));
        comunidades.add(new Comunidad("Navarra","http://www.navarra.es/home_es/Servicios/ficha/3345/cita-previa-en-el-centro-de-salud","navarra"));
        comunidades.add(new Comunidad("Cataluña","http://www.gencat.cat/ics/usuaris/visites.htm","catalunya"));
        comunidades.add(new Comunidad("Castilla León","https://cita.saludcastillayleon.es/CitaPreviaWeb/start2.htm","castillaleon"));
        comunidades.add(new Comunidad("Asturias","https://www28.asturias.es/solicitudCitaPrevia/action/inicio?method=solicitudCita","asturias"));
        comunidades.add(new Comunidad("Andalucía","https://ws003.juntadeandalucia.es/pls/intersas/servicios.tramite_enlinea_citamedico","andalucia"));
        comunidades.add(new Comunidad("Aragón","https://www.saludinforma.es/portalsi/web/salud/tramites-gestiones/cita-previa","aragon"));
        comunidades.add(new Comunidad("Islas Baleares","https://porpac.ibsalut.es/services/Index.action","baleares"));
        comunidades.add(new Comunidad("Canarias","http://www3.gobiernodecanarias.org/sanidad/scs/gc/18/cita_previa/","canarias"));
        comunidades.add(new Comunidad("Cantabria","https://citaprevia.scsalud.es/","cantabria"));
        comunidades.add(new Comunidad("Castilla La Mancha","http://sescam.castillalamancha.es/ciudadanos/cita-previa","castillamancha"));
        comunidades.add(new Comunidad("Comunidad Valenciana","http://www.san.gva.es/citaprevia","valencia"));
        comunidades.add(new Comunidad("Extremadura","http://www.saludextremadura.com/web/portalsalud/login?param=citaprevia","extremadura"));
        comunidades.add(new Comunidad("Galicia","https://extranet.sergas.es/cita/autenticacion.asp?JS=S&idioma=es","galicia"));
        comunidades.add(new Comunidad("Madrid","https://www.citaprevia.sanidadmadrid.org/Forms/Acceso.aspx","madrid"));
        comunidades.add(new Comunidad("Murcia","https://sms.carm.es/cmap/iniciarReserva.do","murcia"));
        comunidades.add(new Comunidad("País Vasco", "https://www.osanet.euskadi.net/o22War/O22MainCiudadanoNPServlet;jsessionid=TlQ4V6GZwLyNQXPn1vTxXTwlcKYQl32GQdjGkfd2h08WhjhLG1y3!1511787831!-1231398069?loc=O22Txt_citapreviaNuevaCita.jsp&cod_ses=15671584", "paisvasco"));

        final ArrayList<Comunidad> comu=(ArrayList<Comunidad>)comunidades.clone();

        COMUNIDAD = (EditText) findViewById(R.id.tbComunidad);
        ListView list = (ListView)findViewById(R.id.listCitas);
        final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(c, comunidades);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Comunidad item = (Comunidad) parent.getItemAtPosition(position);
                String link = item.getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                startActivity(intent);
            }
        });
        COMUNIDAD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                comu.clear();

                for (int i=0; i<comunidades.size(); i++){
                    String c1 = comunidades.get(i).getName().toUpperCase();
                    String c2 = COMUNIDAD.getText().toString().toUpperCase();
                    if(c1.indexOf(c2)!=-1){
                        comu.add(new Comunidad(comunidades.get(i).getName(),comunidades.get(i).getLink() ,comunidades.get(i).getIco()));
                    }
                }


                ListView list = (ListView)findViewById(R.id.listCitas);
                final ArrayAdapterComunidad adapter = new ArrayAdapterComunidad(c, comu);
                list.setAdapter(adapter);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
