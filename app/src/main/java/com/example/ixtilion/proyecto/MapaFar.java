package com.example.ixtilion.proyecto;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapaFar extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //LARDERO
    public static final LatLng Farmacia_Lardero_1 = new LatLng(42.427069, -2.457645);
    public static final LatLng Farmacia_Lardero = new LatLng(42.427797, -2.461100);
    public static final LatLng Farmacia_Lardero_2 = new LatLng(42.436800, -2.455254);
    //LOGROÑO
    public static final LatLng Farmacia_Logroño_1 = new LatLng(42.444802, -2.450890);
    public static final LatLng Farmacia_Logroño_2 = new LatLng(42.467615, -2.437706);
    public static final LatLng Farmacia_Logroño_3 = new LatLng(42.463229, -2.438347);
    public static final LatLng Farmacia_Logroño_4 = new LatLng(42.460959, -2.457071);
    public static final LatLng Farmacia_Logroño_5 = new LatLng(42.461143, -2.454676);
    public static final LatLng Farmacia_Logroño_6 = new LatLng(42.462819, -2.439891);
    public static final LatLng Farmacia_Logroño_7 = new LatLng(42.456718, -2.451202);
    public static final LatLng Farmacia_Logroño_8 = new LatLng(42.465951, -2.447463);
    public static final LatLng Farmacia_Logroño_9 = new LatLng(42.466405, -2.442904);
    public static final LatLng Farmacia_Logroño_10 = new LatLng(42.460927, -2.440632);
    public static final LatLng Farmacia_Logroño_11 = new LatLng(42.464180, -2.449621);
    public static final LatLng Farmacia_Logroño_12 = new LatLng(42.458756, -2.446651);
    public static final LatLng Farmacia_Logroño_13 = new LatLng(42.463747, -2.432461);
    public static final LatLng Farmacia_Logroño_14 = new LatLng(42.463576, -2.461850);
    public static final LatLng Farmacia_Logroño_15 = new LatLng(42.464766, -2.453613);
    public static final LatLng Farmacia_Logroño_16 = new LatLng(42.455651, -2.438306);
    public static final LatLng Farmacia_Logroño_17 = new LatLng(42.465939, -2.447594);
    public static final LatLng Farmacia_Logroño_18 = new LatLng(42.467209, -2.461623);

    //CALAHORRA
    public static final LatLng Farmacia_Calahorra_1 = new LatLng(42.309187, -1.970870);
    public static final LatLng Farmacia_Calahorra_2 = new LatLng(42.302871, -1.973574);
    public static final LatLng Farmacia_Calahorra_3 = new LatLng(42.307441, -1.969283);
    public static final LatLng Farmacia_Calahorra_4 = new LatLng(42.303188, -1.966064);
    public static final LatLng Farmacia_Calahorra_5 = new LatLng(42.303760, -1.963832);
    public static final LatLng Farmacia_Calahorra_6 = new LatLng(42.301665, -1.960957);
    public static final LatLng Farmacia_Calahorra_7 = new LatLng(42.308241, -1.966020);
    public static final LatLng Farmacia_Calahorra_8 = new LatLng(42.305147, -1.968273);

    //SAN ADRIAN
    public static final LatLng Farmacia_SanAdrian_1 = new LatLng(42.337621, -1.935516);
    public static final LatLng Farmacia_SanAdrian_2 = new LatLng(42.334749, -1.935879);
    public static final LatLng Farmacia_SanAdrian_3 = new LatLng(42.336970, -1.939248);
    public static final LatLng Farmacia_SanAdrian_4 = new LatLng(42.334955, -1.931996);
    public static final LatLng Farmacia_SanAdrian_5 = new LatLng(42.332846, -1.932983);
    public static final LatLng Farmacia_SanAdrian_6 = new LatLng(42.333861, -1.934463);

    //ALFARO
    public static final LatLng Farmacia_Alfaro_1 = new LatLng(42.179128, -1.749449);
    public static final LatLng Farmacia_Alfaro_2 = new LatLng(42.177454, -1.750254);
    public static final LatLng Farmacia_Alfaro_3 = new LatLng(42.175633, -1.750898);

    //LUGO
    public static final LatLng Farmacia_Lugo_1 = new LatLng(43.024050, -7.566285);
    public static final LatLng Farmacia_Lugo_2 = new LatLng(43.002461, -7.545342);
    public static final LatLng Farmacia_Lugo_3 = new LatLng(43.010432, -7.557273);

    //CORUÑA
    public static final LatLng Farmacia_Corunia_1 = new LatLng(43.350502, -8.404030);
    public static final LatLng Farmacia_Corunia_2 = new LatLng(43.363420, -8.407291);
    public static final LatLng Farmacia_Corunia_3 = new LatLng(43.362328, -8.422741);

    public static final LatLng Farmacia_Ourense_1 = new LatLng(42.340772, -7.873009);
    public static final LatLng Farmacia_Ourense_2 = new LatLng(42.345428, -7.861733);

    public static final LatLng Farmacia_Asturias_1 = new LatLng(43.516926, -5.677284);

    //MADRID
    public static final LatLng Farmacia_Madrid_1 = new LatLng(40.421372, -3.676545);
    public static final LatLng Farmacia_Madrid_2 = new LatLng(40.421220, -3.677365);
    public static final LatLng Farmacia_Madrid_3 = new LatLng(40.423518, -3.67189);


    //BARCELONA
    public static final LatLng Farmacia_Bcna_1 = new LatLng(41.395869, 2.155594);
    public static final LatLng Farmacia_Bcna_2 = new LatLng(41.393294, 2.143149);
    public static final LatLng Farmacia_Bcna_3 = new LatLng(41.371784, 2.181731);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_far);
        setUpMapIfNeeded();

        //BARCELONA
        setMarker(Farmacia_Bcna_1,"Farmacia -Sierra Mandri, ","Avinguda Diagonal, 478\n" +
                "Barcelona\n" +
                "934 16 12 70");

        setMarker(Farmacia_Bcna_2,"Farmacia - De la Iglesia Martinez, Mº Jose","Calle del Mestre Nicolau, 6\n" +
                "Barcelona\n" +
                "932 01 44 95");

        setMarker(Farmacia_Bcna_3,"Farmacia - Vanesa Fernanez Leon","Edificio World Trade Center\n" +
                "Barcelona\n" +
                "935 08 80 32");


        //LARDERO
        setMarker(Farmacia_Lardero_1,"Farmacia - Martínez Tutor, M Jesús","Calle Nocedillo, 22\n" +
                "Lardero\n" +
                "941 44 88 04");

        setMarker(Farmacia_Lardero,"Farmacia - María Gayo Otero","Calle de González Gallarza, 62\n" +
                "Lardero\n" +
                "941 45 20 38");

        setMarker(Farmacia_Lardero_2,"Farmacia - Diez Sampedro","Calle Manuel de Falla, 77\n" +
                "Lardero\n" +
                "941 49 92 23");

        //LOGROÑO
        setMarker(Farmacia_Logroño_1,"Farmacia - Alberdi Marquiegui","Av. de Madrid, 135\n" +
                "Logroño\n" +
                "941 20 71 37");

        setMarker(Farmacia_Logroño_2,"Farmacia - Acedo Martinez, Mª. C.","Calle Doce Ligero de Artilleria, 4\n" +
                "Logroño\n" +
                "941 23 06 67");

        setMarker(Farmacia_Logroño_3,"Farmacia - Alfredo Heredia Esteban","Calle Marques de La Ensenada, 11\n" +
                "Logroño\n" +
                "941 23 68 28");

        setMarker(Farmacia_Logroño_4,"Farmacia - Amaia Canales Leza","Calle Huesca, 53, 55\n" +
                "Logroño\n" +
                "941 22 62 50");

        setMarker(Farmacia_Logroño_5,"Farmacia - Antonio Migu Prior Molto","Calle Chile, 23\n" +
                "Logroño\n" +
                "941 22 94 90");

        setMarker(Farmacia_Logroño_6,"Farmacia - Ascension Maria Cruz Puras Villaro","Avenida Colon, 27\n" +
                "Logroño\n" +
                "941 23 91 19");

        setMarker(Farmacia_Logroño_7,"Farmacia - Beatriz Sabras Dulin","Av. República Argentina, 64\n" +
                "Logroño\n" +
                "941 28 88 50");

        setMarker(Farmacia_Logroño_8,"Farmacia - Carlos Martinez Gil","Calle Hermanos Moroy, 28\n" +
                "Logroño\n" +
                "941 25 10 65");

        setMarker(Farmacia_Logroño_9,"Farmacia - Carmen García Carbonell","Av. de la Paz, 1\n" +
                "Logroño\n" +
                "941 24 88 01");

        setMarker(Farmacia_Logroño_10,"Farmacia - Carmen Hierro Alonso","Calle Villamediana, 19\n" +
                "Logroño\n" +
                "941 23 29 17");

        setMarker(Farmacia_Logroño_11,"Farmacia - F S Martínez Farmaceuticos Sc","Av. Gran Vía Rey Juan Carlos I, 26B\n" +
                "Logroño\n" +
                "941 22 57 05");

        setMarker(Farmacia_Logroño_12,"Farmacia - Adan Martinez","Calle General Vara del Rey, 58\n" +
                "Logroño\n" +
                "941 23 66 91");

        setMarker(Farmacia_Logroño_13,"Farmacia - Fraile Gallo","Av. de la Paz, 88\n" +
                "Logroño\n" +
                "941 23 44 55");

        setMarker(Farmacia_Logroño_14,"Farmacia - Hierro Alonso","Calle Marques de Murrieta, 78\n" +
                "Logroño\n" +
                "941 22 08 01");

        setMarker(Farmacia_Logroño_15,"Farmacia - Ldo. Jose Antonio Gonzalez","Av de la Gran Vía Rey Juan Carlos I, 67\n" +
                "Logroño\n" +
                "941 22 03 32");

        setMarker(Farmacia_Logroño_16,"Farmacia - Mercedes","Calle Estambrera, 22\n" +
                "Logroño\n" +
                "941 50 09 16");

        setMarker(Farmacia_Logroño_17,"Farmacia - Rodriguez Maimon Torrijo","Calle Capitan Gallarza, 4\n" +
                "Logroño\n" +
                "941 25 19 14");

        setMarker(Farmacia_Logroño_18,"Farmacia - Ruiz de Clavijo Ballugera","Calle Gonzalo de Berceo, 54\n" +
                "Logroño\n" +
                "941 21 31 76");

        //CALAHORRA
        setMarker(Farmacia_Calahorra_1,"Farmacia - José María Domingo Pérez Caballero","Calle de los Doctores Castroviejo, 19\n" +
                "Calahorra\n" +
                "941 14 69 58");
        setMarker(Farmacia_Calahorra_2,"Farmacia - Pagola Sáenz","Av de Numancia, 80\n" +
                "Calahorra\n" +
                "941 13 03 38");
        setMarker(Farmacia_Calahorra_3,"Farmacia - M. A. Seminario","Calle de Julio Longinos, 25\n" +
                "Calahorra\n" +
                "941 13 01 48");
        setMarker(Farmacia_Calahorra_4,"Farmacia -  Mercedes Peña Hurtado","Avenida Numancia, 6\n" +
                "Calahorra\n" +
                "941 13 24 29");
        setMarker(Farmacia_Calahorra_5,"Farmacia - Diego Ameyugo González","Paseo del Mercadal, 12\n" +
                "Calahorra\n" +
                "941 13 00 99");
        setMarker(Farmacia_Calahorra_6,"Farmacia - Marta Lázaro","Plaza el Raso, 2\n" +
                "Calahorra\n" +
                "941 14 54 50");
        setMarker(Farmacia_Calahorra_7,"Farmacia - Dr. Vázquez Lasa","Av. Ntra. Sra. de Valvanera, 54\n" +
                "Calahorra\n" +
                "941 13 11 90");
        setMarker(Farmacia_Calahorra_8,"Farmacia - Santiago Piñeiro Marín","Calle del Bebricio, 53\n" +
                "Calahorra\n" +
                "941 13 16 36");

        //S.A.
        setMarker(Farmacia_SanAdrian_1,"Farmacia - Olga Gimeno","Ctra. Estella, 77\n" +
                "San Adrián\n" +
                "948 69 62 85");
        setMarker(Farmacia_SanAdrian_2,"Farmacia - Moreno Sola","Calle de Sta Gema, 30\n" +
                "San Adrián\n" +
                "948 67 01 11");
        setMarker(Farmacia_SanAdrian_3,"Farmacia - Miguel Sola Manero","Grupo San José Obrero, 71\n" +
                "San Adrián\n" +
                "948 69 66 67");
        setMarker(Farmacia_SanAdrian_4,"Farmacia - Ldo Javier Arce","Calle Pelayo Sola, 10\n" +
                "San Adrián\n" +
                "948 67 00 98");
        setMarker(Farmacia_SanAdrian_5,"Farmacia - Milian Grao","Plaza Vera Magallón, 8\n" +
                "San Adrián\n" +
                "948 67 00 42");
        setMarker(Farmacia_SanAdrian_6,"Farmacia - María Rosario Manero Sánchez","Ctra. Estella, 18\n" +
                "San Adrián\n" +
                "948 67 00 99");

        //ALFARO
        setMarker(Farmacia_Alfaro_1,"Farmacia - Twose Navajas","Calle de las Pozas, 3\n" +
                "Alfaro\n" +
                "941 18 01 12");
        setMarker(Farmacia_Alfaro_2,"Farmacia - Boyra Navea","Calle Mayor, 5\n" +
                "Alfaro\n" +
                "941 18 09 88");
        setMarker(Farmacia_Alfaro_3,"Farmacia - María Carmen Bretón Espinosa","Calle Trasmuro, 11\n" +
                "Alfaro\n" +
                "941 18 33 25");

        //LUGO
        setMarker(Farmacia_Lugo_1,"Farmacia - Labandeira","Av de La Coruña, 299\n" +
                "Lugo\n" +
                "982 21 54 00");
        setMarker(Farmacia_Lugo_2,"Farmacia - Pintos","Ronda das Fontiñas, 302\n" +
                "Lugo\n" +
                "982 22 45 18");
        setMarker(Farmacia_Lugo_3,"Farmacia - da Cruz","Rúa Conde Pallares, 6\n" +
                "Lugo\n" +
                "982 23 11 60");

        //CORUÑA
        setMarker(Farmacia_Corunia_1,"Farmacia - Fra Carracedo","Av del Alcalde Pérez Arda, 51\n" +
                "A Coruña\n" +
                "981 29 60 61");
        setMarker(Farmacia_Corunia_2,"Farmacia - Gato Luaces","Calle de Emilia Pardo Bazán, 12\n" +
                "A Coruña\n" +
                "981 12 15 80");
        setMarker(Farmacia_Corunia_3,"Farmacia - Pérez Jares","Ronda de Outeiro, 261\n" +
                "A Coruña\n" +
                "981 25 20 29");

        setMarker(Farmacia_Ourense_1,"Farmacia - López Domínguez","Calle de Ervedelo, 82\n" +
                "Ourense\n" +
                "988 22 21 13");
        setMarker(Farmacia_Ourense_2,"Farmacia - Piña González","Calle de Curros Enríquez, 10\n" +
                "Ourense\n" +
                "988 23 30 21");

        setMarker(Farmacia_Asturias_1,"Farmacia - de Roces","Av. Salvador Allende, 69\n" +
                "Asturias\n" +
                "985 38 63 67");


         //MADRID
        setMarker(Farmacia_Madrid_1,"Farmacia - de Bobadilla","Calle Bobadilla, 81\n" +
                "Madrid\n" +
                "917 10 89 94");

        setMarker(Farmacia_Madrid_2,"Farmacia - Muriel","Muriel Romano, 363\n" +
                "Asturias\n" +
                "917 23 87 63");

        setMarker(Farmacia_Asturias_1,"Farmacia - Optica Online","Madrid Velez, 19\n" +
                "Madrid\n" +
                "917 83 77 21");

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                //setUpMap();
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.setMyLocationEnabled(true);
            }
        }
    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); //Color del marcador
    }
}
