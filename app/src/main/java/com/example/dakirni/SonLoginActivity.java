package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SonLoginActivity extends AppCompatActivity {

TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_login);
        TextView login= findViewById(R.id.login);
forgotPassword = findViewById(R.id.forgotPassword);
forgotPassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent fp = new Intent(getApplicationContext(),ForgotPassword.class);
        startActivity(fp);
    }
});
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SonLoginActivity.this,List_Parent.class);
                startActivity(intent);
            }
        });
    }

}