package com.businessx.costumersmanagementapp.apiService;

import com.businessx.costumersmanagementapp.models.Client;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    @GET("db")
    Call<ClientResponse> obtainClientList();

    @POST("db")
    Call<ClientResponse> addNewClient(@Field("client") Client newClient);

    @PUT("db")
    Call<ClientResponse> modifyClient(@Field("id") int id);

    @DELETE("db")
    Call<ClientResponse> deleteClient(@Field("id") int id);

}
