package com.donga.examples.bumin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.donga.examples.bumin.retrofit.retrofitRoom.Interface_room;
import com.donga.examples.bumin.retrofit.retrofitRoom.Master4;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class RoomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressDialog mProgressDialog;

    @BindView(R.id.toolbar_room)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_room)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_room)
    NavigationView navigationView;
    @BindView(R.id.list_room)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        showProgressDialog();

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_room room = client.create(Interface_room.class);
        Call<Master4> call4 = room.getRoom();
        call4.enqueue(new Callback<Master4>() {
            @Override
            public void onResponse(Call<Master4> call, Response<Master4> response) {
                // Adapter 생성
                RoomListViewAdapter adapter = new RoomListViewAdapter();
                // Adapter달기
                listview.setAdapter(adapter);
                for(int i = 0; i<response.body().getResult_body().size(); i++){
                    adapter.addItem(response.body().getResult_body().get(i).getLoc(),
                            response.body().getResult_body().get(i).getAll(),
                            response.body().getResult_body().get(i).getUse(),
                            response.body().getResult_body().get(i).getRemain(),
                            response.body().getResult_body().get(i).getUtil());

//                    if(response.body().getResult_body().get(i).getLoc().equals("사회대 열람실1")){
//                        Log.i("onResponse", response.body().getResult_body().get(i).getLoc()+"의 이용률 : "+response.body().getResult_body().get(i).getUtil());
//                        Log.i("onResponse", response.body().getResult_body().get(i).getUtil());
//                    }

                    hideProgressDialog();
                }
            }
            @Override
            public void onFailure(Call<Master4> call, Throwable t) {
                hideProgressDialog();
                Log.i("CALL4", "onFailure");
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_room);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_room);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.dismiss();
        }
    }
}
