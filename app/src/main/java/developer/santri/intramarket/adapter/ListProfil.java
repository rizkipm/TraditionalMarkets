package developer.santri.intramarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import developer.santri.intramarket.R;
import developer.santri.intramarket.activity.DaftarPasar;
import developer.santri.intramarket.libraryJSON.ImageLoader;


public class ListProfil extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public ListProfil(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_profil, null);

        TextView id_berita = (TextView) vi.findViewById(R.id.id2);
        TextView judul = (TextView) vi.findViewById(R.id.judul2);
        TextView des = (TextView) vi.findViewById(R.id.tanggal2);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.gambar2);

        HashMap<String, String> daftar_berita = new HashMap<String, String>();
        daftar_berita = data.get(position);

        id_berita.setText(daftar_berita.get(DaftarPasar.TAG_ID));
    	des.setText(daftar_berita.get(DaftarPasar.TAG_jalan));
        judul.setText(daftar_berita.get(DaftarPasar.TAG_JUDUL));

        imageLoader.DisplayImage(daftar_berita.get(DaftarPasar.TAG_GAMBAR),thumb_image);

        return vi;
    }
}