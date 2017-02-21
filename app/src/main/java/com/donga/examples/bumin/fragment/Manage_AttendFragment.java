package com.donga.examples.bumin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donga.examples.bumin.R;
import com.donga.examples.bumin.adapter.AttendAdapter;
import com.donga.examples.bumin.adapter.MyData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Manage_AttendFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_attend, container, false);
        ButterKnife.bind(this,rootview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new AttendAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData("2017.02.02","개강집회 알림"));
        myDataset.add(new MyData("2017.02.05","집회 알림"));
        myDataset.add(new MyData("2017.02.07","오감도집회 알림"));
        myDataset.add(new MyData("2017.02.10","포미스집회 알림"));
        myDataset.add(new MyData("2017.02.12","졸송"));
        myDataset.add(new MyData("2017.02.13","오울림"));
        myDataset.add(new MyData("2017.02.13","방집"));



        return rootview;
    }


}