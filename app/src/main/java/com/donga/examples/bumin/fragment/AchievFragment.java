package com.donga.examples.bumin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.donga.examples.bumin.AppendLog;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.InfoSingleton;
import com.donga.examples.bumin.retrofit.retrofitAchieveAll.Interface_achall;
import com.donga.examples.bumin.retrofit.retrofitAchieveAll.Master;
import com.donga.examples.bumin.retrofit.retrofitAchieveSep.Interface_achsep;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
public class AchievFragment extends Fragment {

    private FragmentManager fm;
    private ProgressDialog mProgressDialog;
    AppendLog appendLog = new AppendLog();

    @BindView(R.id.achiev_all)
    Spinner achiev_all;
    @BindView(R.id.part_layout)
    RelativeLayout part_layout;
    @BindView(R.id.all_ok_card)
    CardView all_ok_card;
    @BindView(R.id.hide)
    LinearLayout hide;
    @BindView(R.id.all_ok)
    Button all_ok;
    @BindView(R.id.part_ok)
    Button part_ok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_achiev, container, false);
        ButterKnife.bind(this,rootview);

        fm = getFragmentManager();

        achiev_all.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    all_ok_card.setVisibility(View.VISIBLE);
                    part_layout.setVisibility(View.GONE);
                } else {
                    all_ok_card.setVisibility(View.GONE);
                    part_layout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "메뉴를 선택해주세요.", Toast.LENGTH_LONG).show();
            }
        });
        return rootview;
    }

    @OnClick(R.id.all_ok)
    void allOkClicked(){
        Toast.makeText(getActivity(), "추가클릭", Toast.LENGTH_SHORT).show();
        hide.setVisibility(View.VISIBLE);
        FragmentTransaction ft = fm.beginTransaction();
        Achiev_All_Fragment achiev_all_fragment = new Achiev_All_Fragment();
        ft.replace(R.id.frag_change, achiev_all_fragment);
        ft.commit();
    }

    @OnClick(R.id.part_ok)
    void onPartOkClicked(){
        Toast.makeText(getActivity(), "클릭", Toast.LENGTH_SHORT).show();
        hide.setVisibility(View.VISIBLE);
        FragmentTransaction ft = fm.beginTransaction();
        Achiev_Part_Fragment achiev_part_fragment = new Achiev_Part_Fragment();
        ft.replace(R.id.frag_change, achiev_part_fragment);
        ft.commit();
        showProgressDialog();
//        ArrayList<String> years = new ArrayList<String>();
//        for(int i = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()))); i>=Integer.parseInt(InfoSingleton.getInstance().getYear()); i--){
//            years.add(String.valueOf(i));
//        }
//        Log.i("YEARS", years.toString());
//        //retrofit 통신
//        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        Interface_achsep ach = client.create(Interface_achsep.class);
//        try {
//            Call<com.donga.examples.bumin.retrofit.retrofitAchieveSep.Master> call = ach.getSepGrade(InfoSingleton.getInstance().getStuId(),
//                    Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)), "2016", "00");
//            call.enqueue(new Callback<com.donga.examples.bumin.retrofit.retrofitAchieveSep.Master>() {
//                @Override
//                public void onResponse(Call<com.donga.examples.bumin.retrofit.retrofitAchieveSep.Master> call, Response<com.donga.examples.bumin.retrofit.retrofitAchieveSep.Master> response) {
//                    Log.i("ACHIEVE_SEP onResponse", response.body().getResult_body().getAllGrade());
//                    hideProgressDialog();
//                }
//                @Override
//                public void onFailure(Call<com.donga.examples.bumin.retrofit.retrofitAchieveSep.Master> call, Throwable t) {
//                    hideProgressDialog();
//                    t.printStackTrace();
//                    appendLog.appendLog("ACHIEVE_SEP onFailure");
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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