package com.example.dakirni.connectingToBackEnd;

import com.example.dakirni.fatherObjects.FatherLogin;
import com.example.dakirni.fatherObjects.FatherRegister;
import com.example.dakirni.fatherObjects.FatherResponse;
import com.example.dakirni.sonObjects.SonLogin;
import com.example.dakirni.sonObjects.SonRegister;
import com.example.dakirni.sonObjects.SonResponse;

import retrofit2.Call;
import retrofit2.http.Body;


import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Authentification {
    @POST("/api/users/login")
    Call<SonResponse> loginSon(@Body SonLogin user);
    @POST("/api/users/register")
    Call<SonResponse> registerSon(@Body SonRegister user);
    @POST("/api/father/login")
    Call<FatherResponse> loginFather(@Body FatherLogin father);
    @POST("/api/father/register")
    Call<FatherResponse> registerFather(@Header("Auth-Token") String header, @Body FatherRegister father);

}
