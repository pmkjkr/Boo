package com.donga.examples.bumin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

    @OnClick(R.id.menu_bo)
    void menu_bo() {
        Intent intent = new Intent(getApplicationContext(), BoActivity.class);
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
    }
}
