package com.example.dakirni.ui.reminders;

import com.example.dakirni.AdapterReminder.Reminder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceRemainder {
    @POST("api/reminder")
    Call<Void> addReminder(@Header ("Auth-Token") String header  ,@Body RemainderForRetrofit remainderForRetrofit);
    @GET("/api/reminder/all/{fatherKey}")
    Call<List<Reminder>> getRemaiders(@Path("fatherKey") String fatherKey);
    @PUT("/api/reminder/{id}")
    Call<Void> updateReminder(@Path("id") String id, @Body RemainderForRetrofit remainderForRetrofit);
    @DELETE("/api/reminder/{id}")
    Call<Void> removereminder(@Path("id") String id);



}
