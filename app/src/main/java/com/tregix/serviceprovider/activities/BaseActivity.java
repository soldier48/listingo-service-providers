package com.tregix.serviceprovider.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.tregix.serviceprovider.BuildConfig;
import com.tregix.serviceprovider.Interface.DialogInteractionListener;
import com.tregix.serviceprovider.Interface.OnDataLoadListener;
import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.Countries;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.Model.Provider.PrivacySettings;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.Provider.ProviderReviewListData;
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.Model.packages.PackageItem;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.os.Build.VERSION.SDK_INT;
import static com.tregix.serviceprovider.fragments.HomeFragment.PLACE_PICKER_REQUEST;

/**
 * Created by Confiz123 on 11/28/2017.
 */

public class BaseActivity extends AppCompatActivity implements
        OnDataLoadListener, OnListInteractionListener, View.OnClickListener, DialogInteractionListener {

    private static Locale myLocale;
    private ProgressDialog progressDialog;
    private static String appUrl = "https://play.google.com/store/apps/details?id=" ;
    private LocationManager locationManager;
    private Looper looper = null;
    private Criteria criteria ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        updateLocale(SharedPreferenceUtil.getStringValue(this,"locale",getString(R.string.default_locale)));

        progressDialog = new ProgressDialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setActionBar();
        setCriteria();
    }



    protected void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected void showProgressDialog(String msg) {
        try {
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.setMessage(msg);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(
                R.anim.no_anim, R.anim.slide_right_out);
        AppUtils.hideSoftKeyboard(this);
    }

    @Override
    public void onCategoriesLoad(List<Category> items) {

    }

    @Override
    public void onProviderLoad(List<ProviderModel> items) {

    }

    @Override
    public void onCountriesLoad(List<Countries> items) {

    }

    @Override
    public void onAppointmentsLoad(List<Appointment> items) {

    }

    @Override
    public void onServiceLoad(List<ProfileServices> items) {

    }

    @Override
    public void onReviewsLoad(List<ProviderReviewListData> items) {

    }

    @Override
    public void onUpdateFavorites(ProviderModel item) {

    }

    @Override
    public void onPrivacyLoaded(PrivacySettings item) {

    }

    @Override
    public void onBusinessHoursLoaded(BusinessHours item) {

    }

    @Override
    public void onJobsLoaded(List<JobItem> items) {

    }

    @Override
    public void onProfileLoaded(ProviderModel items) {

    }

    @Override
    public void onPackagesLoad(List<PackageItem> items) {

    }

    @Override
    public void onSuccess(String msg) {
        try {
            hideProgressDialog();
            AppUtils.showDialog(this, msg, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Constants.Errors errorCode, String error) {
        try {
            hideProgressDialog();
            AppUtils.showDialog(this, error, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onProviderListInteraction(ProviderModel item) {

    }

    @Override
    public void onCategoryListInteraction(Category item) {

    }

    @Override
    public void onAppointmentInteraction(Appointment item, int pos) {

    }

    @Override
    public void onProviderFavorite(ProviderModel item) {

    }

    @Override
    public void onJobItemSelection(JobItem item) {

    }

    @Override
    public void onUserMessageSelection(String path, int post) {

    }

    @Override
    public void onPackageSelection(PackageItem item) {

    }

    @Override
    public void removeItem(int position) {

    }

    protected void openAcitivty(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void openAcitivty(Intent intent2, Class<?> cls) {
        Intent intent = new Intent(this, cls);
        if (intent2 != null) {
            intent = intent2;
            intent.setClass(this, cls);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void openAcitivty(Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(Constants.DATA, bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void openAcitivty(ArrayList<String> categories, Class<?> cls, int code, String title, String seletectedItem) {
        Intent newIntent = new Intent(this, cls);
        newIntent.putStringArrayListExtra(Constants.DATA, categories);
        newIntent.putExtra(Constants.TITLE, title);
        newIntent.putExtra(Constants.SELECTED_ITEM,seletectedItem);
        startActivityForResult(newIntent, code);
    }

    protected void openActivityForResult(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(Constants.IS_RESULT_ACTIVITY, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, Constants.REQUEST_KEY_LOGIN);
    }

    @Override
    public void onClick(View view) {

    }

    protected void showDialogSignedUp(String msg, final DialogInteractionListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(msg)
                .setPositiveButton(R.string.title_login, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onPositiveClick(null);
                        } else {
                            openAcitivty(getIntent(), LoginActivity.class);
                            finish();
                        }
                        dialog.dismiss();

                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .show();
    }

    protected void pickLocation() {

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, PLACE_PICKER_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place myplace = Autocomplete.getPlaceFromIntent(data);
                setLocation(myplace.getAddress().toString(), myplace);
            }
        }
    }

    public void showRadiusDialog(final DialogInteractionListener listener, String val) {

        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        final View Viewlayout = inflater.inflate(R.layout.ui_radius_search,
                (ViewGroup) findViewById(R.id.layout_dialog));

        final TextView item1 = (TextView) Viewlayout.findViewById(R.id.txtItem1); // txtItem1

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        //popDialog.setTitle("Please Select Rank 1-100 ");
        popDialog.setView(Viewlayout);

        //  seekBar1
        final SeekBar seek1 = (SeekBar) Viewlayout.findViewById(R.id.seekBar1);
        if (val != null && !val.isEmpty()) {
            seek1.setProgress(Integer.parseInt(val));
        }
        item1.setText(getString(R.string.distance_in_miles) + val);
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Do something here with new value
                item1.setText(getString(R.string.distance_in_miles) + progress);

            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });

        // Button OK
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onPositiveClick(seek1.getProgress() + "");
                        dialog.dismiss();
                    }

                });


        popDialog.create();
        popDialog.show();

    }

    @Override
    public void onPositiveClick(String msg) {

    }

    protected void setLocation(String name, Place place) {

    }

    protected void setLocation(String name, String lat, String longitude) {

    }

    public void makePhoneCall(Context context){

    }

    public void makePhoneCall(Context context, String number) {
        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel:" + number));
            context.startActivity(phoneIntent);
        } else {
            getPhoneCallPermission();
        }
    }

    protected void getPhoneCallPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makePhoneCall(this);
                }
                break;
            }
        }
    }

    public void openEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public static void showRateDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Rate application")
                .setMessage("Please, rate the app at play store")
                .setPositiveButton("RATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (context != null) {
                            String link = "market://details?id=";
                            try {
                                context.getPackageManager()
                                        .getPackageInfo("com.android.vending", 0);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                                link = appUrl;
                            }
                            context.startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(link + context.getPackageName())));
                        }
                    }
                })
                .setNegativeButton("CANCEL", null);
        builder.show();
    }

    protected void shareApp() {
        String message = appUrl + BuildConfig.APPLICATION_ID;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Choose "));
    }

    protected void openWebview(String url,String title){
        AppUtils.openWebview(this,url,title);
    }

    protected void confirmLogout(){
        new AlertDialog.Builder(this)
                .setMessage(R.string.msg_logout)
                .setCancelable(false)
                .setPositiveButton(R.string.title_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferenceUtil.storeBooleanValue(BaseActivity.this, Constants.ISUSERLOGGEDIN, false);
                        openAcitivty(LoginActivity.class);
                        FirebaseAuth.getInstance().signOut();
                        finish();
                    }
                })
                .setNegativeButton(R.string.title_no, null)
                .show();
    }

    protected void updateLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    private void setCriteria() {
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }

    public void getUserLocation() {
        final LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            getLocation();
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

    private void getLocation() {
        if (SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                locationManager.requestSingleUpdate(criteria, locationListener, looper);
            }
        } else {
            locationManager.requestSingleUpdate(criteria, locationListener, looper);
        }
    }

    private String getAddress(double latitude, double longitude) {
        try {

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("Location Changes", location.toString());
            String latitude = location.getLatitude()+"";
            String longitude = String.valueOf(location.getLongitude());

            setLocation(getAddress(location.getLatitude(),location.getLongitude()), latitude,longitude);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Status Changed", String.valueOf(status));
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Provider Enabled", provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Provider Disabled", provider);
        }
    };

}
