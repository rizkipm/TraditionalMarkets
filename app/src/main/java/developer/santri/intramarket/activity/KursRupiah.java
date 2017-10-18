package developer.santri.intramarket.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import developer.santri.intramarket.R;


public class KursRupiah extends Fragment {
    WebView wb;
    ProgressBar progressBar;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved) {
        View v = inflater.inflate(R.layout.fragment_kurs_rupiah, null);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        wb = (WebView) v.findViewById(R.id.webViewApp);
        progressBar=(ProgressBar)v.findViewById(R.id.progressBar2);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());


        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wb.setHorizontalScrollBarEnabled(false);
        wb.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wb.getSettings().setAllowFileAccessFromFileURLs(true);
        wb.getSettings().setAllowUniversalAccessFromFileURLs(true);

        wb.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        wb.loadUrl("https://www.mataf.net/id/currency/converter");
        return v;
    }
}

