package com.donga.examples.bumin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.bumin.AllListViewAdapter;
import com.donga.examples.bumin.PartListViewAdapter;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.GradeSingleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Achiev_Part_Fragment extends Fragment {
    private LinearLayout achiev_bottom;
    private CardView below;

    @BindView(R.id.list_part)
    ListView list_part;
    @BindView(R.id.get)
    TextView get;
    @BindView(R.id.aver)
    TextView aver;


    private PartListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_achiev_part, container, false);
        ButterKnife.bind(this,rootview);

        adapter = new PartListViewAdapter();
        list_part.setAdapter(adapter);

        get.setText(GradeSingleton.getInstance().getPartGrade());
        aver.setText(GradeSingleton.getInstance().getPartAvg());

        int GradeDetailSize = GradeSingleton.getInstance().getDetail2().size();
        ArrayList<ArrayList<String>> DetailList = GradeSingleton.getInstance().getDetail2();
        ArrayList<String> yearList = new ArrayList<String>();

        for(int i = 1; i<GradeDetailSize; i++){
            if (GradeSingleton.getInstance().getDetail2().get(i).get(0).length() == 4) {
                yearList.add(String.valueOf(i));
            }
        }
        ArrayList<String> fTitle = new ArrayList<String>(yearList.size());
        ArrayList<String> sTitle = new ArrayList<String>(yearList.size());
        ArrayList<Integer> position = new ArrayList<Integer>();
        for (int i = 1; i < GradeDetailSize; i++) {
            if (GradeSingleton.getInstance().getDetail2().get(i).get(0).length() == 4) {
                fTitle.add(DetailList.get(i).get(0));
                sTitle.add(DetailList.get(i).get(1));
            }

        }
        for(int q = 0; q<yearList.size(); q++){
            adapter.addItem(fTitle.get(q),sTitle.get(q));
            adapter.addItem1("교과목명", "성적");
            if(q<yearList.size()-1) {
                for (int j = Integer.parseInt(yearList.get(q)); j < Integer.parseInt(yearList.get(q+1)); j++) {
                    adapter.addItem2(String.valueOf(j), DetailList.get(j).get(3), DetailList.get(j).get(6));
                    position.add(j);
                }
            }else{
                for(int k = Integer.parseInt(yearList.get(q)); k<GradeDetailSize; k++){
                    adapter.addItem2(String.valueOf(k), DetailList.get(k).get(3), DetailList.get(k).get(6));
                    position.add(k);
                }
            }
        }

//        adapter.addItem("2014","1학기");
//        adapter.addItem1("교과목명","성적");
//        adapter.addItem2("d","컴퓨터구조와운영체제","B+");
//        adapter.addItem2("d","디지털정보활용","C+");
//        adapter.addItem2("d","새로운문화속의음악(바하에서K-POP까지)","C+");
//        adapter.addItem2("d","경영학원론","A");

        return rootview;
    }
}