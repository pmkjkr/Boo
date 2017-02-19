package com.donga.examples.bumin.listviewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.donga.examples.bumin.listviewItem.ProSubListViewItem;
import com.donga.examples.bumin.R;

import java.util.ArrayList;

/**
 * Created by nature on 16. 7. 19.
 */
public class ProSubListViewAdapter extends BaseAdapter {

    public ArrayList<ProSubListViewItem> mlistData = new ArrayList<ProSubListViewItem>();

    public ProSubListViewAdapter() {
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_pro_sub, parent, false);
        }

        TextView pro_sub_name = (TextView) convertView.findViewById(R.id.pro_sub_name);
        TextView pro_sub_major = (TextView) convertView.findViewById(R.id.pro_sub_major);
        ImageView pro_sub_call = (ImageView) convertView.findViewById(R.id.pro_sub_call);
        ImageView pro_sub_mail = (ImageView) convertView.findViewById(R.id.pro_sub_mail);

        ProSubListViewItem listData = mlistData.get(position);

        pro_sub_name.setText(listData.getPro_sub_name());
        pro_sub_major.setText(listData.getPro_sub_major());
        pro_sub_call.setImageDrawable(listData.getPro_sub_call());
        pro_sub_mail.setImageDrawable(listData.getPro_sub_mail());

        return convertView;
    }

    public void addItem(String name, String major, Drawable call, Drawable mail) {
        ProSubListViewItem addInfo = new ProSubListViewItem();
        addInfo.setPro_sub_name(name);
        addInfo.setPro_sub_major(major);
        addInfo.setPro_sub_call(call);
        addInfo.setPro_sub_mail(mail);

        mlistData.add(addInfo);
    }
}