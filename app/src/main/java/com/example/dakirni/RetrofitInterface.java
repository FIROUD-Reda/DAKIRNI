package com.example.dakirni;

import com.example.dakirni.AdapterContact.Contact;
import com.example.dakirni.msgsAdapter.Message;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @POST("/api/message/post")
    Call<Void> addMessage(@Header ("Auth-Token") String header,@Body Message sentMessage);
    @PUT("/api/message/delivered/{msgId}/{fatherKey}")
    Call<Message> updateMessage(@Header ("Auth-Token") String header,@Path("msgId") Double msgId,@Path("fatherKey") String fatherKey);
    @GET("/api/message/get/{fatherKey}")
    Call<List<Message>> getMessages(@Header ("Auth-Token") String header,@Path("fatherKey") String fatherKey);
    @GET("/api/message/get/undelivered/{fatherKey}")
    Call<List<Message>> getUndeliveredMessages(@Path("fatherKey") String fatherKey);


    @POST("/contacts/addcontact")
    Call<Void> addContact(@Body Contact newContact);
    @GET("/contacts/allcontacts")
    Call<List<Contact>> getContacts();
    @POST("/contacts/updatecontact")
    Call<Void> updateContact(@Body Contact updatedContact);
    @POST("/contacts/deletecontact")
    Call<Void> deleteContact(@Body Contact deletedContact);
    @GET("/sons/allsons")
    Call<List<Contact>> getSons();
}
