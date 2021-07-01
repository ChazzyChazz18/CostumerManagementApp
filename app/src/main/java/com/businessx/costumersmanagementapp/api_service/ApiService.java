package com.businessx.costumersmanagementapp.api_service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("data")
    Call<ClientResponse> obtainClientList();

}
