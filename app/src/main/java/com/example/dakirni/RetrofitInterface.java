package com.example.dakirni;

import com.example.dakirni.AdapterContact.Contact;
import com.example.dakirni.msgsAdapter.Message;
import com.example.dakirni.ui.safezone.SafeZone;

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
    Call<Void> addMessage(@Header("Auth-Token") String header, @Body Message sentMessage);

    @PUT("/api/message/delivered/{msgId}/{fatherKey}")
    Call<Message> updateMessage(@Path("msgId") Double msgId, @Path("fatherKey") String fatherKey);

    @GET("/api/message/get/{fatherKey}")
    Call<List<Message>> getMessages(@Header("Auth-Token") String header, @Path("fatherKey") String fatherKey);

    @GET("/api/message/get/undelivered/{fatherKey}")
    Call<List<Message>> getUndeliveredMessages(@Path("fatherKey") String fatherKey);


    @POST("/contacts/addcontact")
    Call<Void> addContact(@Header ("Auth-Token") String header, @Body Contact newContact);
    @GET("/contacts/allcontacts/{fatherKey}")
    Call<List<Contact>> getContacts(@Header ("Auth-Token") String header, @Path("fatherKey") String fatherKey);
    @POST("/contacts/updatecontact/{fatherKey}")
    Call<Void> updateContact(@Header ("Auth-Token") String header, @Body Contact updatedContact, @Path("fatherKey") String fatherKey);
    @POST("/contacts/deletecontact/{fatherKey}")
    Call<Void> deleteContact(@Header ("Auth-Token") String header, @Body Contact deletedContact, @Path("fatherKey") String fatherKey);
    @GET("/sons/allsons/{fatherKey}")
    Call<List<Contact>> getSons( @Path("fatherKey") String fatherKey);

    @GET("/safezone/getsafezoneAndroid/{fatherKey}")
    Call<List<SafeZone>> getMaps(@Path("fatherKey") String fatherKey);
}
