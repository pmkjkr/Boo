package com.donga.examples.bumin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rhfoq on 2017-02-07.
 */
public class HomeActivity extends AppCompatActivity {

    @OnClick(R.id.menu_res)
    void menu_res() {
        Intent intent = new Intent(getApplicationContext(), ResActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_room)
    void menu_room() {
        Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.back);
        linearLayout.getBackground().setAlpha(90);

//        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.back1);
//        linearLayout1.getBackground().setAlpha(90);
//
//        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.back2);
//        linearLayout2.getBackground().setAlpha(90);
//
//        LinearLayout linearLayout3 = (LinearLayout)findViewById(R.id.back3);
//        linearLayout3.getBackground().setAlpha(90);
    }
}
