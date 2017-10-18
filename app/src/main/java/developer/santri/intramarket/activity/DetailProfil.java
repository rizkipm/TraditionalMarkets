package developer.santri.intramarket.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
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
import developer.santri.intramarket.libraryJSON.ImageLoader;

public class DetailProfil extends AppCompatActivity {
    public ImageLoader imageLoader;
    {
        imageLoader = new ImageLoader(null);
    }
    JSONArray json = null;
    String id;
    private ProgressDialog progressDialog;
   developer.santri.intramarket.libraryJSON.JSONParser jsonParser = new developer.santri.intramarket.libraryJSON.JSONParser();
    public static final String TAG_ID ="idpasar";
    public static final String TAG_JUDUL ="namapasar";
    public static final String TAG_GAMBAR ="gambarpasar";
    public static final String TAG_DES ="deskripsipasar";

    public static final String TAG_Longitude ="logitude";
    public static final String TAG_Latitude ="lutitude";
    //public static final String url_detail_berita ="http://200.100.1.175/berita/detailBerita.php";

    GoogleMap myMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdaftar);


        Intent i = getIntent();
        id = i.getStringExtra(TAG_ID);

        new TampilDetailBerita().execute();
    }
    class TampilDetailBerita extends AsyncTask<String,String,String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(DetailProfil.this);
            progressDialog.setMessage("Loading.....");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            try{
                List<NameValuePair> parampa = new ArrayList<>();
                parampa.add(new BasicNameValuePair("idpasar", id));
                JSONObject jsonObject = jsonParser.makeHttpRequest(config.URL_DETAIL_PROFIL,"GET", parampa);
                json = jsonObject.getJSONArray("profilpasar");

                Log.v("Loading : ", ">" + json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView img = (ImageView) findViewById(R.id.gambar);
                        TextView judul = (TextView) findViewById(R.id.judul);
                        //TextView des = (TextView) findViewById(R.id.deskripsi);
                        TextView isi = (TextView) findViewById(R.id.isi);

                        myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps))
                                .getMap();

                        try {
                            JSONObject ob = json.getJSONObject(0);
                            String judul_mama = ob.getString(TAG_JUDUL);
                            String isi_mama = ob.getString(TAG_DES);
                            String deskripsi_mama = ob.getString(TAG_DES);
                            Double lat1 = Double.parseDouble(ob.getString(TAG_Latitude));
                            Double lat2 = Double.parseDouble(ob.getString(TAG_Longitude));

                            LatLng latLng = new LatLng(lat1,lat2);

                            myMap.getUiSettings().setZoomControlsEnabled(true);
                            myMap.getUiSettings().setCompassEnabled(true);
                            myMap.getUiSettings().setMyLocationButtonEnabled(true);
                            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

                            Marker marker = myMap.addMarker(new MarkerOptions().position(latLng)
                                    .title(judul_mama)
                                    .snippet(deskripsi_mama));


                            judul.setText(judul_mama);
                            isi.setText(deskripsi_mama);
                            isi.setText(isi_mama);
                            imageLoader.DisplayImage(config.URL_GAMBAR + ob.getString(TAG_GAMBAR), img);
                        } catch (JSONException e) {
                        }
                    }
                });
            }catch (JSONException e){
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();

        }
    }
}