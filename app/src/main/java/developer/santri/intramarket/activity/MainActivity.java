package developer.santri.intramarket.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import developer.santri.intramarket.fragment.HargaBarangFrag;
import developer.santri.intramarket.R;
import developer.santri.intramarket.adapter.ListUtama;
import developer.santri.intramarket.config.config;
import developer.santri.intramarket.libraryJSON.JSONParser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog pDialog;

    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> DaftarBerita = new ArrayList<HashMap<String, String>>();

    //private static String url_berita = "http://12.12.12.188/kabarpasar/pasar.php";

    public static final String TAG_ID= "idinfo";
    public static final String TAG_JUDUL = "judul";
    public static final String TAG_GAMBAR = "gambar";
    public static final String TAG_DESKRIPSI = "deskripsi";
    public static final String TAG_TANGGAL = "tanggal";



    JSONArray string_json = null;

    ListView list;
    ListUtama adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DaftarBerita = new ArrayList<HashMap<String, String>>();

        new AmbilData().execute();

        list = (ListView) findViewById(R.id.list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                HashMap<String, String> map = DaftarBerita.get(position);

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), DetailUtama.class);

                in.putExtra(TAG_ID, map.get(TAG_ID));
                in.putExtra(TAG_GAMBAR, map.get(TAG_GAMBAR));

                startActivity(in);
            }
        });
    }

    public void SetListViewAdapter(ArrayList<HashMap<String, String>> informasi) {
        adapter = new ListUtama(this, informasi);
        list.setAdapter(adapter);
    }


    class AmbilData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            JSONObject json = jParser.makeHttpRequest(config.URL_BERITA, "GET", params);
            Log.i("Ini nilai json ", ">" + json);

            try {


                string_json = json.getJSONArray("informasi");

                for (int i = 0; i < string_json.length(); i++) {
                    JSONObject c = string_json.getJSONObject(i);

                    String id_berita = c.getString(TAG_ID);
                    String judul = c.getString(TAG_JUDUL);
                    String link_image = config.URL_GAMBAR + c.getString(TAG_GAMBAR);
                    String tanggal = c.getString(TAG_TANGGAL);
                    String info = c.getString(TAG_DESKRIPSI);

                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_ID, id_berita);
                    map.put(TAG_JUDUL, judul);
                    map.put(TAG_GAMBAR, link_image);
                    map.put(TAG_TANGGAL,tanggal);
                    map.put(TAG_DESKRIPSI,info);

                    DaftarBerita.add(map);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();

            runOnUiThread(new Runnable() {
                public void run() {

                    SetListViewAdapter(DaftarBerita);

                    //Update Time..`

					// Current Date
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String formattedDate = df.format(c.getTime());

                    TextView updateTime = (TextView) findViewById(R.id.update);
                    updateTime.setText("Seputar pasar");

                }
            });

        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftr = fm.beginTransaction();

        if (id == R.id.nav_camara) {
            Intent e= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(e);


        } else if (id == R.id.nav_gallery) {
        Intent a= new Intent(getApplicationContext(),DaftarPasar.class);
            startActivity(a);


        } else if (id == R.id.nav_slideshow) {
            Intent b = new Intent(getApplicationContext(),HargaBarangFrag.class);
            startActivity(b);

//

        } else if (id == R.id.nav_manage) {
        Intent c= new Intent(getApplicationContext(),FriendsActivity.class);
            startActivity(c);




        } else if (id == R.id.ayewa){
           KursRupiah aa = new KursRupiah();

            aa .setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.container,aa,"").commit();
            getSupportFragmentManager().popBackStack();
        } else if (id == R.id.nav_share) {
            Intent d =new Intent(getApplicationContext(),LokasiCurrentDatabase.class);
            startActivity(d);


        } else if (id == R.id.nav_send) {
           Intent e = new Intent(getApplicationContext(),Tentang.class);
            startActivity(e);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
