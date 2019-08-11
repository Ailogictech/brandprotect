package com.brandprotect.client.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {
    @GET("app-downloaded/{code}")
    Call<ConnectToServiceResponse> connectToService(@Path("code") String code);
}