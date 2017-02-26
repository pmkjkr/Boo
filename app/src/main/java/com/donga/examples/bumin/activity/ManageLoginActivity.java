package com.donga.examples.bumin.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.donga.examples.bumin.AppendLog;
import com.donga.examples.bumin.MinServiceClass;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.ManageSingleton;
import com.donga.examples.bumin.retrofit.retrofitAuthLogin.Interface_authLogin;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageLoginActivity extends AppCompatActivity {
    AppendLog log = new AppendLog();

    @BindView(R.id.s_manageId)
    EditText s_manageId;
    @BindView(R.id.s_managePw)
    EditText s_managePw;
    @BindView(R.id.login_manageBt)
    Button login_manageBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_manageBt)
    void onLoginClicked(){
        Log.i("CLICK", s_manageId.getText().toString()+","+s_managePw.getText().toString());

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_authLogin authLogin = client.create(Interface_authLogin.class);
        Call<com.donga.examples.bumin.retrofit.retrofitAuthLogin.Master> call4 = authLogin.authLogin(s_manageId.getText().toString(), s_managePw.getText().toString());
        call4.enqueue(new Callback<com.donga.examples.bumin.retrofit.retrofitAuthLogin.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.bumin.retrofit.retrofitAuthLogin.Master> call, Response<com.donga.examples.bumin.retrofit.retrofitAuthLogin.Master> response) {
                ManageSingleton.getInstance().setToken(response.body().getToken());
                ManageSingleton.getInstance().setManagerID(s_manageId.getText().toString());

                Log.i("ManageLoginActivity", "started");
                Intent i = new Intent(getApplicationContext(), ManageActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<com.donga.examples.bumin.retrofit.retrofitAuthLogin.Master> call, Throwable t) {
                log.appendLog("inManageLoginActivity failure");
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}
