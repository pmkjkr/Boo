package com.donga.examples.bumin.retrofit.retrofitPNormalNotis;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 8.
 */

public class Master {
    int result_code;
    ArrayList<Notice> result_body;

    public ArrayList<Notice> getResult_body() {
        return result_body;
    }

    public int getResult_code() {
        return result_code;
    }
}
