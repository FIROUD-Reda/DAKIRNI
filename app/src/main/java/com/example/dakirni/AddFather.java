package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dakirni.Crypto.Utils;
import com.example.dakirni.connectingToBackEnd.Authentification;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.fatherObjects.FatherRegister;
import com.example.dakirni.fatherObjects.FatherResponse;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddFather extends AppCompatActivity {
    ImageView upload;
    Bitmap selectedImage;
    InputStream imageStream;
    Uri imageUri;
    EditText myName;
    EditText myKey;
    EditText myAge;
    EditText myRelation;
    TextView add;
    EditText key;
    String stringedImage;
    String name;
    String key_str;
    String relation;
    int age;
    Retrofit retrofit = APIClient.getRetrofitInstance();
    Authentification retrofitInterface= retrofit.create(Authentification.class);

    public final Integer RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_father);
        Utils utils = new Utils();
        upload = findViewById(R.id.image_upload);
        key = findViewById(R.id.myKey);
        add = findViewById(R.id.addFather);
        myName = findViewById(R.id.myName);
        myAge = findViewById(R.id.myAge);
        myRelation = findViewById(R.id.myRelation);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = myName.getText().toString();
                key_str = key.getText().toString();
                age = Integer.parseInt(myAge.getText().toString());
                relation = myRelation.getText().toString();
                Toast.makeText(getApplicationContext(), stringedImage, Toast.LENGTH_SHORT).show();
                FatherRegister fatherRegister = new FatherRegister(name,key_str,age,relation,stringedImage);

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

                Call<FatherResponse> call = retrofitInterface.registerFather(maListe.toString(),fatherRegister);
                call.enqueue(new Callback<FatherResponse>() {
                    @Override
                    public void onResponse(Call<FatherResponse> call, Response<FatherResponse> response) {

                        if (response.code() == 200) {
                            FatherResponse result = response.body();
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.code() == 409) {
                            Toast.makeText(getApplicationContext(),"User not found ! Try Signup !",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<FatherResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();

                    }
                });

            }

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
                upload.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}