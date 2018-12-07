package com.infrasoft.infraoffer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("registration")
    Call<RegistrationResponse> registration(@Body Registration request);
}
