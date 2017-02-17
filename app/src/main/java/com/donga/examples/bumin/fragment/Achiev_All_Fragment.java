package com.donga.examples.bumin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.donga.examples.bumin.AllListViewAdapter;
import com.donga.examples.bumin.AppendLog;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.InfoSingleton;
import com.donga.examples.bumin.retrofit.retrofitAchieveAll.Interface_achall;
import com.donga.examples.bumin.retrofit.retrofitAchieveAll.Master;

import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Achiev_All_Fragment extends Fragment {
    private ProgressDialog mProgressDialog;
    AppendLog appendLog = new AppendLog();
    @BindView(R.id.list_all)
    ListView list_all;
    @BindView(R.id.tv_getAllGrade)
    TextView tv_getAllGrade;
    @BindView(R.id.tv_getAllAverage)
    TextView tv_getAllAverage;

    private AllListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_achiev_all, container, false);
        ButterKnife.bind(this,rootview);

        showProgressDialog();

        adapter = new AllListViewAdapter();
        list_all.setAdapter(adapter);

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_achall ach = client.create(Interface_achall.class);
        try {
            Call<Master> call = ach.getAllGrade(InfoSingleton.getInstance().getStuId(), Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    hideProgressDialog();
                    tv_getAllGrade.setText(response.body().getResult_body().getAllGrade());
                    tv_getAllAverage.setText(response.body().getResult_body().getAvgGrade());
                    for(int i = 0; i<response.body().getResult_body().getDetail().size(); i++){
//                        Log.i("??????", String.valueOf(response.body().getResult_body().getDetail().get(i).size()));

                        for(int j = 0; j<response.body().getResult_body().getDetail().get(i).size(); j++){
                            if(response.body().getResult_body().getDetail().get(i).get(0).length()==4){
                                Log.i("??????", response.body().getResult_body().getDetail().get(i).get(0));
//                                for(int k = i; k<)
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<Master> call, Throwable t) {
                    hideProgressDialog();
                    t.printStackTrace();
                    appendLog.appendLog("ACHIEVE_ALL onFailure");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.addItem("교과목명","이수구분","학점","성적");
        adapter.addItem("컴퓨터구조와운영체제","학과교양","3","B+");
        adapter.addItem("디지털정보활용","중점교양","3","C+");
        adapter.addItem("Talking English 1A","교양필수","1","P");
        adapter.addItem("새로운문화속의음악(바하에서K-POP까지)","학과교양","3","C+");

        return rootview;
    }

    // MD5 복호화
    public static String Decrypt(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
//               BASE64Decoder decoder = new BASE64Decoder();
//               Base64.decode(input, flags)
//               byte [] results = cipher.doFinal(decoder.decodeBuffer(text));
        // BASE64Decoder decoder = new BASE64Decoder();
        // Base64.decode(input, flags)
        byte[] results = cipher.doFinal(Base64.decode(text, 0));
        return new String(results, "UTF-8");
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
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