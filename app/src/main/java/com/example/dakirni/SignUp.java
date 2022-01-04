package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    Button btn_signup;
    EditText input_username;
    EditText input_email;
    EditText input_password;
    EditText input_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_signup = findViewById(R.id.btn_signup);
        input_username = findViewById(R.id.input_username);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_confirm_password = findViewById(R.id.input_confirm_password);
    }
}