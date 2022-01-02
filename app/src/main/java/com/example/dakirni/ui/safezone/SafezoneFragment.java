package com.example.dakirni.ui.safezone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.dakirni.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SafezoneFragment extends Fragment implements OnMapReadyCallback {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_safezone, container, false);
        SupportMapFragment supportMapFragment;
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            if(fragmentManager != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                supportMapFragment = SupportMapFragment.newInstance();
                fragmentTransaction.replace(R.id.map, supportMapFragment).commit();
            }
        }
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
        return root;
    }
    @Override public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            // Add a marker
            LatLng fstg = new LatLng(31.643478, -8.021075);
            googleMap.addMarker(new MarkerOptions().position(fstg).title("FSTG"));
            // Adjust camera, move it to the target
            CameraPosition camera_position = new CameraPosition.Builder().target(fstg).zoom(15.0f).build();
            CameraUpdate camera_update = CameraUpdateFactory.newCameraPosition(camera_position);
            googleMap.moveCamera(camera_update);
        }
    }
}