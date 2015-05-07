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
<<<<<<< HEAD
    //public static final LatLng Farmacia_Logroño_13 = new LatLng();
=======
>>>>>>> origin/master
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);
    //public static final LatLng Farmacia_Logroño_3 = new LatLng(42.436800, -2.455254);








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_far);
        setUpMapIfNeeded();

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
