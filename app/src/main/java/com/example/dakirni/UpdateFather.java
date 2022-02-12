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

import com.example.dakirni.connectingToBackEnd.Authentification;
import com.example.dakirni.connectingToBackEnd.CrudFather;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.fatherObjects.FatherCrudResponse;
import com.example.dakirni.fatherObjects.FatherRegister;
import com.example.dakirni.fatherObjects.FatherResponse;
import com.example.dakirni.fatherObjects.FatherUpdate;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateFather extends AppCompatActivity {
    ImageView upload;
    Bitmap selectedImage;
    InputStream imageStream;
    Uri imageUri;
    EditText myName;
    EditText myKey;
    EditText myAge;
    EditText myRelation;
    TextView generate;
    TextView add;
    TextView update_send;
    EditText key;
    String stringedImage;
    String name;
    String key_str_http;
    String name_str_http;
    String age_str_http;
    String relation_str_http;
    String photo_str_http;
    String relation;
    int age;
    Retrofit retrofit = APIClient.getRetrofitInstance();
    CrudFather retrofitInterface = retrofit.create(CrudFather.class);
    public final Integer RESULT_LOAD_IMG = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_father);
        Intent intent = getIntent();
        key_str_http = intent.getStringExtra("key");
        age_str_http = intent.getStringExtra("age");
        name_str_http = intent.getStringExtra("name");
        relation_str_http = intent.getStringExtra("relation");
        photo_str_http = intent.getStringExtra("photo");
        upload = findViewById(R.id.image_upload_update);
        update_send = findViewById(R.id.updateFather);
        myName = findViewById(R.id.myName_update);
        myAge = findViewById(R.id.myAge_update);
        myRelation = findViewById(R.id.myRelation_update);
myName.setText(name_str_http);
myAge.setText(age_str_http);
myRelation.setText(relation_str_http);
upload.setImageBitmap(decodeImage(photo_str_http));
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });


        update_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = myName.getText().toString();
                age = Integer.parseInt(myAge.getText().toString());
                relation = myRelation.getText().toString();
                FatherUpdate fatherUpdate = new FatherUpdate(name,  age, relation, stringedImage);

                SonDbHelper sonDbHelper = new SonDbHelper(getApplicationContext());
                ArrayList<String> arrayList = sonDbHelper.lireToken();
                StringBuffer maListe = new StringBuffer();

                try {
                    Iterator<String> iter = arrayList.iterator();
                    while (iter.hasNext()) {
                        maListe.append(iter.next());
                    }
                    Toast.makeText(getApplicationContext(), maListe.toString(), Toast.LENGTH_SHORT).show();
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(getApplicationContext(), "Aucun Résultat trouvé !", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();

                Call<List<FatherCrudResponse>> call = retrofitInterface.updateOneFather(maListe.toString(), key_str_http,fatherUpdate);
                call.enqueue(new Callback<List<FatherCrudResponse>>() {
                    @Override
                    public void onResponse(Call<List<FatherCrudResponse>> call, Response<List<FatherCrudResponse>> response) {

                        if (response.code() == 200) {
                            List<FatherCrudResponse> result = response.body();
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 409) {
                            Toast.makeText(getApplicationContext(), "User not found ! Try Signup !", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<FatherCrudResponse>> call, Throwable t) {
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
    private Bitmap decodeImage(String stringedImage) {
        byte[] bytes = Base64.decode(stringedImage, Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        // set bitmap on imageView
        return bitmap;
    }
}