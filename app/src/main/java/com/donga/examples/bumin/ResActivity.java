package com.donga.examples.bumin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.donga.examples.bumin.retrofit.retrofitMeal.Interface_meal;
import com.donga.examples.bumin.retrofit.retrofitMeal.Master3;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ResActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressDialog mProgressDialog;

    @BindView(R.id.toolbar_res)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_res)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_res)
    NavigationView navigationView;
    @BindView(R.id.date_text)
    TextView date_text;

    @BindView(R.id.guk)
    TextView guk;
    @BindView(R.id.gang)
    TextView gang;
    @BindView(R.id.bumin)
    TextView bumin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        retrofit();

        SimpleDateFormat msimpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date currentTime = new Date();
        String now = msimpleDateFormat.format(currentTime);

        date_text.setText(now);
        date_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CalendarFragment();
                newFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_res);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_res);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void retrofit(){
        showProgressDialog();

        SimpleDateFormat msimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String nowTime = msimpleDateFormat.format(currentTime);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_meal meal = client.create(Interface_meal.class);
        retrofit2.Call<Master3> call3 = meal.getMeal(nowTime);
        call3.enqueue(new Callback<Master3>() {
            @Override
            public void onResponse(Call<Master3> call, Response<Master3> response) {
                String source_guk = response.body().getResult_body().getInter();
                guk.setText(Html.fromHtml(source_guk));
                guk.setMovementMethod(LinkMovementMethod.getInstance());

                String source_bumin = response.body().getResult_body().getBumin_kyo();
                bumin.setText(Html.fromHtml(source_bumin));
                bumin.setMovementMethod(LinkMovementMethod.getInstance());

                String source_gang = response.body().getResult_body().getGang();
                gang.setText(Html.fromHtml(source_gang));
                gang.setMovementMethod(LinkMovementMethod.getInstance());

                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<Master3> call, Throwable t) {
                hideProgressDialog();
                Log.i("CALL3", "onFailure");
                t.printStackTrace();
            }
        });
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
