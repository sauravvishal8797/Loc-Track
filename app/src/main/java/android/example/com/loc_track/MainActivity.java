package android.example.com.loc_track;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient
        .OnConnectionFailedListener, LocationListener {

    private EditText lattitude;
    private EditText longitude;
    private EditText adderss;
    private Button button;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location mLastlocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lattitude = (EditText) findViewById(R.id.latitudeEdit);
        longitude = (EditText) findViewById(R.id.longitudeEdit);
        adderss = (EditText) findViewById(R.id.addressEdit);
        button = (Button) findViewById(R.id.button);
        if(checkGooglePlayServices())
        {
            buildgoogleclientapi();
        }


    }

    private boolean checkGooglePlayServices(){

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultcode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if(resultcode != ConnectionResult.SUCCESS){

            if(googleApiAvailability.isUserResolvableError(resultcode)){
                googleApiAvailability.getErrorDialog(this, resultcode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }

            else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }

        return true;

    }

    protected synchronized void buildgoogleclientapi(){

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override protected void onStop() {
        super.onStop();
        if(googleApiClient.isConnected()){

            googleApiClient.disconnect();
        }

    }

    @Override public void onLocationChanged(Location location) {

        Double lat = location.getLatitude();
        Double lon = location.getLongitude();

        lattitude.setText(Double.toString(lat));
        longitude.setText(Double.toString(lon));

    }

    @Override public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override public void onProviderEnabled(String s) {

    }

    @Override public void onProviderDisabled(String s) {

    }

    @Override public void onConnected(@Nullable Bundle bundle) {

        locationRequest = LocationRequest.create();

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(googleApiClient,
                locationRequest, this)

    }

    @Override public void onConnectionSuspended(int i) {

    }

    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
