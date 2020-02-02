package com.tregix.serviceprovider.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.tregix.serviceprovider.DataManager.CategoryDataManager;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.Utils.SharedPreferenceUtil;
import com.tregix.serviceprovider.Utils.UtilFirebaseAnalytics;
import com.tregix.serviceprovider.activities.AdvanceSearch;
import com.tregix.serviceprovider.activities.CategoryListActivity;
import com.tregix.serviceprovider.activities.NavigationDrawerActivity;
import com.tregix.serviceprovider.activities.ProviderListActivity;
import com.tregix.serviceprovider.activities.SearchResultActivity;
import com.tregix.serviceprovider.activities.SelectableItemActivity;
import com.tregix.serviceprovider.adapters.CategoryRecyclerViewAdapter;
import com.tregix.serviceprovider.adapters.FeaturedProviderListRecyclerViewAdapter;
import com.tregix.serviceprovider.adapters.SingleItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.os.Build.VERSION.SDK_INT;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getProviders;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.loadCountries;
import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

/**
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    public static final int REQUEST_CODE_CATEGORY = 100;
    public static final int REQUEST_CODE_SUB_CATEGORY = 101;
    public static final int REQUEST_CODE_COUNTRIES = 102;
    public static final int REQUEST_CODE_CITY = 103;
    public static final int PLACE_PICKER_REQUEST = 104;
    public static final int REQUEST_UPDATE_FAVORITE = 105;
    public static final int REQUEST_CODE_ENABLE_GPS = 106;

    private ShimmerRecyclerView categoryRecycler;
    private ShimmerRecyclerView featuredRecycler;
    private EditText keyword;
    private TextView category;
    private TextView location;
    private RelativeLayout categoryAll;
    private RelativeLayout featuredAll;
    private int userId;
    private String place;
    private String latitude;
    private String longitude;

    private LocationManager locationManager;
    private Looper looper = null;
    private Criteria criteria ;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Places.initialize(getApplicationContext(), getString(R.string.map_api_key));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_home);
        initViews(view);
        setListeners(view);
        User user = DatabaseUtil.getInstance().getUser();
        if (user != null && SharedPreferenceUtil.getBoolen(getActivity(), Constants.ISUSERLOGGEDIN)) {
            userId = user.getData().getID();
        }
        loadData();
        setCriteria();

        return view;
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

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
    }


    private void loadData() {
        /*List<Category> categoriesList = DatabaseUtil.getInstance().getCategoriesList();
        if (categoriesList != null && !categoriesList.isEmpty()) {
            onCategoriesLoad(categoriesList);
        }else{*/
        new CategoryDataManager().loadDataFromServer(this, true);
        //}

        RetrofitUtil.createProviderAPI().getFeaturedProviders(1, 25, userId, Constants.IS_FEATURED_CODE)
                .enqueue(getProviders(this));

        List<String> countries = DatabaseUtil.getInstance().getCountries();
        if (countries.isEmpty()) {
            RetrofitUtil.createProviderAPI().loadCountries().enqueue(loadCountries(this));
        }

    }

    private void initViews(View view) {
        keyword = view.findViewById(R.id.search_keyword);
        categoryAll = view.findViewById(R.id.categories_all);
        featuredAll = view.findViewById(R.id.featured_listing_all);
        category = view.findViewById(R.id.search_category);
        location = view.findViewById(R.id.search_location);
        categoryRecycler = view.findViewById(R.id.recyclerView_categories);
        featuredRecycler = view.findViewById(R.id.featured_listing);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        featuredRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setListeners(View view) {
        categoryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActiivtyIntent = new Intent(getActivity(), CategoryListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TITLE, getString(R.string.all_categories));
                detailActiivtyIntent.putExtra(Constants.DATA, bundle);
                startActivity(detailActiivtyIntent);
            }
        });

        featuredAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActiivtyIntent = new Intent(getActivity(), ProviderListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TITLE, getString(R.string.featured_provider));
                bundle.putString(Constants.IS_FEATURED, Constants.IS_FEATURED_CODE);
                detailActiivtyIntent.putExtra(Constants.DATA, bundle);
                startActivity(detailActiivtyIntent);
            }
        });
        view.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = keyword.getText() != null ? keyword.getText().toString() : Constants.EMPTY_STRING;
                Intent newIntent = new Intent(getActivity(), SearchResultActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(Constants.KEYWORD, key);
                bundle.putString(Constants.CATEGORY, category.getText().toString());
                bundle.putInt(Constants.CATEGORY_ID, DatabaseUtil.getInstance().getCategoryID(category.getText().toString()));
                if (location.getText() != null && !location.getText().toString().isEmpty()) {
                    bundle.putString(Constants.LOCATION, place);
                    bundle.putString(Constants.LATITUDE, latitude);
                    bundle.putString(Constants.LONGITUDE, longitude);
                }

                newIntent.putExtra(Constants.DATA, bundle);
                startActivity(newIntent);
                UtilFirebaseAnalytics.logEvent(Constants.EVENT_SEARCH, bundle);
            }
        });
        view.findViewById(R.id.home_advance_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), AdvanceSearch.class);
                startActivity(newIntent);
                UtilFirebaseAnalytics.logEvent(Constants.EVENT_CLICK, "Open", Constants.EVENT_ADVANCE_SEARCH);
            }
        });

        view.findViewById(R.id.search_category_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), SelectableItemActivity.class);
                ArrayList<String> categories = DatabaseUtil.getInstance().getCategories();
                newIntent.putStringArrayListExtra(Constants.DATA, categories);
                newIntent.putExtra(Constants.TITLE, getString(R.string.title_category));
                if (category.getText() != null) {
                    newIntent.putExtra(Constants.SELECTED_ITEM, category.getText().toString());
                }
                startActivityForResult(newIntent, REQUEST_CODE_CATEGORY);
            }
        });

        view.findViewById(R.id.current_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentLocation();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocationPicker();
            }
        });
    }

    private void openLocationPicker() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(getActivity());
        startActivityForResult(intent, PLACE_PICKER_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CATEGORY) {
            if (resultCode == RESULT_OK) {
                List categoryList = data.getStringArrayListExtra(Constants.DATA);
                if (categoryList != null && !categoryList.isEmpty()) {
                    category.setText(categoryList.get(0).toString());
                } else {
                    category.setText(Constants.EMPTY_STRING);
                }
            }
        }
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place myplace = Autocomplete.getPlaceFromIntent(data);
                location.setText(myplace.getAddress());
                LatLng queriedLocation = myplace.getLatLng();
                place = myplace.getAddress();
                if (queriedLocation != null) {
                    latitude = queriedLocation.latitude + "";
                    longitude = queriedLocation.longitude + "";
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if(requestCode == REQUEST_CODE_ENABLE_GPS){
            if (resultCode == RESULT_OK) {
                getLocation();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void notifyChange() {
        if (featuredRecycler.getAdapter() != null) {
            featuredRecycler.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onCategoriesLoad(List<Category> items) {
        if (items != null && items.size() > 3) {
            categoryRecycler.setAdapter(new CategoryRecyclerViewAdapter(items.subList(0, 3), HomeFragment.this));
        } else {
            categoryRecycler.setAdapter(new CategoryRecyclerViewAdapter(items, HomeFragment.this));
        }
    }

    @Override
    public void onProviderLoad(List<ProviderModel> items) {
        featuredRecycler.setAdapter(new FeaturedProviderListRecyclerViewAdapter(items, HomeFragment.this));
    }

    public void onError(Constants.Errors errorCode, String error) {
        try {
            List<String> list = new ArrayList<String>() {{
                add(getString(R.string.err_something_wrong));
            }};
            switch (errorCode) {
                case CATEGORY_FAILED:
                    setCategoryOnError(list);
                    break;
                case PROVIDER:
                    featuredRecycler.setAdapter(null);
                    break;

            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void setCategoryOnError(List<String> list) {
        List<Category> categoriesList = DatabaseUtil.getInstance().getCategoriesList();
        if (categoriesList != null && !categoriesList.isEmpty()) {
            onCategoriesLoad(categoriesList);
        } else {
            categoryRecycler.setAdapter(new SingleItemRecyclerViewAdapter(list));
        }
    }



    public void getCurrentLocation() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            getLocation();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                locationManager.requestSingleUpdate(criteria, locationListener, looper);
            }
        } else {
            locationManager.requestSingleUpdate(criteria, locationListener, looper);
        }
    }

    private void getAddress(double latitude, double longitude) {
        try {

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getActivity(), Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            place = address;
            location.setText(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("Location Changes", location.toString());
            latitude = location.getLatitude()+"";
            longitude = String.valueOf(location.getLongitude());
            getAddress(location.getLatitude(),location.getLongitude());
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
