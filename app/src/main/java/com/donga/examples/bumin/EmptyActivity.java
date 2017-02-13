package com.donga.examples.bumin;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-09.
 */
public class EmptyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BottomSheetBehavior behavior;

    @BindView(R.id.toolbar_empty)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_empty)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_empty)
    NavigationView navigationView;
    @BindView(R.id.fab_empty)
    FloatingActionButton fab;
    @BindView(R.id.list_empty)
    ListView listview;
    @BindView(R.id.bottom)
    GridLayout bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        EmptyListViewAdapter adapter;

        // Adapter 생성
        adapter = new EmptyListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview.setAdapter(adapter);
        // 리스트뷰 아이템 추가
        adapter.addItem("일해라 핫산");
        adapter.addItem("망아지는 일하는 중ㅠ");
        adapter.addItem("이쁘게 만들어놩~~><");
        adapter.addItem("꺄잉");
        adapter.addItem("스고이");
        adapter.addItem("잠이온다...");
        adapter.addItem("자고싶다ㅠㅠ");

         bottom = (GridLayout) findViewById(R.id.bottom);
        behavior = BottomSheetBehavior.from(bottom);
        behavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250.f, getResources().getDisplayMetrics()));
        behavior.setHideable(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_empty);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_empty);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
