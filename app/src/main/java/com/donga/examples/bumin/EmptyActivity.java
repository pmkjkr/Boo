package com.donga.examples.bumin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.donga.examples.bumin.retrofit.retrofitEmpty.Interface_Empty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-09.
 */
public class EmptyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog mProgressDialog;

    BottomSheetBehavior behavior;
    EmptyListViewAdapter adapter;

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

    @BindView(R.id.empty_day)
    Spinner empty_day;
    @BindView(R.id.empty_clock1)
    Spinner empty_clock1;
    @BindView(R.id.empty_clock2)
    Spinner empty_clock2;
    @BindView(R.id.bottom_button2)
    Button bottom_button2;

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

        List<String> empty_day_list = Arrays.asList(getResources().getStringArray(R.array.empty_day));
        final List<String> empty_clock_list = Arrays.asList(getResources().getStringArray(R.array.empty_clock));
        ArrayAdapter<String> empty_day_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, empty_day_list);
        ArrayAdapter<String> empty_clock_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, empty_clock_list);
        empty_day.setAdapter(empty_day_adapter);
        empty_clock1.setAdapter(empty_clock_adapter);
        empty_clock2.setAdapter(empty_clock_adapter);

        empty_clock1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int j = 0;
                ArrayList<String> empty_clock2_list = new ArrayList<String>();

                for (int i = Integer.parseInt(parent.getSelectedItem().toString()); i <= 20; i++) {
                    empty_clock2_list.add(String.valueOf(i));
                    j++;
                }
                ArrayAdapter<String> empty_clock2_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, empty_clock2_list);
                empty_clock2.setAdapter(empty_clock2_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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

    @OnClick(R.id.bottom_button2)
    void onBottomButtonClicked() {
        String empty_day_day = empty_day.getSelectedItem().toString();
        Log.i("empty_clock1", empty_clock1.getSelectedItem().toString());
        Log.i("empty_clock2", empty_clock2.getSelectedItem().toString());

        switch (empty_day_day) {
            case "월":
                empty_day_day = "1";
                break;
            case "화":
                empty_day_day = "2";
                break;
            case "수":
                empty_day_day = "3";
                break;
            case "목":
                empty_day_day = "4";
                break;
            case "금":
                empty_day_day = "5";
                break;
        }
        Log.i("empty_day", empty_day_day);

        showProgressDialog();

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_Empty empty = client.create(Interface_Empty.class);
        Call<com.donga.examples.bumin.retrofit.retrofitEmpty.Master> call = empty.getEmpty(empty_day_day,
                empty_clock1.getSelectedItem().toString(), empty_clock2.getSelectedItem().toString());
        call.enqueue(new Callback<com.donga.examples.bumin.retrofit.retrofitEmpty.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.bumin.retrofit.retrofitEmpty.Master> call, Response<com.donga.examples.bumin.retrofit.retrofitEmpty.Master> response) {
                // Adapter 생성
                adapter = new EmptyListViewAdapter();
                // 리스트뷰 참조 및 Adapter달기
                listview.setAdapter(adapter);
                // 리스트뷰 아이템 추가
                for (int i = 0; i < response.body().getResult_body().size(); i++) {
                    adapter.addItem(response.body().getResult_body().get(i).getRoom_no());
                    hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<com.donga.examples.bumin.retrofit.retrofitEmpty.Master> call, Throwable t) {
                hideProgressDialog();
                Log.i("EMPTY onFailure", "dd");
                t.printStackTrace();
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
        }
    }
}
