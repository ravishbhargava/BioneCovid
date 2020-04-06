package com.bione.corona;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.location.LocationListener;

import com.bione.corona.ui.DashBoardActivity;
import com.bione.corona.ui.MainActivity;
import com.bione.corona.ui.base.BaseActivity;
import com.bione.corona.ui.base.MyBroadCastReceiver;
import com.bione.corona.ui.base.MyJobService;
import com.bione.corona.utils.CommonData;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;


public class SplashActivity extends BaseActivity implements LocationListener {

    private FusedLocationProviderClient fusedLocationClient;
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;

    private LocationManager locationManager;
    private String provider;

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.i("aagya.", "aaagya");

        broadcastReceiver = new MyBroadCastReceiver();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle mBundle = new Bundle();
        mBundle.putString(FirebaseAnalytics.Param.LOCATION, "India");
        mFirebaseAnalytics.logEvent("location", mBundle);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "101");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Ravish");
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        MyApplication application = (MyApplication) getApplication();
        mTracker = application.getDefaultTracker();


        Log.i("screen", "Setting screen name: " + "name");
        mTracker.setScreenName(" main activity");
        mTracker.setLocation("Jalandhar");

        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Location")
                .setAction("New Location")
                .build());


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Log.i("location-----", "   --- lat  " + location.getLatitude());
                            Log.i("location-----", "   --- long  " + location.getLongitude());
                            mTracker.setLocation("lat: " + location.getLatitude() + "lng: " + location.getLongitude());
                            Bundle bundle = new Bundle();
                            bundle.putString("lat", "" + location.getLatitude());
                            bundle.putString("long", "" + location.getLongitude());
                            mFirebaseAnalytics.logEvent("Location", bundle);
                        } else {
                            Log.i("nullll-----", "   --- loc  ");
                        }
                    }
                });


        checkLocationPermission();

        createNotificationChannel();


        new Handler().postDelayed(new Runnable() {
            public void run() {
                // do something...
                createAlarm();
                if (CommonData.getPassword() == null) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (CommonData.getPassword().equalsIgnoreCase("")) {
                        Log.d("password", "not saved : " + CommonData.getPassword());
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("password", " saved : " + CommonData.getPassword());
                        Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        }, 2000);

//        // Get the location manager
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        // Define the criteria how to select the locatioin provider -> use
//        // default
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, true);
//
//        Location location = locationManager.getLastKnownLocation(provider);
//
//        // Initialize the location fields
//        if (location != null) {
//            System.out.println("Provider " + provider + " has been selected.");
//            onLocationChanged(location);
//        } else {
//            Toast.makeText(getApplicationContext(), "Location not available", Toast.LENGTH_SHORT).show();
////            longitudeField.setText("Location not available");
//        }


    }

    /**
     * create alarm for notification
     */
    private void createAlarm() {
        Intent myIntent = new Intent(SplashActivity.this, MyBroadCastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(SplashActivity.this, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.HOUR, 2);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

//        scheduleJob();
    }


    public void scheduleJob() {
        ComponentName componentName = new ComponentName(getApplicationContext(), MyJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(1000 * 15 * 60)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resulCode = jobScheduler.schedule(jobInfo);
        if (resulCode == JobScheduler.RESULT_SUCCESS) {
            Log.d("splash", "success");
        } else {
            Log.d("splash", "failed");
        }
    }

    public void cancelJob() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(123);
        Log.d("splash", "Job Cancel");
    }

    /**
     * Create the NotificationChannel, but only on API 26+ because
     * the NotificationChannel class is new and not in the support library
     */
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("-------------     notification created", " here...............");
            CharSequence name = "Covid";
            String description = "Bione";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("coronaId", name,
                    importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviours after this
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        } else {
            Log.d("---++++++++ ----- +++++++ -----     notification created", " else...............");
        }
    }


    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

//            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }

    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

//            locationManager.removeUpdates(this);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        Log.d("lat-- ", String.valueOf(lat));
        Log.d("long-- ", String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("checkLocationPermission", "1st if");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission")
                        .setMessage("Location perm")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(SplashActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                                Log.d("checkLocationPermission", "alert clicked");
                            }
                        })
                        .create()
                        .show();
                Log.d("checkLocationPermission", "2nd if");

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
                Log.d("checkLocationPermission", "2nd else ");
            }
            return false;
        } else {
            Log.d("checkLocationPermission", "1st else");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    Log.d("onRequestPermissionsResult", "1st if");
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.d("onRequestPermissionsResult", "2nd if");
                        //Request location updates:
//                        locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {
                    //Deny
                    Log.d("onRequestPermissionsResult", "else");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

}