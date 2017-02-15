package com.donga.examples.bumin.Singleton;

import java.util.Map;

/**
 * Created by pmk on 17. 2. 14.
 */
public class PushSingleton {
    private static PushSingleton mInstance = null;
    private String mString;
    private Map mMap;

    public static PushSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new PushSingleton();
        }
        return mInstance;
    }

    private PushSingleton() {

    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }

    public Map getmMap() {
        return mMap;
    }

    public void setmMap(Map mMap) {
        this.mMap = mMap;
    }
}
