package com.example.dakirni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class BaseLocationDialog extends AppCompatDialogFragment {

    private EditText input_base_longitude;
    private EditText input_base_latitude;
    private BaseLocationDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // set the View
        View view = inflater.inflate(R.layout.activity_base_location_dialog, null);

        // String variables to use defined string values
        String set = getString(R.string.set);
        String cancel = getString(R.string.cancel);

        input_base_longitude = view.findViewById(R.id.input_base_longitude);
        input_base_latitude = view.findViewById(R.id.input_base_latitude);

        // set elements of the Dialog : title + two buttons
        builder.setView(view)
                .setTitle("Set Base Location")
                .setPositiveButton(set, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // get the inserted values and set using the setLocation function
                        String longitude = input_base_longitude.getText().toString();
                        String latitude = input_base_latitude.getText().toString();
                        listener.setLocation(longitude, latitude);
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // return the created Dialog
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BaseLocationDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement BaseLocationDialogListener");
        }
    }

    // interface, must be implemented in other class to use the values of longitude and latitude1
    public interface BaseLocationDialogListener {
        void setLocation(String longitude, String latitude);
    }
}