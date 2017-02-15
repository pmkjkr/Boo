package com.donga.examples.bumin.retrofit.retrofitEmpty;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_Empty {
    @GET("donga/empty/room")
    Call<Master> getEmpty(@Query("day") String day, @Query("from") String from, @Query("to") String to);
}