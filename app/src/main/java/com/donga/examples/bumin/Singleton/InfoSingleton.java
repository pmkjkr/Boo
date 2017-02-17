package com.donga.examples.bumin.Singleton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pmk on 17. 2. 16.
 */
public class InfoSingleton {
    private static InfoSingleton mInstance = null;
    private String stuId, stuPw, year;

    public static InfoSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new InfoSingleton();
        }
        return mInstance;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
