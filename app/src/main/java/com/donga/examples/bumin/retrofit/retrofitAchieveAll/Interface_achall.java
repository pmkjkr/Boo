package com.donga.examples.bumin.retrofit.retrofitAchieveAll;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_achall {
    @FormUrlEncoded
    @POST("/donga/getAllGrade")
    Call<Master> getAllGrade(@Field("stuId") String stuId, @Field("stuPw") String stuPw);
}