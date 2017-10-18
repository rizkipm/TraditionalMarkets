package developer.santri.intramarket.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import developer.santri.intramarket.R;
import developer.santri.intramarket.adapter.ListProfil;
import developer.santri.intramarket.config.config;
import developer.santri.intramarket.libraryJSON.JSONParser;


public class DaftarPasar extends AppCompatActivity {
    JSONParser jsonParser = new JSONParser();
    private ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> DaftarBerita = new ArrayList<HashMap<String, String>>();
    // private static String url_berita ="http://200.100.1.175/berita/getBerita.php";
    public static final String TAG_ID ="idpasar";
    public static final String TAG_JUDUL ="namapasar";
    //    public static final String TAG_DES ="isi";
    public static final String TAG_GAMBAR ="gambarpasar";
    public static final String TAG_Longitude ="logitude";
    public static final String TAG_Latitude ="lutitude";
    public static final String TAG_jalan ="deskripsipasar";



    JSONArray json = null;
    ListView ls;
    ListProfil adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);




        DaftarBerita = new ArrayList<HashMap<String, String>>();
        new AmbilData().execute();

        ls = (ListView)findViewById(R.id.list);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = DaftarBerita.get(position);

                //Memanggail class detail berita dan mengirim data ID dan Gambar
                Intent a = new Intent(getApplicationContext(), DetailProfil.class);
                a.putExtra(TAG_ID, map.get(TAG_ID));
                a.putExtra(TAG_GAMBAR, map.get(TAG_GAMBAR));
                startActivity(a);

            }
        });
    }
    public void SetListViewAdapter(ArrayList<HashMap<String, String >> info){
        adapter = new ListProfil(this, info);
        ls.setAdapter(adapter);
    }
    class AmbilData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(DaftarPasar.this);
            progressDialog.setMessage("Loading.....");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> parampa = new ArrayList<NameValuePair>();
            JSONObject jsonObject = jsonParser.makeHttpRequest(config.URL_PROFIL, "GET", parampa);
            Log.i("Nilai JSONnya : ", " " + jsonObject);
            try{
                json = jsonObject.getJSONArray("pasar");
                for(int i =0; i <json.length(); i++)
                {
                    JSONObject c = json.getJSONObject(i);

                    String id_info = c.getString(TAG_ID);
                    String judul = c.getString(TAG_JUDUL);
//                    String deskripsi = c.getString(TAG_DES);
                    String gbr = config.URL_GAMBAR + c.getString(TAG_GAMBAR);

                    HashMap<String, String> map = new HashMap<String,String>();
                    map.put(TAG_ID, id_info);
//                    map.put(TAG_DES, deskripsi);
                    map.put(TAG_JUDUL, judul);
                    map.put(TAG_GAMBAR, gbr);

                    DaftarBerita.add(map);
                }
            }catch (JSONException e){
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SetListViewAdapter(DaftarBerita);
                    //mendapatkan waktu sekarang
                    //               Calendar kal = Calendar.getInstance();
                    //             SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                               String formatDate = fr.format(kal.getTime());
                             TextView update = (TextView)findViewById(R.id.update);

                       update.setText("Daftar Pasar : " );//+ formatDate);
                }

            });
        }
    }







}
