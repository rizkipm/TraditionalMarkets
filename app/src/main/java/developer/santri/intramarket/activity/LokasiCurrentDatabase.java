package developer.santri.intramarket.activity;


import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import developer.santri.intramarket.R;
import developer.santri.intramarket.config.config;
import developer.santri.intramarket.libraryJSON.JSONParser;


/**
 * Created by macbook on 8/5/15.
 */
public class LokasiCurrentDatabase extends FragmentActivity implements LocationListener {

    public static final String TAG_Npsn = "idpasar";
    public static final String TAG_NAMASEKOLAH = "namapasar";
    public static final String TAG_DESK = "deskripsipasar";
    public static final String TAG_LOGI = "logitude";
    public static final String TAG_LOTI = "lutitude";
    public static final String TAG_GAMBAR = "gambarpasar";

    //    = "http://200.100.1.172/daftarpasaar.php";
    JSONArray string_json = null;
    double latitude;
    double longitude;
    String dataLat, dataLot;
    JSONParser jsonParser = new JSONParser();
    GoogleMap googleMap;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        } else {
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            googleMap = fm.getMap();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                onLocationChanged(location);
            }
//            locationManager.requestLocationUpdates(provider, 500000, 0, this);

            new AmbilMaps().execute();

        }

    }


    @Override
    public void onLocationChanged(Location location) {
        //  TextView tvLocation = (TextView)findViewById(R.id.tv_location);
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        dataLat = Double.toString(latitude);
        dataLot = Double.toString(longitude);

        LatLng latLng = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions().position(latLng).title("Posisi Sekarang"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    class Sekolah {
        private String namapasar, deskripsipasar;
        private double logitude, lutitude;

        public String getNamapasar() {
            return namapasar;
        }

        public void setNamapasar(String namapasar) {
            this.namapasar = namapasar;
        }

        public String getDeskripsipasar() {
            return deskripsipasar;
        }

        public void setDeskripsipasar(String deskripsipasar) {
            this.deskripsipasar = deskripsipasar;
        }

        public double getLogitude() {
            return logitude;
        }

        public void setLogitude(double logitude) {
            this.logitude = logitude;
        }

        public double getLutitude() {
            return lutitude;
        }

        public void setLutitude(double lutitude) {
            this.lutitude = lutitude;
        }
    }

    class AmbilMaps extends AsyncTask<String, String, String> {
        private ArrayList<Sekolah> dataSekolah = new ArrayList<>();

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<Marker> markers = new ArrayList<Marker>();

            for (int i = 0; i < dataSekolah.size(); i++) {
                Sekolah x = dataSekolah.get(i);
                Double a12 = x.getLogitude();
                Double a13 = x.getLutitude();
                LatLng nLat = new LatLng(a12, a13);
                Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(x.getLogitude(), x.getLutitude()))
                                .title("Nama Pasar : " + "-" + x.getNamapasar())
                                .snippet(x.getDeskripsipasar() + "," ).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon))

                );
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nLat, 14));
//                googleMap.animateCamera(CameraUpdateFactory.zoomTo(14), 500000, null);
//                ); //...
                markers.add(marker);

                markers.size();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            dataLat = Double.toString(latitude);
            dataLot = Double.toString(longitude);

//            LatLng latLng = new LatLng(latitude, longitude);
//            Toast.makeText(getApplicationContext(),"Latitude = "+ latitude + "Longitude = " +longitude,
//                    Toast.LENGTH_LONG).show();


            List<NameValuePair> params1 = new ArrayList<NameValuePair>();
//            params1.add(new BasicNameValuePair(TAG_LINTANG, dataLat));
//            params1.add(new BasicNameValuePair(TAG_BUJUR, dataLot));

            JSONObject json = jsonParser.makeHttpRequest(
                    config.URL_DATA, "GET", params1);
            Log.i("Ini nilai json ", ">" + json);

            try {
                string_json = json.getJSONArray("pasar");

                for (int i = 0; i < string_json.length(); i++) {

                    Sekolah s = new Sekolah();

                    JSONObject c = string_json.getJSONObject(i);
                    Double lat1 = c.getDouble(TAG_LOGI);
                    Double lat2 = c.getDouble(TAG_LOTI);

                    s.setLutitude(lat1);
                    s.setLogitude(lat2);
                    s.setNamapasar(c.getString(TAG_NAMASEKOLAH));
                    s.setDeskripsipasar((String) c.get(TAG_DESK));
                    //   s.setKota((String) c.get(TAG_kabupaten_kota));
//                    s.setProvinsi((String)c.get(TAG_Provinsi));


                    dataSekolah.add(s);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}