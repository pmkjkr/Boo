package com.donga.examples.bumin.Singleton;

/**
 * Created by pmk on 17. 2. 21.
 */
public class ManageSingleton {
    private static ManageSingleton mInstance = null;

    String token;
    String managerID;

    public static ManageSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new ManageSingleton();
        }
        return mInstance;
    }

    private ManageSingleton() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
}
