package com.tregix.serviceprovider.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.activities.BaseActivity;
import com.tregix.serviceprovider.activities.ProviderDetailActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION.SDK_INT;

public class NearByActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager mLocationManager;
    private static final long MIN_TIME = 0;
    private static final float MIN_DISTANCE = 0;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        statusCheck();
    }

    private void getNearBy() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            provider = mLocationManager.getBestProvider(criteria, false);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = mLocationManager.getLastKnownLocation(provider);

            // Initialize the location fields
            if (location != null && mMap != null) {
                mMap.setMyLocationEnabled(true);
                System.out.println("Provider " + provider + " has been selected.");
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(latLng).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                RetrofitUtil.createProviderAPI().searchProvider("application/json",
                        1,
                        -1,
                        "",
                        "",
                        5 + "",
                        "",
                        "",
                        "",
                        location.getLatitude() + "",
                        location.getLongitude() + "").enqueue(dataCallBack);
            }
        } else {
            getLocation();
        }
    }

    private void getLocation() {
        if (SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                getNearBy();
            }
        } else {
            getNearBy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                getLocation();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


    Callback<List<ProviderModel>> dataCallBack = new Callback<List<ProviderModel>>() {
        @Override
        public void onResponse(Call<List<ProviderModel>> call, Response<List<ProviderModel>> response) {
            if (response.isSuccessful()) {
                List<ProviderModel> data = new ArrayList<>();

                if (response.body() != null && !response.body().isEmpty()) {
                    data.addAll(response.body());

                    for (ProviderModel model : data) {
                        if (model.getLatitude() != null
                                && model.getLongitude() != null
                                && !model.getLatitude().isEmpty()
                                && !model.getLongitude().isEmpty()) {
                            LatLng latLng = new LatLng(Double.parseDouble(model.getLatitude()), Double.parseDouble(model.getLongitude()));

                            Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(model.getUsername())
                                    .snippet(model.getCategory())
                                    .icon(BitmapDescriptorFactory.fromBitmap(getImageBitmapFromURL(NearByActivity.this, model.getAvatar()))));

                            markers.put(marker, model);
                        }
                    }
                }
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
                // showNoData();
            }
        }

        @Override
        public void onFailure(Call<List<ProviderModel>> call, Throwable t) {
            t.printStackTrace();
            //showNoData();
        }
    };

    public static Bitmap getImageBitmapFromURL(final Context context, final String imageUrl) {
        Bitmap imageBitmap = null;
        try {
            imageBitmap = new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        int targetHeight = 120;
                        int targetWidth = 120;

                        return Picasso.get().load(String.valueOf(imageUrl))
                                .resize(targetWidth, targetHeight)
                                .placeholder(R.drawable.ic_user_circle)
                                .error(R.drawable.ic_user_circle)
                                .get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageBitmap;
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            getNearBy();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            getCurrentLocation();
        }

    }

    private Map<Marker, ProviderModel> markers = new HashMap<>();

    private void getCurrentLocation() {
        if (!mMap.isMyLocationEnabled())
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

        mMap.setMyLocationEnabled(true);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = null;
        if (lm != null) {
            myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);


            if (myLocation == null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                String provider = lm.getBestProvider(criteria, true);
                myLocation = lm.getLastKnownLocation(provider);
            }

            if (myLocation == null) {
                myLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (myLocation != null) {
                LatLng userLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10), 1500, null);


                RetrofitUtil.createProviderAPI().searchProvider("application/json",1,
                        "",
                        "",
                        10 + "",
                        "",
                        "",
                        "",
                        myLocation.getLatitude() + "",
                        myLocation.getLongitude() + "").enqueue(dataCallBack);

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        ProviderModel dataModel = (ProviderModel) markers.get(marker);
                        Intent detailActiivtyIntent = new Intent(NearByActivity.this, ProviderDetailActivity.class);
                        detailActiivtyIntent.putExtra(Constants.DATA, dataModel);
                        openAcitivty(detailActiivtyIntent, ProviderDetailActivity.class);
                        return false;
                    }
                });
            }


        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocation();
    }
}
