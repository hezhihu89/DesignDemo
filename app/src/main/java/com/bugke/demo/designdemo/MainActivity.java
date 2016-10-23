package com.bugke.demo.designdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bugke.demo.designdemo.fragment.FragmentPage;
import com.bugke.demo.designdemo.fragment.NewFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements PageAdapter {

    private static final String[][] PAGES = new String[][]{{"Android", "http://p.codekk.com/api/op/page/1?type=mix"},
            {"iOS", "http://p.codekk.com/api/op/page/2?type=mix"},
            {"新闻", "http://3g.163.com/touch/reconstruct/article/list/BA10TA81wangning/"},
            {"天气", "http://p.codekk.com/api/op/page/4?type=mix"},
            {"福利", "http://p.codekk.com/api/op/page/5?type=mix"},
            {"学习", "http://p.codekk.com/api/op/page/6?type=mix"}};

    TabLayout mTabLayout;

    ViewPager mViewpage;
    private FloatingActionButton floatingActionButton;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

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
        Fragment fragment;
        switch (position) {
            case 2:
                fragment = NewFragment.newInstance(PAGES[position][0], PAGES[position][1]);
                break;
            default:
                fragment = FragmentPage.newInstance(PAGES[position][0], PAGES[position][1]);
                break;
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
}
