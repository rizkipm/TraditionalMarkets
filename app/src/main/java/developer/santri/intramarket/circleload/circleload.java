package developer.santri.intramarket.circleload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

import developer.santri.intramarket.R;
import developer.santri.intramarket.intro.ZoomAnimation;


public class circleload extends Activity {

    private AnimatedCircleLoadingView animatedCircleLoadingView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_circle);


//        textView = (TextView)findViewById(R.id.btnNextCircle);
//
//
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(getApplicationContext(),
//                        ZoomAnimation.class);
//                startActivity(a);
//
//                textView.setVisibility(View.INVISIBLE);
//                textView.setVisibility(View.GONE);
//
//                finish();
//
//            }
//
//        });
        animatedCircleLoadingView = (AnimatedCircleLoadingView) findViewById(R.id.circle_loading_view);
        startLoading();

        startPercentMockThread();


    }

    private void startLoading() {
        animatedCircleLoadingView.startDeterminate();
    }

    private void startPercentMockThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(65);
                        changePercent(i);

                        if (i==100){
                            Intent a = new Intent(getApplicationContext(),
                                    ZoomAnimation.class);
                            startActivity(a);
                            finish();
                        }
                        //textView.setVisibility(View.VISIBLE);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();

    }

    private void changePercent(final int percent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.setPercent(percent);
            }
        });


    }

    public void resetLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.resetLoading();
            }
        });
    }
}