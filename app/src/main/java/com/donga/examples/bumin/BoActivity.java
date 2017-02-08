package com.donga.examples.bumin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class BoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo);
        ButterKnife.bind(this);

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.list_bo);
        listview.setAdapter(adapter);

        // 리스트뷰 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_notice), "공지사항");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_event),"행사모집");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_hag),"학사정보") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_bo),"자유게시판") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_job),"알바정보") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_market),"벼룩시장") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_lost),"분실물센터") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bo_circle),"동아리센터") ;
    }
}
