package developer.santri.intramarket.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import developer.santri.intramarket.HargaBarang.Bulanan;
import developer.santri.intramarket.HargaBarang.Mingguan;
import developer.santri.intramarket.R;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class HargaBarangFrag extends AppCompatActivity implements MaterialTabListener{

    private MaterialTabHost tabHost;
    private ViewPager pager;
    private ViewPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga_barang2);


        setUpView();


    }

    public void setUpView(){
        tabHost = (MaterialTabHost)findViewById(R.id.tabHost);
        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //looping untuk menampilkan tab

        for (int i=0; i<adapter.getCount(); i++){
            tabHost.addTab(tabHost.newTab().setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );
            //add listener tab to tab

        }
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0){
                return new Bulanan();
            }else{
                return new Bulanan();


            }

        }

        @Override
        public int getCount() {
            return 2;
        }
        public CharSequence getPageTitle(int position){
            if(position ==0){
                return "MINGGUAN";
            }else{
                return "BULANAN";
            }

        }
    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

}
