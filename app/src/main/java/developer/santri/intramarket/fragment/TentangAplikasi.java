package developer.santri.intramarket.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import developer.santri.intramarket.R;


public class TentangAplikasi extends Fragment {
    @Override

    public View onCreateView(LayoutInflater inflater ,ViewGroup container,Bundle saved) {
        View v =inflater.inflate(R.layout.fragment_tentang_aplikasi,null);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return v;
    }
}
