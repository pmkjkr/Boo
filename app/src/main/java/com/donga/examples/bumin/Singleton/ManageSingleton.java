package com.donga.examples.bumin.Singleton;

/**
 * Created by pmk on 17. 2. 21.
 */
public class ManageSingleton {
    private static ManageSingleton mInstance = null;

    String token;
    String managerID;
    String pcnotis_id;

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

    public String getPcnotis_id() {
        return pcnotis_id;
    }

    public void setPcnotis_id(String pcnotis_id) {
        this.pcnotis_id = pcnotis_id;
    }
}
