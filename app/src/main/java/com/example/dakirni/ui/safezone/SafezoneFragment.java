package com.example.dakirni.ui.safezone;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dakirni.R;
import com.example.dakirni.RetrofitInterface;
import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SafezoneFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap googleMap;
    List<SafeZone> safeZoneList = new ArrayList<>();
    private String BASE_URL = environementVariablesOfDakirni.websiteDataUrl;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    Polygon polygon1,polygon2,polygon3;
    Handler mHandler = new Handler();
//    private static final int COLOR_WHITE_ARGB = 0xffffffff;
//    private static final int COLOR_DARK_GREEN_ARGB = 0xff388E3C;
//    private static final int COLOR_LIGHT_GREEN_ARGB = 0xff81C784;
//    private static final int COLOR_DARK_ORANGE_ARGB = 0xffF57F17;
//    private static final int COLOR_LIGHT_ORANGE_ARGB = 0xffF9A825;
//
//    private static final int POLYGON_STROKE_WIDTH_PX = 8;
//    private static final int PATTERN_DASH_LENGTH_PX = 20;
//    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
//
//    private static final int PATTERN_GAP_LENGTH_PX = 20;
//    private static final PatternItem DOT = new Dot();
//    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
//    private static final int COLOR_BLACK_ARGB = 0xff000000;


    // Create a stroke pattern of a gap followed by a dash.
//    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);
//
//    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
//    private static final List<PatternItem> PATTERN_POLYGON_BETA =
//            Arrays.asList(DOT, GAP, DASH, GAP);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_safezone, container, false);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        SupportMapFragment supportMapFragment;
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
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

//    private void getMapData() {
//
//        Call<List<SafeZone>> call = retrofitInterface.getMaps();
//
//        call.enqueue(new Callback<List<SafeZone>>() {
//
//            @Override
//            public void onResponse(Call<List<SafeZone>> call, Response<List<SafeZone>> response) {
//                if (response.body() != null) {
//
//                    safeZoneList = new ArrayList<>();
//                    safeZoneList.addAll(response.body());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<SafeZone>> call, Throwable t) {
////                Toast.makeText(root.getContext(), "We have sadly encountred an error while getting the maps: " + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

