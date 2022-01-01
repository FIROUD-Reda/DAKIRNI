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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class AddContactActivity extends AppCompatActivity {

    private Button btn_confirm_add_contact;
    private Button btn_cancel_add_contact;
    private Button btn_add_contact_image;
    private EditText input_contact_name;
    private EditText input_contact_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        btn_confirm_add_contact = findViewById(R.id.btn_confirm_add_contact);
        btn_cancel_add_contact = findViewById(R.id.btn_cancel_add_contact);
        btn_add_contact_image = findViewById(R.id.btn_add_contact_image);
        input_contact_name = findViewById(R.id.input_contact_name);
        input_contact_number = findViewById(R.id.input_contact_number);
        btn_confirm_add_contact = findViewById(R.id.btn_confirm_add_contact);

        // add an event for the add image button
        btn_add_contact_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
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
            TextView tv_add_contact_image;
            tv_add_contact_image = findViewById(R.id.tv_add_contact_image);
            tv_add_contact_image.setText(str);
            ImageView imageView_add_contact;
            imageView_add_contact = (ImageView) findViewById(R.id.imageView_add_contact);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fullPhotoUri);
                imageView_add_contact.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}