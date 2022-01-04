package com.example.dakirni;

import com.example.dakirni.msgsAdapter.Message;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/addMessage")
    Call<Message> addMessage(@Body Message sentMessage);

}
