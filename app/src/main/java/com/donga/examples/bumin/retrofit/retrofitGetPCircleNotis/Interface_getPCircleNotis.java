package com.donga.examples.bumin.retrofit.retrofitGetPCircleNotis;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getPCircleNotis {
    @GET("getPCircleNotis")
    Call<Master> getPCircleNotis(@Header("Authorization") String token);
}