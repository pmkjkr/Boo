package com.donga.examples.bumin.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.donga.examples.bumin.R;
import com.donga.examples.bumin.listviewAdapter.RoomListViewAdapter;
import com.donga.examples.bumin.retrofit.retrofitRoom.Interface_room;
import com.donga.examples.bumin.retrofit.retrofitRoom.Master4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.iv_room)
    ImageView iv_room;
    View header;
    TextView tv_room5;

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

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog();
                retrofit();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        retrofit();

    }

    @OnClick(R.id.iv_room)
    void onIvClicked() {
        showProgressDialog();
        retrofit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_room);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
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

        if (id == R.id.nav_res) {
            Intent intent = new Intent(getApplicationContext(), ResActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_room) {
            Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pro) {

        } else if (id == R.id.nav_stu) {
            Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_empty) {
            Intent intent = new Intent(getApplicationContext(), EmptyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_site) {
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), ManageActivity.class);
            startActivity(intent);
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

    public void retrofit() {
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
                for (int i = 0; i < response.body().getResult_body().size(); i++) {
                    adapter.addItem(response.body().getResult_body().get(i).getLoc(),
                            response.body().getResult_body().get(i).getAll(),
                            response.body().getResult_body().get(i).getUse(),
                            response.body().getResult_body().get(i).getRemain(),
                            response.body().getResult_body().get(i).getUtil());
                    if (validateEmail(response.body().getResult_body().get(i).getUtil())) {
                        header = getLayoutInflater().inflate(R.layout.listview_room, null, false);
                        tv_room5 = (TextView)header.findViewById(R.id.text_room5);
                        Log.i("100%", "100%");
                        tv_room5.setTextColor(Color.RED);
                    }
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

    public static boolean validateEmail(String emailStr) {
        final Pattern VALID_PERCENT_REGEX = Pattern.compile("100");
        Matcher matcher = VALID_PERCENT_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
