package com.donga.examples.bumin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.bumin.AppendLog;
import com.donga.examples.bumin.R;
import com.donga.examples.bumin.Singleton.ManageSingleton;
import com.donga.examples.bumin.listviewAdapter.MemberListViewAdapter;
import com.donga.examples.bumin.retrofit.retrofitGetMembers.Interface_getMembers;
import com.donga.examples.bumin.retrofit.retrofitGetMembers.Master;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Manage_MemberFragment extends Fragment {
    AppendLog log = new AppendLog();
    private ProgressDialog mProgressDialog;

    @BindView(R.id.list_member)
    ListView list_member;
    @BindView(R.id.tv_allSelect)
    TextView tv_allSelect;
    @BindView(R.id.manage_member_select)
    CardView manage_member_select;
    @BindView(R.id.garbage)
    ImageView garbage;
//    @BindView(R.id.manage_member_check)
//    CheckBox manage_member_check;

    private MemberListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_member, container, false);
        ButterKnife.bind(this, rootview);

//        showProgressDialog();

        adapter = new MemberListViewAdapter();
        list_member.setAdapter(adapter);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_getMembers getMembers = client.create(Interface_getMembers.class);
        Call<Master> call = getMembers.getMembers("bearer " + ManageSingleton.getInstance().getToken());
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if (response.body().getResult_code() == 1) {
                    for (int i = 0; i < response.body().getResult_body().size(); i++) {
                        adapter.addItem1(response.body().getResult_body().get(i).getStuId(), response.body().getResult_body().get(i).getName());
                        adapter.notifyDataSetChanged();
                    }
//                    hideProgressDialog();
                } else {
//                    hideProgressDialog();
                    log.appendLog("inMemberFragment code not matched");
                    Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
//                hideProgressDialog();
                log.appendLog("inMemberFragment failure");
                Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT);
                t.printStackTrace();
            }
        });

        return rootview;
    }

    @OnClick(R.id.tv_allSelect)
    void onAllSelectClicked() {
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            list_member.setItemChecked(i, true);
            if (list_member.isItemChecked(i)) {
                Log.i("SELECTED ITEM", adapter.getStuId(i));
            }
        }
    }

//    @OnClick(R.id.garbage)
//    void onDeleteClicked(){
//        manage_member_select.setVisibility(View.VISIBLE);
//        list_member.findViewById(R.id.manage_member_check).setVisibility(View.VISIBLE);
//    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.dismiss();
        }
    }
}