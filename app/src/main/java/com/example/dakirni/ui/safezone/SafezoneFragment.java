package com.example.dakirni.ui.safezone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dakirni.R;
import com.example.dakirni.databinding.ActivitySafeZoneBinding;
import com.example.dakirni.databinding.FragmentSafezoneBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SafezoneFragment extends FragmentActivity implements OnMapReadyCallback {

    private SafezoneViewModel safezoneViewModel;
    private FragmentSafezoneBinding binding;

    private GoogleMap mMap;

    public View onCreate(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        safezoneViewModel =
                new ViewModelProvider(this).get(SafezoneViewModel.class);

        binding = FragmentSafezoneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in FSTG and move the camera
        LatLng fstg = new LatLng(31.643478, -8.021075);
        mMap.addMarker(new MarkerOptions().position(fstg).title("FSTG"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fstg));
    }

}