package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BaseLocationActivity extends AppCompatActivity implements BaseLocationDialog.BaseLocationDialogListener {

    private Button btn_save;
    private Button btn_get_location;
    private Button btn_insert_location;
    private TextView tv_get_longitude;
    private TextView tv_get_latitude;
    private TextView tv_insert_longitude;
    private TextView tv_insert_latitude;
    private EditText editText_set_location_radius;
    private RadioButton radioButton_automatic_detection;
    private RadioButton radioButton_manual_detection;
    private RadioGroup radioGroup_base_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_location);

        radioButton_automatic_detection = findViewById(R.id.radioButton_automatic_detection);
        radioButton_manual_detection = findViewById(R.id.radioButton_manual_detection);

        tv_get_longitude = findViewById(R.id.tv_get_longitude);
        tv_get_latitude = findViewById(R.id.tv_get_latitude);

        tv_insert_longitude = findViewById(R.id.tv_insert_longitude);
        tv_insert_latitude = findViewById(R.id.tv_insert_latitude);

        tv_insert_longitude = (TextView) findViewById(R.id.tv_insert_longitude);
        tv_insert_latitude = (TextView) findViewById(R.id.tv_insert_latitude);

        btn_get_location = (Button) findViewById(R.id.btn_get_location);

        btn_insert_location = (Button) findViewById(R.id.btn_insert_location);

        // Click listener, open a Dialog that allows to manually insert longitude and latitude
        btn_insert_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBaseLocationDialog();
            }
        });

        radioGroup_base_location = findViewById(R.id.radioGroup_base_location);

        // Checked Change Listener, to enable/disable button+correspondent text views depending on the checked radio button (automatic/manual detection)
        radioGroup_base_location.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                automaticDetectionChecked(radioButton_automatic_detection.isChecked());
            }
        });
    }

    // function, enable/disable Buttons and Text Views depending on is_checked value
    public void automaticDetectionChecked(Boolean is_checked) {
        btn_insert_location.setEnabled(!is_checked);
        btn_get_location.setEnabled(is_checked);
        tv_insert_longitude.setEnabled(!is_checked);
        tv_insert_latitude.setEnabled(!is_checked);
        tv_get_longitude.setEnabled(is_checked);
        tv_get_latitude.setEnabled(is_checked);
    }

    // function, show the Base Location Dialog
    public void openBaseLocationDialog() {
        BaseLocationDialog baseLocationDialog = new BaseLocationDialog();
        baseLocationDialog.show(getSupportFragmentManager(), "base location dialog");
    }

    // set Texts of the two Text Views to be equal to the longitude and latitude from the Base Location Dialog
    @Override
    public void setLocation(String longitude, String latitude) {
        tv_insert_longitude.setText(longitude);
        tv_insert_latitude.setText(latitude);
    }
}