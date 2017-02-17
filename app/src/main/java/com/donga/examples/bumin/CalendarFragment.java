package com.donga.examples.bumin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.bumin.Singleton.DateSingleton;
import com.donga.examples.bumin.retrofit.retrofitMeal.Interface_meal;
import com.donga.examples.bumin.retrofit.retrofitMeal.Master3;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kim on 16. 8. 10.
 */
public class CalendarFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private ProgressDialog mProgressDialog;

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
    public void onDateSet(DatePicker view, final int year, final int month, final int date) {
        final TextView textView = (TextView) getActivity().findViewById(R.id.date_text);

        if (month < 10 && date < 10) {
            String new_month = "0" + (month + 1);
            String new_day = "0" + date;
            String stringOfDate = year + "년 " + new_month + "월 " + new_day + "일";
            DateSingleton.getInstance().setmString(year + "-" + new_month + "-" + new_day);
            textView.setText(stringOfDate);
        } else if (month >= 10 && date < 10) {
            String new_day = "0" + date;
            String stringOfDate = year + "년 " + (month + 1) + "월 " + new_day + "일";
            DateSingleton.getInstance().setmString(year + "-" + (month + 1) + "-" + new_day);
            textView.setText(stringOfDate);
        } else if (month < 10 && date >= 10) {
            String new_month = "0" + (month + 1);
            String stringOfDate = year + "년 " + new_month + "월 " + date + "일";
            DateSingleton.getInstance().setmString(year + "-" + new_month + "-" + date);
            textView.setText(stringOfDate);
        } else {
            String stringOfDate = year + "년 " + (month + 1) + "월 " + date + "일";
            DateSingleton.getInstance().setmString(year + "-" + (month + 1) + "-" + date);
            textView.setText(stringOfDate);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        showProgressDialog();

        final TextView guk = (TextView) getActivity().findViewById(R.id.guk);
        final TextView bumin = (TextView) getActivity().findViewById(R.id.bumin);
        final TextView gang = (TextView) getActivity().findViewById(R.id.gang);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_meal meal = client.create(Interface_meal.class);
        retrofit2.Call<Master3> call3 = meal.getMeal(DateSingleton.getInstance().getmString());
        call3.enqueue(new Callback<Master3>() {
            @Override
            public void onResponse(Call<Master3> call, Response<Master3> response) {
                String source_guk = response.body().getResult_body().getInter();
                guk.setText(Html.fromHtml(source_guk));
                guk.setMovementMethod(LinkMovementMethod.getInstance());

                String source_bumin = response.body().getResult_body().getBumin_kyo();
                bumin.setText(Html.fromHtml(source_bumin));
                bumin.setMovementMethod(LinkMovementMethod.getInstance());

                String source_gang = response.body().getResult_body().getGang();
                gang.setText(Html.fromHtml(source_gang));
                gang.setMovementMethod(LinkMovementMethod.getInstance());

                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<Master3> call, Throwable t) {
                hideProgressDialog();
                Log.i("CALL3", "onFailure");
                t.printStackTrace();
            }
        });
    }

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
        }
    }
}