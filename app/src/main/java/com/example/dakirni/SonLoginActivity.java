package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dakirni.connectingToBackEnd.Authentification;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.sonObjects.SonLogin;
import com.example.dakirni.sonObjects.SonResponse;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SonLoginActivity extends AppCompatActivity {

TextView forgotPassword;
EditText email;
EditText password;
    Retrofit retrofit = APIClient.getRetrofitInstance();
    Authentification retrofitInterface= retrofit.create(Authentification.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_login);
        TextView login= findViewById(R.id.login);
        SonDbHelper sonDbHelper = new SonDbHelper(getApplicationContext());

        email = findViewById(R.id.email_input);
        password = findViewById(R.id.pass);
        forgotPassword = findViewById(R.id.forgotPassword);
        ArrayList<String> arrayList = sonDbHelper.lireTouteDonnees();
        try {
            Iterator<String> iter = arrayList.iterator();
            StringBuffer maListe = new StringBuffer();
            while (iter.hasNext()) {
                maListe.append(iter.next() + " ");
            }
            Toast.makeText(this,maListe.toString(),Toast.LENGTH_SHORT).show();
        }catch (ArrayIndexOutOfBoundsException e){
            Toast.makeText(this,"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
        }
forgotPassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent fp = new Intent(getApplicationContext(),SignUp.class);
        startActivity(fp);
    }
});
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SonLogin sonLogin = new SonLogin(email.getText().toString(),password.getText().toString());

                Call<SonResponse> call = retrofitInterface.loginSon(sonLogin);

                call.enqueue(new Callback<SonResponse>() {
                    @Override
                    public void onResponse(Call<SonResponse> call, Response<SonResponse> response) {

                        if (response.code() == 200) {
                            SonResponse result = response.body();
                            Toast.makeText(getApplicationContext(), response.body().get_id(), Toast.LENGTH_SHORT).show();
                            sonDbHelper.insererDonnee(response.headers().get("Auth-Token"), response.body().get_id(),response.body().getName(),response.body().getEmail(),response.body().getPassword());
                            Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                            ArrayList<String> arrayList = sonDbHelper.lireToken();
                            try {
                                Iterator<String> iter = arrayList.iterator();
                                StringBuffer maListe = new StringBuffer();
                                while (iter.hasNext()) {
                                    maListe.append(iter.next() + " ");
                                }
                                Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent = new Intent(SonLoginActivity.this, List_Parent.class);
                                startActivity(intent);

                            }catch (ArrayIndexOutOfBoundsException e){
                                Toast.makeText(getApplicationContext(),"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if (response.code() == 409) {
                            Toast.makeText(getApplicationContext(),"User not found ! Try Signup !",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SonResponse> call, Throwable t) {

                    }
                });



            }
        });
    }

}