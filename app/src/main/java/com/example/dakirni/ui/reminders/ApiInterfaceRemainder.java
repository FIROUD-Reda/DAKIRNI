package com.example.dakirni.ui.reminders;

import com.example.dakirni.AdapterReminder.Reminder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceRemainder {
    /*@GET("posts/{id}")
    public Call<post> getpost(@Path("id") int postId);
    @GET("/comments?postId=1")
    public Call<List<posts>> getAllposts (@Query("postId") String postId);*/
//    @FormUrlEncoded
//    @POST("api/reminder")
//    public Call<RemainderForRetrofit> StoreRemainder (
//            @Field("Reminder_title") String Reminder_title,
//            @Field("Reminder_content") String Reminder_content,
//            @Field("isRepeat") Boolean isRepeat
//    );
    @POST("api/reminder")
    Call<Void> addReminder(@Body RemainderForRetrofit remainderForRetrofit);
    @GET("/api/reminder/all/{id}")
    Call<List<Reminder>> getRemaiders(@Path("id") String id);
    @PUT("/api/reminder/{id}")
    Call<Void> updateReminder(@Path("id") String id, @Body RemainderForRetrofit remainderForRetrofit);
    @DELETE("/api/reminder/{id}")
    Call<Void> removereminder(@Path("id") String id );



}
