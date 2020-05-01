package com.carsyalla.www.cars;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsyalla.www.R;
import com.carsyalla.www.cars.library.checkConnection;
import com.carsyalla.www.cars.searchMap.PlaceAutocompleteAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class add_center_map extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    static LatLng mylating;
    static double current_lat, current_lng, mylat, mylng;
    int PLACE_PICKER_REQUEST = 1;
    float DEFAULT_ZOOM = 15f;
    Boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    ImageView currentlocation;
    Button confirm, goToSearch;
    AutoCompleteTextView search;
    static GoogleApiClient mGoogleApiClient;
    static PlaceAutocompleteAdapter placeAutocompleteAdapter;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection checkConnection = new checkConnection();
        if (checkConnection.isOnline(this) == true) {
            setContentView(R.layout.add_center_map);
            MultiDex.install(this);
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            supportMapFragment.getMapAsync(this);
            getDeviceLocation();
            //get lat and lng
            getLat_lng();
            //search for place
            searchPlace();
        } else {
            setContentView(R.layout.no_connection);
            onclickonconnection();
        }
    }

    //IF GRANTED PREMISSION DONE GET CURRENT LOCATION
    public void getloca() {
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    //GET CURRENT LOCATION
    private void getDeviceLocation() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location currentLocation = (Location) task.getResult();
                            current_lat = currentLocation.getLatitude();
                            current_lng = currentLocation.getLongitude();
                            Log.e("currentLocation", current_lat + "....." + current_lng);
                            moveCamera(new LatLng(current_lat, current_lng),
                                    15f);
                            mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                                @Override
                                public void onCameraIdle() {

                                    mylat = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude;
                                    mylng = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude;

                                }
                            });


                        } else {
                            Toast.makeText(add_center_map.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
        }
    }

    //GET CURRENT LOCATION CAMERA
    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    //GET PREMISSION IF YES OR NO
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    //initialize our map
                }
            }
        }
    }

    //search for place
    public void searchPlace() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);
        search = (AutoCompleteTextView) findViewById(R.id.search);
        search.setOnItemClickListener(mAutocompleteClickListener);
        search.setAdapter(placeAutocompleteAdapter);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER) {
                    geolocate();
                }
                return false;
            }
        });
        // search by press ok search
        goToSearch = (Button) findViewById(R.id.goToSearch);
        goToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    //go to search
    public void geolocate() {
        String searching = search.getText().toString();
        Geocoder geocoder = new Geocoder(add_center_map.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searching, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list.size() > 0) {
            Address address = list.get(0);
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();
            LatLng jk = new LatLng(latitude, longitude);
            String title = address.getAddressLine(0);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jk, 15.2f));
        }

    }


    /*
        --------------------------- google places API autocomplete suggestions -----------------
     */

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            final AutocompletePrediction item = placeAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                places.release();
                return;
            }
            final Place place = places.get(0);


            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM);

            places.release();
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        getLocationPermission();
        currentlocation = (ImageView) findViewById(R.id.currentlocation);
        currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getloca();
            }
        });
        getloca();

    }

    //confirm to get lat and lng
    private void getLat_lng() {
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("lat", "" + mylat);
                intent.putExtra("lng", "" + mylng);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //refresh page
    public void onclickonconnection() {
        Button noConnection = (Button) findViewById(R.id.tryagain);
        noConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                startActivity(intent);
            }
        });
    }
}
