package com.donga.examples.bumin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class RoomListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<RoomListViewItem> listViewItemList = new ArrayList<RoomListViewItem>();

    // ListViewAdapter의 생성자
    public RoomListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_room, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView text_room1 = (TextView) convertView.findViewById(R.id.text_room1);
        TextView text_room2 = (TextView) convertView.findViewById(R.id.text_room2);
        TextView text_room3 = (TextView) convertView.findViewById(R.id.text_room3);
        TextView text_room4 = (TextView) convertView.findViewById(R.id.text_room4);
        TextView text_room5 = (TextView) convertView.findViewById(R.id.text_room5);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        RoomListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        text_room1.setText(listViewItem.getTitle1());
        text_room2.setText(listViewItem.getTitle2());
        text_room3.setText(listViewItem.getTitle3());
        text_room4.setText(listViewItem.getTitle4());
        text_room5.setText(listViewItem.getTitle5() + "%");

//        String msg1 = text_room5.getText().toString();
//        String regex = "^100+@$";
//        if(validateEmail(text_room5.getText().toString())){
        if (50.0 <= Float.parseFloat(listViewItem.getTitle5()) && Float.parseFloat(listViewItem.getTitle5()) <= 100.0) {
            Log.i("validated" + pos, text_room5.getText().toString());
//            text_room5.setTextColor(Color.RED);

        } else {
            Log.i("notval" + pos, text_room5.getText().toString());
        }


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title1, String title2, String title3, String title4, String title5) {
        RoomListViewItem item = new RoomListViewItem();

        item.setTitle1(title1);
        item.setTitle2(title2);
        item.setTitle3(title3);
        item.setTitle4(title4);
        item.setTitle5(title5);

        listViewItemList.add(item);
    }

//    public static boolean validateEmail(String emailStr) {
//        final Pattern VALID_PERCENT_REGEX = Pattern.compile("100%");
//        Matcher matcher = VALID_PERCENT_REGEX.matcher(emailStr);
//        return matcher.find();
//    }
}