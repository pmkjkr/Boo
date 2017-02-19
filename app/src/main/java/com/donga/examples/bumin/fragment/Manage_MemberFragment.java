package com.donga.examples.bumin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.donga.examples.bumin.R;
import com.donga.examples.bumin.listviewAdapter.MemberListViewAdapter;
import com.donga.examples.bumin.listviewAdapter.PartListViewAdapter;
import com.donga.examples.bumin.listviewItem.MemberListViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Manage_MemberFragment extends Fragment{
    @BindView(R.id.list_member)
    ListView list_member;
//    @BindView(R.id.manage_member_divi)
//    TextView member_divi;

    private MemberListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_member, container, false);
        ButterKnife.bind(this,rootview);

        adapter = new MemberListViewAdapter();
        list_member.setAdapter(adapter);

        adapter.addItem("14학번"); //학번 순으로 정렬하는데 학번의 앞 두자리가 14면 14학번, 13이면 13으로 나눔
        adapter.addItem1("1419142","김현정");
        adapter.addItem1("1419142","김현정");
        adapter.addItem1("1419142","김현정");
        adapter.addItem1("1419142","김현정");
        adapter.addItem("15학번");
        adapter.addItem1("1519142","김현정");
        adapter.addItem1("1519142","김현정");
        adapter.addItem1("1519142","김현정");
        adapter.addItem1("1519142","김현정");
        adapter.addItem("16학번");
        adapter.addItem1("1619142","김현정");
        adapter.addItem1("1619142","김현정");
        adapter.addItem1("1619142","김현정");
        adapter.addItem1("1619142","김현정");

        return rootview;
    }
}