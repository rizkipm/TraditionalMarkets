package developer.santri.intramarket.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
import developer.santri.intramarket.libraryJSON.JSONParser;

public class detaildaftar extends AppCompatActivity {

    public ImageLoader imageLoader;
    {

        imageLoader = new ImageLoader(null);
    }


    JSONArray string_json = null;

    String idpasar;


    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    public static final String TAG_ID = "idpasar";
    public static final String TAG_NAMA = "namapasar";
    public static final String TAG_GAMBAR = "gambarpasar";
    public static final String TAG_DESKRIPSI = "deskripsipasar";
    public static final String TAG_LONGI = "longitude";
    public static final String TAG_LOTI = "lotitude";
    //    private static final String url_detail_berita = "http://200.100.1.216/kabarpasar/detailberita.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpasar);

        Intent i = getIntent();

        idpasar = i.getStringExtra(TAG_ID);

        //Toast.makeText(getApplicationContext(), "id berita = " + idberita, Toast.LENGTH_SHORT).show();


        new AmbilDetailBerita().execute();

    }

    class AmbilDetailBerita extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(detaildaftar.this);
            pDialog.setMessage("Mohon Tunggu ... !");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... params) {

            try {

                List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("idpasar",idpasar));

                JSONObject json = jsonParser.makeHttpRequest(
                        config.URL_DETAIL_DAFTAR, "GET", params1);
                string_json = json.getJSONArray("pasar");

                runOnUiThread(new Runnable() {
                    public void run() {

                        ImageView thumb_image = (ImageView)
                                findViewById(R.id.imageView2);

                       // TextView judul = (TextView) findViewById(R.id.judul1);
                        //  TextView detail = (TextView) findViewById(R.id.detail);
                        TextView deskripsi = (TextView) findViewById(R.id.deskripsi2);
                        TextView tanggal = (TextView)findViewById(R.id.tanggal2);
                        TextView longi = (TextView)findViewById(R.id.longitude);
                        TextView loti = (TextView)findViewById(R.id.lotitude);

                        try {
                            // ambil objek member pertama dari JSON Array
                            JSONObject ar = string_json.getJSONObject(0);
//                            String judul_d = ar.getString("idpasar");
                            //String isi_d = config.URL_GAMBAR + ar.getString("gambar");
                            String des_d = ar.getString("namapasar");
                            String tanggal_d = ar.getString("deskripsipasar");
                            String logitt = ar.getString("logitude");
                            String luti = ar.getString("lutitude");

                           // judul.setText(judul_d);
                            //detail.setText(detail_d);
                            deskripsi.setText(des_d);
                            tanggal.setText(tanggal_d);
                            longi.setText(logitt);
                            loti.setText(luti);

                            imageLoader.DisplayImage(config.URL_GAMBAR + ar.getString(TAG_GAMBAR),thumb_image);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
        }
    }

    public void keluar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Ingin" + " keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

}



