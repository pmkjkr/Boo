package com.donga.examples.bumin.retrofit.retrofitRoom;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_room {
    @GET("/donga/getWebSeat")
    Call<Master4> getRoom();
}