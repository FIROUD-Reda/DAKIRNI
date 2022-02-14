package com.example.dakirni;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackingService extends Service {

    private static final String TAG = TrackingService.class.getSimpleName();
    environementVariablesOfDakirni env;

    private String BASE_URL = env.BASE_URL;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    public TrackingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        trackNotification();
        locationUpdates();
    }

    //Create the notification
    private void trackNotification() {
        Log.d("notif", "start");
        String stop = "stop";
        registerReceiver(stopReceiver, new IntentFilter(stop));
        PendingIntent broadcastIntent = PendingIntent.getBroadcast(
                this, 0, new Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT);

        // Create notification
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Tracking is currently enabled.")
                .setOngoing(true)
                .setContentIntent(broadcastIntent)
                .setSmallIcon(R.drawable.ic_baseline_location);
        startForeground(1, builder.build());

        Log.d("notif", "end");
    }

    private void safeZoneNotification(String notificationText) {
        Log.d("notif", "start");
        String stop = "stop";
        registerReceiver(stopReceiver, new IntentFilter(stop));
        PendingIntent broadcastIntent = PendingIntent.getBroadcast(
                this, 0, new Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT);

        // Create notification
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(notificationText)
                .setOngoing(true)
                .setContentIntent(broadcastIntent)
                .setSmallIcon(R.drawable.ic_baseline_warning_24);
        startForeground(1, builder.build());

        Log.d("notif", "end");
    }

    protected BroadcastReceiver stopReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("bc", "start");
            unregisterReceiver(stopReceiver);
        //Stop the Service
            stopSelf();
            Log.d("bc", "end");
        }
    };

    //Track the device's location
    private void locationUpdates() {

        Log.d("up", "start");
        LocationRequest request = new LocationRequest();

        //Specify the request interval

        request.setInterval(10000);

        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        // Check the location permission

        if (permission == PackageManager.PERMISSION_GRANTED) {

            // Request location updates

            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

            //Get a reference to the database

                    Location location = locationResult.getLastLocation();
                    //reference to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://dakirni-default-rtdb.firebaseio.com/");
                    DatabaseReference ref = database.getReference("location");
                    if (location != null) {
//                        ref.setValue(location);
                        ref.child("user_id").child("father_id").setValue(location);
//                        ref.child("user2_id").child("father2_id").setValue(location);
                    } else {
                        Log.e("Location", "No location found");
                    }
                }
            }, null);
        }
        Log.d("up", "end");
    }

}