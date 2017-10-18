//package developer.santri.intramarket.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import developer.santri.intramarket.activity.MainActivity;
//import developer.santri.intramarket.R;
//import developer.santri.intramarket.libraryJSON.ImageLoader;
//
//
//public class ListDaftar extends BaseAdapter {
//
//    private Activity activity;
//    private ArrayList<HashMap<String, String>> data;
//    private static LayoutInflater inflater = null;
//    public ImageLoader imageLoader;
//
//    public ListDaftar(Activity a, ArrayList<HashMap<String, String>> d) {
//        activity = a;
//        data = d;
//        inflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        imageLoader = new ImageLoader(activity.getApplicationContext());
//    }
//
//    public int getCount() {
//        return data.size();
//    }
//
//    public Object getItem(int position) {
//        return position;
//    }
//
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View vi = convertView;
//        if (convertView == null)
//            vi = inflater.inflate(R.layout.listdaftar, null);
//
////        TextView id_berita = (TextView) vi.findViewById(R.id.id1);
////        TextView judul = (TextView) vi.findViewById(R.id.judul1);
////        ImageView thumb_image = (ImageView) vi.findViewById(R.id.gambar1);
////        TextView tanggal = (TextView)vi.findViewById(R.id.tanggal1);
//
//        HashMap<String, String> daftar_utama = new HashMap<String, String>();
//        daftar_utama = data.get(position);
//
////        id_berita.setText(daftar_utama.get(MainActivity.TAG_ID));
////        judul.setText(daftar_utama.get(MainActivity.TAG_JUDUL));
////        tanggal.setText(daftar_utama.get(MainActivity.TAG_TANGGAL));
////        imageLoader.DisplayImage(daftar_utama.get(MainActivity.TAG_GAMBAR),thumb_image);
//        return vi;
//    }
//}