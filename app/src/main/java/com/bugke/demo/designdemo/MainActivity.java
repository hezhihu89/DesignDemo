package com.bugke.demo.designdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.bugke.demo.designdemo.baidu.BaiDuLocationListener;
import com.bugke.demo.designdemo.baidu.MHLocationCaballck;
import com.bugke.demo.designdemo.fragment.BaseFragment;
import com.bugke.demo.designdemo.fragment.FragmentPage;
import com.bugke.demo.designdemo.fragment.NewFragment;
import com.bugke.demo.designdemo.fragment.WeathFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements PageAdapter, View.OnClickListener, MHLocationCaballck {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String[][] PAGES = new String[][]{{"Android", "http://p.codekk.com/api/op/page/"},
            {"iOS", "http://p.codekk.com/api/op/page/"},
            {"新闻", "http://3g.163.com/touch/reconstruct/article/list/BA10TA81wangning/"},
            {"天气", "http://api.yytianqi.com/weatherindex?city="},
            {"福利", "http://p.codekk.com/api/op/page/"},
            {"学习", "http://p.codekk.com/api/op/page/"}};

    TabLayout mTabLayout;

    ViewPager mViewpage;
    private FloatingActionButton floatingActionButton;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Map<Integer, BaseFragment> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        arrayList = new HashMap<>();


        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.id_toolbar_layout);
        appBarLayout.setOnClickListener(this);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle("Desing 设计");

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floation);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Snackbar snackbar = Snackbar.make(view, "提示文本", Snackbar.LENGTH_LONG);
                snackbar.show();
                snackbar.setAction("按钮title", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(v, "第二个", Snackbar.LENGTH_LONG);
                        snackbar1.show();
                        snackbar1.setAction("提示", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                });
            }
        });

        mViewpage = (ViewPager) findViewById(R.id.Viewpage);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), this);
        mViewpage.setAdapter(adapter);
        mViewpage.setOffscreenPageLimit(PAGES.length);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mTabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorAccent));
        mTabLayout.setupWithViewPager(mViewpage);
        getLocation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = arrayList.get(position);
        if (null == fragment) {
            switch (position) {
                case 2:
                    fragment = NewFragment.newInstance(PAGES[position][0], PAGES[position][1]);
                    break;
                case 3:
                    fragment = WeathFragment.newInstance(PAGES[position][0], PAGES[position][1]);
                    break;
                default:
                    fragment = FragmentPage.newInstance(PAGES[position][0], PAGES[position][1]);
                    break;
            }
            arrayList.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGES[position][0];
    }

    @Override
    public int getCount() {
        return PAGES.length;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_toolbar_layout:
                arrayList.get(mViewpage.getCurrentItem()).scrollTop();
                break;
        }
    }

    @Override
    public void locationCallBack(BDLocation location, LocationClient client) {
        if(location.getLocType() == 161){
            Snackbar snackbar1 = Snackbar.make(mViewpage, "定位成功: " + location.getAddrStr() , Snackbar.LENGTH_LONG);
            snackbar1.show();
            snackbar1.setAction("重新获取", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getLocations();
                }
            });
            client.stop();
        }
    }

    public void getLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, BaiDuLocationListener.ACCESS_FINE_LOCATION);
                return;
            } else {
                //上面已经写好的拨号方法
                getLocations();
            }
        } else {
            //上面已经写好的拨号方法
            getLocations();
        }
    }

    public void getLocations() {
        BaiDuLocationListener.getIns(this).start(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case BaiDuLocationListener.ACCESS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    getLocations();
                } else {
                    // Permission Denied
                    Snackbar snackbar1 = Snackbar.make(floatingActionButton, "获取权限失败", Snackbar.LENGTH_LONG);
                    snackbar1.show();
                    snackbar1.setAction("重新获取", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getLocation();
                        }
                    });
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
