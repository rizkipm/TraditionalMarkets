package developer.santri.intramarket.activity;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import developer.santri.intramarket.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    String dataLat, dataLot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng melayan = new LatLng(-7.5561078, 111.6259908);
        mMap.addMarker(new MarkerOptions().position(melayan).title("Pasar Melayan Baru")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(melayan));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(melayan, 20));


        LatLng arjo = new LatLng(-8.1244287,111.1464163);
        mMap.addMarker(new MarkerOptions().position(arjo).title("Pasar Arjosari Pacitan")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(arjo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arjo, 20));

        LatLng kosambi = new LatLng(-6.9192564,107.6198725);
        mMap.addMarker(new MarkerOptions().position(kosambi).title("Pasar Kosambi Bandung")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kosambi));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kosambi, 20));

        LatLng beringharjo = new LatLng(-7.7989953,110.3649107);
        mMap.addMarker(new MarkerOptions().position(beringharjo).title("Pasar Beringharjo Yogya")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beringharjo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(beringharjo, 20));

        LatLng depok = new LatLng(-6.4030587,106.8313653);
        mMap.addMarker(new MarkerOptions().position(depok).title("Pasar Segar Depok")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(depok));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(depok, 20));

        LatLng klewer = new LatLng(-6.4030587,106.8313653);
        mMap.addMarker(new MarkerOptions().position(klewer).title("Pasar Klewer Solo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(klewer));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(klewer, 20));


        LatLng tanahabang = new LatLng(-6.187712,106.8121491);
        mMap.addMarker(new MarkerOptions().position(tanahabang).title("Pasar Tanah Abang")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tanahabang));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tanahabang, 20));

        LatLng jati = new LatLng(-6.2149736,106.8624098);
        mMap.addMarker(new MarkerOptions().position(jati).title("Pasar Jatinegara")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jati));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jati, 20));

    }

}