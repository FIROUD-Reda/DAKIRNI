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

public class TrackingService extends Service {

    private static final String TAG = TrackingService.class.getSimpleName();
    environementVariablesOfDakirni env;

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
        trackNotification();
        locationUpdates();
    }

    //Create the persistent notification//

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

//    private void loginToFirebase() {
//        Log.d("login", "start");

////Authenticate with Firebase, using the email and password we created earlier//
//
//        String email = env.key+"@gmail.com";
//        String password = env.key;
//
//
////Call OnCompleteListener if the user is signed in successfully//
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(
//                email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(Task<AuthResult> task) {
//
////If the user has been authenticated...//
//
//                if (task.isSuccessful()) {
//
////...then call locationUpdates//
////                    Toast.makeText(getApplicationContext(),"auth",Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Firebase authentication done");
//                    locationUpdates();
//                } else {
//
////If sign in fails, then log the error//
//
//                    Log.d(TAG, "Firebase authentication failed");
//                }
//            }
//        });

//        locationUpdates();
//        Log.d("login", "end");
//    }

    //Track the device's location//
    private void locationUpdates() {

        Log.d("up", "start");
        LocationRequest request = new LocationRequest();

        //Specify the request interval

        request.setInterval(10000);

        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
//        final String path = getString(R.string.firebase_path);
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        //Check the location permission

        if (permission == PackageManager.PERMISSION_GRANTED) {

//...then request location updates//

            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

//Get a reference to the database, so your app can perform read and write operations//

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

//                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Location");
//                    Location location = locationResult.getLastLocation();
//                    if (location != null) {
//
////Save the location data to the database//
//
//                        ref.setValue(location);
//                    }
                }
            }, null);
        }
        Log.d("up", "end");
    }

}