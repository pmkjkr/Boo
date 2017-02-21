package com.donga.examples.bumin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.ManageSingleton;
import com.donga.examples.bumin.retrofit.retrofitAuthLogin.Interface_authLogin;
import com.donga.examples.bumin.retrofit.retrofitAuthLogin.Master;
import com.donga.examples.bumin.retrofit.retrofitFcm.Interface_fcm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Manage_LetterFragment extends Fragment {
    @BindView(R.id.manage_letter_spinner)
    Spinner manage_letter_spinner;
    @BindView(R.id.manage_letter_name)
    EditText manage_letter_name;
    @BindView(R.id.manage_letter_title)
    EditText manage_letter_title;
    @BindView(R.id.manage_letter_content)
    EditText manage_letter_content;
    @BindView(R.id.manage_letter_send)
    CardView manage_letter_send;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_letter, container, false);
        ButterKnife.bind(this, rootview);

        return rootview;
    }

    @OnClick(R.id.manage_letter_send)
    void onSendClick(){
        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_fcm fcm = client.create(Interface_fcm.class);
        Call<com.donga.examples.bumin.retrofit.retrofitFcm.Master> call = fcm.sendFcm("bearer "+ManageSingleton.getInstance().getToken(),
                manage_letter_name.getText().toString(), manage_letter_title.getText().toString(), manage_letter_content.getText().toString());
        call.enqueue(new Callback<com.donga.examples.bumin.retrofit.retrofitFcm.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.bumin.retrofit.retrofitFcm.Master> call, Response<com.donga.examples.bumin.retrofit.retrofitFcm.Master> response) {
                Log.i("onresponse", "done");
            }

            @Override
            public void onFailure(Call<com.donga.examples.bumin.retrofit.retrofitFcm.Master> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}