package com.donga.examples.bumin.fragment;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.bumin.AppendLog;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.InfoSingleton;
import com.donga.examples.bumin.retrofit.retrofitGrad.Interface_grad;
import com.donga.examples.bumin.retrofit.retrofitSchedule.Interface_sche;
import com.donga.examples.bumin.retrofit.retrofitSchedule.Master;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_ScheFragment extends Fragment {
    private ProgressDialog mProgressDialog;
    AppendLog appendLog = new AppendLog();

    @BindView(R.id.tv_pro_tel)
    TextView tv_pro_tel;
    @BindView(R.id.tv_pro_email)
    TextView tv_pro_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_sche, container, false);
        ButterKnife.bind(this,rootview);


//        showProgressDialog();
//
//        //retrofit 통신
//        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        Interface_sche sche = client.create(Interface_sche.class);
//        try {
//            Call<Master> call = sche.getTimeTable(InfoSingleton.getInstance().getStuId(), Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
//            call.enqueue(new Callback<Master>() {
//                @Override
//                public void onResponse(Call<Master> call, Response<Master> response) {
//                    Log.i("Schedule onResponse", response.body().getResult_body().get(0).getMon().get(0));
//
//                    hideProgressDialog();
//                }
//
//                @Override
//                public void onFailure(Call<Master> call, Throwable t) {
//                    hideProgressDialog();
//                    t.printStackTrace();
//                    appendLog.appendLog("Schedule onFailure");
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return rootview;
    }

    @OnClick(R.id.tv_pro_tel)
    void onTelClicked(){
        ClipboardManager clipboardManager = (ClipboardManager)getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", tv_pro_tel.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
    }

    @OnLongClick(R.id.tv_pro_tel)
    boolean onLongTelClicked(){
        String pro_tel = tv_pro_tel.getText().toString();
        Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", pro_tel)));
        startActivity(in);
        return true;
    }

    @OnClick(R.id.tv_pro_email)
    void onEmailClicked(){
        ClipboardManager clipboardManager = (ClipboardManager)getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", tv_pro_email.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
    }

    @OnLongClick(R.id.tv_pro_email)
    boolean onEmailLongClicked(){
        Intent it = new Intent(Intent.ACTION_SEND);
        String[] mailaddr = {tv_pro_email.getText().toString()};
        it.setType("plaine/text");
        it.putExtra(Intent.EXTRA_EMAIL, mailaddr);
        startActivity(it);
        return true;
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
}