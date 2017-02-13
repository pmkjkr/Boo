package com.donga.examples.bumin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by kim on 16. 8. 10.
 */
public class CalendarFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);

        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, date);
    }

    //텍스트에 선택한 날짜 나타내기
    @Override
    public void onDateSet(DatePicker view, int year, int month, int date) {
        TextView textView = (TextView)getActivity().findViewById(R.id.date_text);
        if(month<10&&date<10){
            String new_month = "0"+(month+1);
            String new_day = "0"+date;
            String stringOfDate = year+"년 "+new_month+ "월 " + new_day + "일";
            textView.setText(stringOfDate);
        }else if(month>=10&&date<10){
            String new_day = "0"+date;
            String stringOfDate = year+"년 "+(month+1)+ "월 " + new_day + "일" ;
            textView.setText(stringOfDate);
        }else if(month<10&&date>=10){
            String new_month = "0"+(month+1);
            String stringOfDate = year+"년 "+new_month+ "월 " + date + "일";
            textView.setText(stringOfDate);
        }else{
            String stringOfDate = year+"년 "+(month+1)+ "월 " + date + "일" ;
            textView.setText(stringOfDate);
        }
    }
}
