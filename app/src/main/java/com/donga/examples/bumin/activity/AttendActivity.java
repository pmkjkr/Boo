package com.donga.examples.bumin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.donga.examples.bumin.R;
import com.donga.examples.bumin.listviewAdapter.AttendListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-21.
 */
public class AttendActivity  extends AppCompatActivity {

    @BindView(R.id.list_attend)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);
        ButterKnife.bind(this);


        AttendListViewAdapter adapter = new AttendListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("1419142", "김현정", "참석");
        adapter.addItem("1124305", "정록헌", "불참");
        adapter.addItem("1123792", "박민규", "불참");
        adapter.addItem("1222977", "백지환", "참석");
        adapter.addItem("1524523", "아무개", "참석");
        adapter.addItem("1248536", "망망망", "불참");
        adapter.addItem("1685225", "멍멍멍", "참석");
        adapter.addItem("1766265", "왈왈왈", "불참");
        adapter.addItem("1352152", "드르렁", "불참");

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        Intent i = new Intent(getApplicationContext(), ManageActivity.class);
//        startActivity(i);
//    }
}