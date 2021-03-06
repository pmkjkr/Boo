package com.donga.examples.bumin.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.donga.examples.bumin.AppendLog;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.InfoSingleton;
import com.donga.examples.bumin.Singleton.PushSingleton;
import com.donga.examples.bumin.retrofit.retrofitLogin.Interface_login;
import com.donga.examples.bumin.retrofit.retrofitLogin.Master;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import me.leolin.shortcutbadger.ShortcutBadger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstActivity extends AppCompatActivity {
    public final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;

    AppendLog log = new AppendLog();

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int badgeCount = 0;
        ShortcutBadger.applyCount(getApplicationContext(), badgeCount);

        //READ_PHONE_STATE 권한 체크
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("contents") != null) {
                Log.i("INTENT", getIntent().getExtras().getString("contents"));
                PushSingleton.getInstance().setmString(getIntent().getExtras().getString("contents"));
//                log.appendLog("inFirstActivity" + getIntent().getExtras().getString("contents"));
            }
        }

        final SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        if (sharedPreferences.contains("stuID") && sharedPreferences.contains("ID") && sharedPreferences.contains("pw")) {

            //retrofit 통신
            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Interface_login room = client.create(Interface_login.class);
            Call<Master> call4 = null;
            try {
                call4 = room.loginUser(String.valueOf(sharedPreferences.getInt("stuID", 0)), Decrypt(sharedPreferences.getString("pw", ""), getString(R.string.decrypt_key)));
            } catch (Exception e) {
                e.printStackTrace();
//                log.appendLog("inFirstActivity LOGIN FAILED");
            }
            call4.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    if (response.body().getResult_code() == 1) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                        InfoSingleton.getInstance().setStuId(String.valueOf(sharedPreferences.getInt("stuID", 0)));
                        InfoSingleton.getInstance().setStuPw(sharedPreferences.getString("pw", ""));

                        if (PushSingleton.getInstance().getmString() != null) {
                            Bundle bun = new Bundle();
                            bun.putString("contents", PushSingleton.getInstance().getmString());
                            intent.putExtras(bun);
                        }
                        startActivity(intent);
                    } else {
//                        log.appendLog("inFirstActivity Att2 code not matched move to LoginActivity");
                        moveToLoginActivity();
                    }
                }

                @Override
                public void onFailure(Call<Master> call, Throwable t) {
                    //retrofit 통신 실패시 SharedPreferences 삭제 후 LoginActivity로 이동
                    t.printStackTrace();
//                    log.appendLog("inFirstActivity AUTO LOGIN FAILED");
                    moveToLoginActivity();
                }
            });


        } else {
            //기기에 저장된 SharedPreferences 없으면 LoginActivity로 이동
//            log.appendLog("inFirstActivity no sharedPreferences move to LoginActivity");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 동의 버튼 선택
                    Log.i("requestPermissions", "done");
                } else {
                    Toast.makeText(this, "권한 사용에 동의해주셔야 이용이 가능합니다.", Toast.LENGTH_SHORT);
//                    log.appendLog("permission denied");
                    finish();
                }
            }
        }
    }

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
        byte[] results = cipher.doFinal(Base64.decode(text, 0));
        return new String(results, "UTF-8");
    }

    public void moveToLoginActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}