package com.donga.examples.bumin;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class BoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar_bo)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_bo)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_bo)
    NavigationView navigationView;
    @BindView(R.id.list_bo)
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        BoListViewAdapter adapter;

        // Adapter 생성
        adapter = new BoListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview.setAdapter(adapter);
        // 리스트뷰 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_notice), "공지사항");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_event), "행사모집");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_hag), "학사정보");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_bo), "자유게시판");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_job), "알바정보");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_market), "벼룩시장");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_lost), "분실물센터");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_circle), "동아리센터");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_bo);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_bo);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
