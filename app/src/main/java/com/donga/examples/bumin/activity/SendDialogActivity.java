package com.donga.examples.bumin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.donga.examples.bumin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rhfoq on 2017-02-22.
 */
public class SendDialogActivity extends Activity {

    //    private String notiMessage;
    @BindView(R.id.dialog_send_date)
    TextView send_date;
    @BindView(R.id.dialog_send_title)
    TextView send_title;
    @BindView(R.id.dialog_send_content)
    TextView send_content;

    @OnClick(R.id.popup_close)
    void close(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        Bundle bun = getIntent().getExtras();
//        notiMessage = bun.getString("contents");

        setContentView(R.layout.activity_send_dialog);
        ButterKnife.bind(this);

    }
}