package com.donga.examples.bumin.retrofit.retrofitLogin;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 8.
 */

public class Master {
    int result_code;
    ArrayList<Login> result_body;

    public ArrayList<Login> getResult_body() {
        return result_body;
    }

    public int getResult_code() {
        return result_code;
    }
}
