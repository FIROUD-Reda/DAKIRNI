package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dakirni.connectingToBackEnd.Authentification;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.sonObjects.SonRegister;
import com.example.dakirni.sonObjects.SonResponse;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {
    Retrofit retrofit = APIClient.getRetrofitInstance();
    Authentification retrofitInterface= retrofit.create(Authentification.class);
    Button btn_signup;
    EditText input_username;
    Uri imageUri;
    String stringedImage;

    EditText input_email;
    EditText input_password;
    Bitmap selectedImage;
    InputStream imageStream;
    EditText input_confirm_password;
    ImageView register_image;
    public final Integer RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_signup = findViewById(R.id.btn_signup);
        input_username = findViewById(R.id.input_username);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        register_image = findViewById(R.id.register_image);
        input_confirm_password = findViewById(R.id.input_confirm_password);
        register_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                if(input_confirm_password.getText().toString()==input_password.getText().toString()){
                    SonRegister sonRegister = new SonRegister(input_username.getText().toString(),input_email.getText().toString(),input_password.getText().toString(),stringedImage);

                    Call<SonResponse> call = retrofitInterface.registerSon(sonRegister);
Toast.makeText(getApplicationContext(),input_username.getText().toString(),Toast.LENGTH_LONG).show();
Toast.makeText(getApplicationContext(),input_email.getText().toString(),Toast.LENGTH_LONG).show();
Toast.makeText(getApplicationContext(),input_password.getText().toString(),Toast.LENGTH_LONG).show();
                    call.enqueue(new Callback<SonResponse>() {
                        @Override
                        public void onResponse(Call<SonResponse> call, Response<SonResponse> response) {

                            if (response.code() == 200) {
                                SonResponse result = response.body();
                                Toast.makeText(getApplicationContext(),response.body().get_id(),Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),response.body().getName(),Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),response.body().getEmail(),Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),response.body().getPassword(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),SonLoginActivity.class);
                                startActivity(intent);
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
//            }
        });
    }
    private String encodeImage(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // compress Bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, stream);
        // Initialize byte array
        byte[] bytes = stream.toByteArray();
        // get base64 encoded string
        String a = Base64.encodeToString(bytes, Base64.DEFAULT);

        return a;
        // set encoded text on textview
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                stringedImage = encodeImage(selectedImage);
                register_image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}