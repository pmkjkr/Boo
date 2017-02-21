package com.donga.examples.bumin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donga.examples.bumin.R;
import com.donga.examples.bumin.activity.AttendActivity;
import com.donga.examples.bumin.activity.ManageActivity;
import com.donga.examples.bumin.adapter.MyData_Send;
import com.donga.examples.bumin.adapter.SendAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Manage_SendFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData_Send> myDataset;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_send, container, false);
        ButterKnife.bind(this,rootview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new SendAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData_Send("2017.02.02","공지 알림","공지내용"));
        myDataset.add(new MyData_Send("2017.02.03","공지 알림","공지내용"));
        myDataset.add(new MyData_Send("2017.02.04","공지 알림","공지내용"));
        myDataset.add(new MyData_Send("2017.02.05","공지 알림","공지내용"));
        myDataset.add(new MyData_Send("2017.02.06","공지 알림","공지내용"));
        return rootview;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i("SENDFRAG", "onPause");
//        Intent i = new Intent(getContext(), AttendActivity.class);
//        startActivity(i);
//    }
}