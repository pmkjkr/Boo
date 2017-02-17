package com.donga.examples.bumin;

import android.graphics.drawable.Drawable;

/**
 * Created by nature on 16. 7. 19.
 */
public class ProSubListViewItem {

    public String pro_sub_name;
    public String pro_sub_major;
    public Drawable pro_sub_call;
    public Drawable pro_sub_mail;

    public Drawable getPro_sub_call() {
        return pro_sub_call;
    }

    public void setPro_sub_call(Drawable pro_sub_call) {
        this.pro_sub_call = pro_sub_call;
    }

    public Drawable getPro_sub_mail() {
        return pro_sub_mail;
    }

    public void setPro_sub_mail(Drawable pro_sub_mail) {
        this.pro_sub_mail = pro_sub_mail;
    }

    public String getPro_sub_major() {
        return pro_sub_major;
    }

    public void setPro_sub_major(String pro_sub_major) {
        this.pro_sub_major = pro_sub_major;
    }

    public String getPro_sub_name() {
        return pro_sub_name;
    }

    public void setPro_sub_name(String pro_sub_name) {
        this.pro_sub_name = pro_sub_name;
    }
}