package com.example.dakirni;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.AdapterSon.ModelClassforson;
import com.example.dakirni.connectingToBackEnd.Authentification;
import com.example.dakirni.connectingToBackEnd.CrudFather;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.fatherObjects.FatherCrudResponse;
import com.example.dakirni.fatherObjects.FatherResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class List_Parent extends AppCompatActivity {

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassforson> fatherList;
    AdapterSon adapter;
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_father);
        initDataforFather();
        initRecyclerView();
        actionButton = findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddFather.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        mrecyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterSon(fatherList, this);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initDataforFather() {
        fatherList = new ArrayList<>();

        Retrofit retrofit = APIClient.getRetrofitInstance();
        CrudFather crudFather= retrofit.create(CrudFather.class);
        SonDbHelper sonDbHelper = new SonDbHelper(getApplicationContext());
        ArrayList<String> arrayList = sonDbHelper.lireToken();
        StringBuffer maListe = new StringBuffer();

        try {
            Iterator<String> iter = arrayList.iterator();
            while (iter.hasNext()) {
                maListe.append(iter.next());
            }
            Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();
        }catch (ArrayIndexOutOfBoundsException e){
            Toast.makeText(getApplicationContext(),"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
        }
//                Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();

        Call<List<FatherCrudResponse>> call = crudFather.getAllFathers(maListe.toString());
        call.enqueue(new Callback<List<FatherCrudResponse>>() {
            @Override
            public void onResponse(Call<List<FatherCrudResponse>> call, @NonNull Response<List<FatherCrudResponse>> response) {

                if (response.code() == 200) {
                    List<FatherCrudResponse> fathers  = response.body();
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    fatherList = new ArrayList<>();

                    for(int i=0;i<fathers.size();i++){


                        fatherList.add(new ModelClassforson(fathers.get(i).getPhoto(), fathers.get(i).getName(), "kEY:" + fathers.get(i).getKey(), "_______________________________________"));

                    }
                    initRecyclerView();
                }
                else if (response.code() == 409) {
                    Toast.makeText(getApplicationContext(),"User not found ! Try Signup !",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<FatherCrudResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();

            }
        });


//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
//

    }

}