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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class FatherLoginActivity extends AppCompatActivity {
String resultOfScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father_login);
        TextView textView=(TextView) findViewById(R.id.login_father);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(FatherLoginActivity.this);
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
        IntentResult intentResult=IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );
        if(intentResult.getContents()!=null){
            resultOfScan=intentResult.getContents();
            AlertDialog.Builder builder=new AlertDialog.Builder(FatherLoginActivity.this);
            builder.setTitle("Result");
            builder.setMessage(resultOfScan);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } else{
            Toast.makeText(getApplicationContext(),"nthg there",Toast.LENGTH_LONG).show();
        }
        //this part is to be deleted afterwards after login as a father
        Intent intent=new Intent(this,List_son.class);
        startActivity(intent);
    }
}