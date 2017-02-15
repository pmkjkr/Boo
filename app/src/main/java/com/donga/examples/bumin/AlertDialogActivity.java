package com.donga.examples.bumin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertDialogActivity extends Activity {

    private String notiMessage;
    @BindView(R.id.tv_popup)
    TextView tv_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle bun = getIntent().getExtras();
        notiMessage = bun.getString("contents");


        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);

        tv_popup.setText(notiMessage);
    }
}