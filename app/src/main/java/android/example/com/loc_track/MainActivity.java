package android.example.com.loc_track;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient
        .OnConnectionFailedListener, LocationListener{

    private EditText lattitude;
    private EditText longitude;
    private EditText adderss;
    private Button button;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lattitude = (EditText) findViewById(R.id.latitudeEdit);
        longitude = (EditText) findViewById(R.id.longitudeEdit);
        adderss = (EditText) findViewById(R.id.addressEdit);
        button = (Button) findViewById(R.id.button);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }


    @Override public void onLocationChanged(Location location) {

    }

    @Override public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override public void onProviderEnabled(String s) {

    }

    @Override public void onProviderDisabled(String s) {

    }

    @Override public void onConnected(@Nullable Bundle bundle) {

    }

    @Override public void onConnectionSuspended(int i) {

    }

    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
