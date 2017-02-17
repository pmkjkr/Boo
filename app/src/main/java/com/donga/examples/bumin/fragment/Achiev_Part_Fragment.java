package com.donga.examples.bumin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.donga.examples.bumin.AllListViewAdapter;
import com.donga.examples.bumin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Achiev_Part_Fragment extends Fragment {

    @BindView(R.id.list_all)
    ListView list_all;

    private AllListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_achiev_part, container, false);
        ButterKnife.bind(this,rootview);

        adapter = new AllListViewAdapter();
        list_all.setAdapter(adapter);

        adapter.addItem("교과목명","이수구분","학점","성적");
        adapter.addItem("정보기술과경영전략","학과교양","3","B+");
        adapter.addItem("디지털정보활용","중점교양","3","C+");
        adapter.addItem("Talking English 1A","교양필수","1","P");
        adapter.addItem("새로운문화속의음악(바하에서K-POP까지)","학과교양","3","C+");

        return rootview;
    }
}