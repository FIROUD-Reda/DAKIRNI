package com.example.dakirni;

import com.example.dakirni.msgsAdapter.Message;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @POST("/api/message/post")
    Call<Void> addMessage(@Body Message sentMessage);
    @PUT("/api/message/delivered/{msgId}")
    Call<Message> updateMessage(@Path("msgId") Double msgId);
    @GET("/api/message/get")
    Call<List<Message>> getMessages();
    @GET("/api/message/get/undelivered")
    Call<List<Message>> getUndeliveredMessages();


}
