package com.example.dakirni.connectingToBackEnd;

import com.example.dakirni.fatherObjects.FatherCrudResponse;
import com.example.dakirni.fatherObjects.FatherRegister;
import com.example.dakirni.fatherObjects.FatherResponse;
import com.example.dakirni.fatherObjects.FatherUpdate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudFather {
    @GET("/api/fathers")
    Call<List<FatherCrudResponse>> getAllFathers(@Header("Auth-Token") String header);
    @DELETE("/api/fathers/{key}")
    Call<List<FatherCrudResponse>> deleteOneFather(@Header("Auth-Token") String header, @Path("key") String key);
    @GET("/api/fathers/{key}")
    Call<FatherCrudResponse> getOneFather(@Header("Auth-Token") String header, @Path("key") String key);
    @PUT("/api/fathers/{key}")
    Call<List<FatherCrudResponse>> updateOneFather(@Header("Auth-Token") String header, @Path("key") String key, @Body() FatherUpdate fatherUpdate);
}
