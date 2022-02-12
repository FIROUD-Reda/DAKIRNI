package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dakirni.AdapterContact.Contact;
import com.example.dakirni.ui.contacts.ContactsFragment;
import com.example.dakirni.ui.message.MessageFragment;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddContactActivity extends AppCompatActivity {

    private Button btn_confirm_add_contact;
    private Button btn_cancel_add_contact;
    private Button btn_add_contact_image;
    private EditText input_contact_name;
    private EditText input_contact_number;
    private EditText editText_contact_encoded_image;
    TextView tv_add_contact_image;
    ImageView imageView_add_contact;
    Bitmap bitmap;


    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.8.110:5000";

    String stringImage;
    Contact newContact = new Contact();
    Contact updatedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        btn_confirm_add_contact = findViewById(R.id.btn_confirm_add_contact);
        btn_cancel_add_contact = findViewById(R.id.btn_cancel_add_contact);
        btn_add_contact_image = findViewById(R.id.btn_add_contact_image);
        input_contact_name = findViewById(R.id.input_contact_name);
        input_contact_number = findViewById(R.id.input_contact_number);
        btn_confirm_add_contact = findViewById(R.id.btn_confirm_add_contact);
        editText_contact_encoded_image = findViewById(R.id.editText_contact_encoded_image);
        imageView_add_contact = (ImageView) findViewById(R.id.imageView_add_contact);

        // add an event for the add image button
        btn_add_contact_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        Bundle bundle = getIntent().getExtras();

        // Add new contact
        if (!bundle.getBoolean("update")) {
            // event for the confirm button, to add a new contact
            btn_confirm_add_contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addNewContact();
                }
            });
        }

        // Update existing contact
        if (bundle.getBoolean("update")) {
            if (bundle.getSerializable("contact") != null) {
                Gson gson = new Gson();
                updatedContact = gson.fromJson(getIntent().getStringExtra("contact"), Contact.class);
//                updatedContact = (Contact) bundle.getSerializable("contact");
                input_contact_name.setText(updatedContact.getTextview1());
                input_contact_number.setText(updatedContact.getTextview2());
                stringImage = updatedContact.getImageview();
                editText_contact_encoded_image.setText(stringImage);
                Bitmap bitmap = decodeImage(stringImage);
                imageView_add_contact.setImageBitmap(bitmap);

                btn_confirm_add_contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateContact();
                    }
                });
            }
        }

    }

    // implicit intent to select an image
    static final int REQUEST_IMAGE_OPEN = 1;

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    // After the image had been selected, the text view get the path, the image view get the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            String str = fullPhotoUri.getPath().toString();
            tv_add_contact_image = findViewById(R.id.tv_add_contact_image);
            tv_add_contact_image.setText(str);
//            imageView_add_contact = (ImageView) findViewById(R.id.imageView_add_contact);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fullPhotoUri);
                imageView_add_contact.setImageBitmap(bitmap);
                stringImage = encodeImage(bitmap);
                editText_contact_encoded_image.setText(stringImage);
//                Toast.makeText(getApplicationContext(), stringImage, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Image have been selected", Toast.LENGTH_LONG).show();
        }
    }

    private void addNewContact() {
        String name = input_contact_name.getText().toString();
        String number = input_contact_number.getText().toString();
        String image = stringImage;
        newContact.setTextview1(name);
        newContact.setTextview2(number);
        newContact.setImageview(image);


        Call<Void> call = retrofitInterface.addContact(newContact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                input_contact_name.setText("");
                input_contact_number.setText("");
                stringImage = "";
                editText_contact_encoded_image.setText("No image had been selected");
                imageView_add_contact.setImageBitmap(null);
                finish();
//                Intent intent = new Intent(getApplicationContext(), ContactsFragment.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateContact() {
        String name = input_contact_name.getText().toString();
        String number = input_contact_number.getText().toString();
        String image = stringImage;
        updatedContact.setTextview1(name);
        updatedContact.setTextview2(number);
        updatedContact.setImageview(image);

        Call<Void> call = retrofitInterface.updateContact(updatedContact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                input_contact_name.setText("");
                input_contact_number.setText("");
                stringImage = "";
                imageView_add_contact.setImageBitmap(null);
                finish();
//                Intent intent = new Intent(getApplicationContext(), ContactsFragment.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // compress Bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream);
        // Initialize byte array
        byte[] bytes = stream.toByteArray();
        // get base64 encoded string
        String encoded = Base64.encodeToString(bytes, Base64.DEFAULT);
        return encoded;
    }

    private Bitmap decodeImage(String stringImage) {
        byte[] bytes = Base64.decode(stringImage, Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        // set bitmap on imageView
        return bitmap;
    }

}