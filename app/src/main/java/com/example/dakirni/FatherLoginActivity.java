package com.example.dakirni;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dakirni.connectingToBackEnd.Authentification;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.database.father.FatherDbHelper;
import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.example.dakirni.fatherObjects.FatherLogin;
import com.example.dakirni.fatherObjects.FatherResponse;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FatherLoginActivity extends AppCompatActivity {
    String resultOfScan;
    Retrofit retrofit = APIClient.getRetrofitInstance();
    Authentification retrofitInterface = retrofit.create(Authentification.class);
    FatherDbHelper fatherDbHelper = new FatherDbHelper(FatherLoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father_login);
        TextView textView = (TextView) findViewById(R.id.login_father);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(FatherLoginActivity.this);
                intentIntegrator.setPrompt("To use flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );
        if (intentResult.getContents() != null) {
            resultOfScan = intentResult.getContents();
            AlertDialog.Builder builder = new AlertDialog.Builder(FatherLoginActivity.this);
            builder.setTitle("Result");
            builder.setMessage(resultOfScan);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FatherLogin fatherLogin = new FatherLogin(resultOfScan);
                    Toast.makeText(getApplicationContext(), resultOfScan, Toast.LENGTH_SHORT).show();
                    Call<FatherResponse> call = retrofitInterface.loginFather(fatherLogin);

                    call.enqueue(new Callback<FatherResponse>() {
                        @Override
                        public void onResponse(Call<FatherResponse> call, Response<FatherResponse> response) {

                            if (response.code() == 200) {
                                FatherResponse result = response.body();
                                fatherDbHelper.insererDonnee(response.body().get_id(), response.body().getName(), response.body().getKey(), response.body().getAge(), response.body().getRelation());
                                ArrayList<String> arrayList = fatherDbHelper.lireTouteDonnees();
                                StringBuilder maListe = new StringBuilder();

                                try {
                                    Iterator<String> iter = arrayList.iterator();
                                    while (iter.hasNext()) {
                                        maListe.append(iter.next());
//                                        Toast.makeText(getApplicationContext(), iter.next(), Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getApplicationContext(), "Auth done", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), maListe.toString(), Toast.LENGTH_SHORT).show();


                                  //


                                    Intent intent = new Intent(FatherLoginActivity.this, FatherChoiceActivity.class);
                                    startActivity(intent);

                                } catch (ArrayIndexOutOfBoundsException e) {

                                    Toast.makeText(getApplicationContext(), "Aucun Résultat trouvé !", Toast.LENGTH_SHORT).show();
                                }//
                            } else if (response.code() == 409) {
                                Toast.makeText(getApplicationContext(), "User not found ! Try Signup !", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<FatherResponse> call, Throwable t) {

                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialogInterface.dismiss();

                }
            });
            builder.show();
        } else {
            Toast.makeText(getApplicationContext(), "nthg there", Toast.LENGTH_LONG).show();
        }
        //this part is to be deleted afterwards after login as a father
    }
}