//    private List<LatLng> initMapData(List<SafeZone> safeZoneList) {
////        for (SafeZone safeZoneElement : safeZoneList) {
//        System.out.println("hadi safe zone list" + safeZoneList.get(0));
//        SafeZone safeZoneElement = safeZoneList.get(0);
//        List<LatLng> latLngList = new ArrayList<>();
//        for (int i = 0; i < safeZoneElement.getSafeZoneLang().size(); i++) {
//            LatLng latLng = new LatLng(safeZoneElement.getSafeZoneLang().get(i), safeZoneElement.getSafeZoneLat().get(i));
//            latLngList.add(latLng);
//        }
//        return latLngList;
////            PolygonOptions polygonOptions=new PolygonOptions().addAll(latLngList).clickable(true);
////           Polygon polygon=new Polygon()googleMap.addPolygon(polygonOptions);
//
//
////        }
//
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            Runnable mRunnableTask = new Runnable() {
                @Override
                public void run() {
                    Call<List<SafeZone>> call = retrofitInterface.getMaps(environementVariablesOfDakirni.key);

                    call.enqueue(new Callback<List<SafeZone>>() {

                        @Override
                        public void onResponse(Call<List<SafeZone>> call, Response<List<SafeZone>> response) {
                            if (response.body() != null) {
                              if(polygon1!=null)
                                  polygon1.remove();
                              if(polygon2!=null)
                                  polygon2.remove();
                              if(polygon3!=null)
                                  polygon3.remove();
                                safeZoneList = new ArrayList<>();
                                safeZoneList.addAll(response.body());
                                SafeZone zone1 = safeZoneList.get(0);
                                List<LatLng> latLngList = new ArrayList<>();
                                for (int i = 0; i < zone1.getSafeZoneLang().size(); i++) {
                                    LatLng latLng = new LatLng(zone1.getSafeZoneLat().get(i), zone1.getSafeZoneLang().get(i));
                                    latLngList.add(latLng);
                                }


                                 polygon1 = googleMap.addPolygon(new PolygonOptions()
                                        .clickable(true)
                                        .addAll(latLngList));
                                polygon1.setStrokeColor(Color.parseColor(zone1.getSafeZoneType()));
                                polygon1.setFillColor(Color.TRANSPARENT);
                                SafeZone zone2 = safeZoneList.get(1);
                                List<LatLng> latLngList2 = new ArrayList<>();
                                for (int i = 0; i < zone2.getSafeZoneLang().size(); i++) {
                                    LatLng latLng2 = new LatLng(zone2.getSafeZoneLat().get(i), zone2.getSafeZoneLang().get(i));
                                    latLngList2.add(latLng2);
                                }
                               polygon2 = googleMap.addPolygon(new PolygonOptions()
                                        .clickable(true)
                                        .addAll(latLngList2));
                                polygon2.setStrokeColor(Color.parseColor(zone2.getSafeZoneType()));
                                polygon2.setFillColor(Color.TRANSPARENT);
                                SafeZone zone3 = safeZoneList.get(2);
                                List<LatLng> latLngList3 = new ArrayList<>();
                                for (int i = 0; i < zone3.getSafeZoneLang().size(); i++) {
                                    LatLng latLng3 = new LatLng(zone3.getSafeZoneLat().get(i), zone3.getSafeZoneLang().get(i));
                                    latLngList3.add(latLng3);
                                }
                                polygon3 = googleMap.addPolygon(new PolygonOptions()
                                        .clickable(true)
                                        .addAll(latLngList3));
                                polygon3.setStrokeColor(Color.parseColor(zone3.getSafeZoneType()));
                                polygon3.setFillColor(Color.TRANSPARENT);
                                // Store a data object with the polygon, used here to indicate an arbitrary type.
//                        polygon1.setTag("alpha");
//                        // Style the polygon.
//                        stylePolygon(polygon1);

                            }

                        }

                        @Override
                        public void onFailure(Call<List<SafeZone>> call, Throwable t) {
//                Toast.makeText(root.getContext(), "We have sadly encountred an error while getting the maps: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    // this will repeat this task again at specified time interval
//            mHandler.postDelayed(this, 600000);
                    mHandler.postDelayed(this, 5000);
                }
            };
            mHandler.postDelayed(mRunnableTask, 100);



            // Add polygons to indicate areas on the map.
//            Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
//                    .clickable(true)
//                    .addAll(latLngList));
//            // Store a data object with the polygon, used here to indicate an arbitrary type.
//            polygon1.setTag("alpha");
//            // Style the polygon.
//            stylePolygon(polygon1);

//            Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
//                    .clickable(true)
//                    .add(
//                            new LatLng(-31.673, 128.892),
//                            new LatLng(-31.952, 115.857),
//                            new LatLng(-17.785, 122.258),
//                            new LatLng(-12.4258, 130.7932)));
//            polygon2.setTag("beta");
//            stylePolygon(polygon2);

//             Add a marker
            LatLng fstg = new LatLng(31.643478, -8.021075);
            googleMap.addMarker(new MarkerOptions().position(fstg).title("FSTG"));

//             Adjust camera, move it to the target
            CameraPosition camera_position = new CameraPosition.Builder().target(fstg).zoom(15.0f).build();
            CameraUpdate camera_update = CameraUpdateFactory.newCameraPosition(camera_position);
            googleMap.moveCamera(camera_update);
        }

    }

//    private void stylePolygon(Polygon polygon) {
//        String type = "";
//        // Get the data object stored with the polygon.
//        if (polygon.getTag() != null) {
//            type = polygon.getTag().toString();
//        }
//
//        List<PatternItem> pattern = null;
//        int strokeColor = COLOR_BLACK_ARGB;
//        int fillColor = COLOR_WHITE_ARGB;
//
//        switch (type) {
//            // If no type is given, allow the API to use the default.
//            case "alpha":
//                // Apply a stroke pattern to render a dashed line, and define colors.
//                pattern = PATTERN_POLYGON_ALPHA;
//                strokeColor = COLOR_DARK_GREEN_ARGB;
//                fillColor = COLOR_LIGHT_GREEN_ARGB;
//                break;
//            case "beta":
//                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
//                pattern = PATTERN_POLYGON_BETA;
//                strokeColor = COLOR_DARK_ORANGE_ARGB;
//                fillColor = COLOR_LIGHT_ORANGE_ARGB;
//                break;
//        }
//
//        polygon.setStrokePattern(pattern);
//        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
//        polygon.setStrokeColor(strokeColor);
//        polygon.setFillColor(fillColor);
//    }
//
}
//}public class SafezoneFragment extends Fragment implements OnMapReadyCallback {
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View root = inflater.inflate(R.layout.fragment_safezone, container, false);
//        SupportMapFragment supportMapFragment;
//        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//        if (supportMapFragment == null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            if(fragmentManager != null) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                supportMapFragment = SupportMapFragment.newInstance();
//                fragmentTransaction.replace(R.id.map, supportMapFragment).commit();
//            }
//        }
//        if (supportMapFragment != null) {
//            supportMapFragment.getMapAsync(this);
//        }
//        return root;
//    }
//    @Override public void onMapReady(GoogleMap googleMap) {
//        if (googleMap != null) {
//            googleMap.getUiSettings().setAllGesturesEnabled(true);
//            // Add a marker
//            LatLng fstg = new LatLng(31.643478, -8.021075);
//            googleMap.addMarker(new MarkerOptions().position(fstg).title("FSTG"));
//            // Adjust camera, move it to the target
//            CameraPosition camera_position = new CameraPosition.Builder().target(fstg).zoom(15.0f).build();
//            CameraUpdate camera_update = CameraUpdateFactory.newCameraPosition(camera_position);
//            googleMap.moveCamera(camera_update);
//        }
//    }
//}