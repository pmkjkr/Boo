package com.donga.examples.bumin;

import android.graphics.drawable.Drawable;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class BoListViewItem {
    private Drawable iconDrawable;
    private String titleStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon;
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public Drawable getIcon() {
        return this.iconDrawable;
    }

    public String getTitle() {
        return this.titleStr;
    }
}
