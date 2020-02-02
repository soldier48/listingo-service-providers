package com.tregix.serviceprovider.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;
import com.tregix.serviceprovider.Model.ImageUpload.ImageUploadResponse;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.categories.SubCategory;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.ProviderApi;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.Utils.SharedPreferenceUtil;
import com.tregix.serviceprovider.adapters.ProfileImageRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION.SDK_INT;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getUserProfile;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.sendRequest;

public class ProfileActivity extends BaseActivity implements View.OnClickListener, OnMapReadyCallback {

    private static final int SELECT_PROFILE_PIC = 1;
    private static final int SELECT_BANNER_PIC = 2;

    private List<ImageUploadResponse> profileImagesObject;
    private List<ImageUploadResponse> bannerImagesObject;

    private RecyclerView recProfilePic;
    private RecyclerView recbannerPic;

    private EditText description;
    private EditText firstName;
    private EditText lastName;
    private EditText tagLine;
    private EditText phone;
    private EditText zipCode;
    private EditText fax;
    private EditText website;
    private EditText shortDescription;
    private TextView address;
    private EditText latitude;
    private EditText longitude;

    private Spinner country;
    private Spinner city;
    private Spinner language;
    private Spinner category;

    private Button btnAddPhoto;
    private Button btnAddBanner;
    private Button updateProfile;
    private Button addCategory;

    private ProviderModel userData;
    private MapFragment smf;
    private GoogleMap gMap;
    private ProfileImageRecyclerViewAdapter profileImageRecyclerViewAdapter;
    private ProfileImageRecyclerViewAdapter bannerImageRecyclerViewAdapter;
    private ProfileImageRecyclerViewAdapter categoryRecyclerViewAdapter;
    TagView tagGroup;

    List<SubCategory> subCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        setListener();


        profileImagesObject = new ArrayList<>();
        bannerImagesObject = new ArrayList<>();


        bannerImageRecyclerViewAdapter = new ProfileImageRecyclerViewAdapter(profileImagesObject);
        profileImageRecyclerViewAdapter = new ProfileImageRecyclerViewAdapter(bannerImagesObject);

        final LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recbannerPic.setLayoutManager(linearLayoutManager);
        recbannerPic.setAdapter(bannerImageRecyclerViewAdapter);

        final LinearLayoutManager profilePicLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recProfilePic.setLayoutManager(profilePicLayoutManager);
        recProfilePic.setAdapter(profileImageRecyclerViewAdapter);

        User user = DatabaseUtil.getInstance().getUser();

        if (user != null && SharedPreferenceUtil.getBoolen(this, Constants.ISUSERLOGGEDIN)) {
            int userId = user.getData().getID();

            showProgressDialog("Loading...");
            RetrofitUtil.createProviderAPI().getUserProfile(userId).enqueue(getUserProfile(this));
        }


    }

    private void initViews() {
        btnAddPhoto = (Button) findViewById(R.id.btn_add_photo);
        btnAddBanner = (Button) findViewById(R.id.btn_add_banner);
        updateProfile = findViewById(R.id.btn_update);

        recProfilePic = (RecyclerView) findViewById(R.id.recyclerview_profile);
        recbannerPic = (RecyclerView) findViewById(R.id.recyclerview_banner);

        description = findViewById(R.id.edtxt_description);
        firstName = findViewById(R.id.info_first_name);
        lastName = findViewById(R.id.info_last_name);
        tagLine = findViewById(R.id.info_tagline);
        phone = findViewById(R.id.info_phone);
        zipCode = findViewById(R.id.info_zip);
        fax = findViewById(R.id.info_fax);
        website = findViewById(R.id.info_url);
        shortDescription = findViewById(R.id.info_short_descrip);
        address = findViewById(R.id.info_location);
        longitude = findViewById(R.id.info_longitude);
        latitude = findViewById(R.id.info_latitude);

        country = findViewById(R.id.info_country);
        language = findViewById(R.id.spn_languages);
        city = findViewById(R.id.info_city);
        category = findViewById(R.id.spn_add_categories);

        addCategory = findViewById(R.id.btn_add_categories);
         tagGroup = (TagView)findViewById(R.id.tag_group);

        smf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (googleMap != null && userData.getLatitude() != null
                && userData.getLongitude() != null
                && !userData.getLatitude().isEmpty()
                && !userData.getLongitude().isEmpty()) {
            LatLng newLatLong = new LatLng(Double.parseDouble(userData.getLatitude()),
                    Double.parseDouble(userData.getLongitude()));
            googleMap.addMarker(new MarkerOptions().position(newLatLong));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLong, 15f));
        }
    }

    private void setDataToSpinners() {

        List<String> cat = DatabaseUtil.getInstance().getSubCategories(userData.getCategory());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        cat);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        category.setAdapter(spinnerArrayAdapter);

        subCategories = DatabaseUtil.getInstance().getAllSubCategories(userData.getCategory());

        for(String name: userData.getSubCategory()){
            for(SubCategory item : subCategories) {
                if(item.getSlug().equals(name)) {
                    Tag tag = new Tag(item.getTitle());
                    tag.isDeletable = true;
                    tagGroup.addTag(tag);
                    break;
                }
            }
        }

        tagGroup.setOnTagDeleteListener(new TagView.OnTagDeleteListener() {
            @Override
            public void onTagDeleted(final TagView view, final Tag tag, final int position) {
                for(SubCategory item : subCategories) {
                    if(item.getTitle().equals(tag.text) || item.getSlug().equals(tag.text)) {
                        userData.getSubCategory().remove(item.getSlug());
                        tagGroup.remove(position);
                        break;
                    }
                }
            }
        });

        final List<String> countryData = DatabaseUtil.getInstance().getCountries();
        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        countryData);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        country.setAdapter(countryArrayAdapter);

        for(int i=0;i<countryData.size();i++){
            if(countryData.get(i).equals(userData.getCountry())){
                country.setSelection(i);
                break;
            }
        }

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                List<String> cities = DatabaseUtil.getInstance().getCities(countryData.get(i));

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (ProfileActivity.this, android.R.layout.simple_spinner_item,
                                cities);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                city.setAdapter(spinnerArrayAdapter);

                for(int pos=0;pos < cities.size();pos++){
                    if(cities.get(pos).equals(userData.getCity())){
                        city.setSelection(pos);
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setListener() {
        btnAddPhoto.setOnClickListener(this);
        btnAddBanner.setOnClickListener(this);
        updateProfile.setOnClickListener(this);
        findViewById(R.id.img_location).setOnClickListener(this);
        addCategory.setOnClickListener(this);
        address.setOnClickListener(this);
    }

    private void uploadFile(String path, Uri fileUri, final int type) {
        // create upload service client

        String username = SharedPreferenceUtil.getStringValue(this, Constants.USERNAME);
        String password = SharedPreferenceUtil.getStringValue(this, Constants.PASSWORD);


        if (!username.isEmpty() && !password.isEmpty()) {
            ProviderApi service =
                    RetrofitUtil.createProviderAPIV2(username, password);

            //
            try {
                // use the FileUtils to get the actual file by uri
                showProgressDialog(getString(R.string.msg_uploading));
                File file = new File(path);

                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(getContentResolver().getType(fileUri)),
                                file
                        );

                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                // finally, execute the request
                Call<ImageUploadResponse> call = service.upload(body);
                call.enqueue(new Callback<ImageUploadResponse>() {
                    @Override
                    public void onResponse(Call<ImageUploadResponse> call,
                                           Response<ImageUploadResponse> response) {
                        hideProgressDialog();
                        Log.v("Upload", "success");
                        ImageUploadResponse item = response.body();

                        try {
                            if (item != null) {

                                item.setSelected(true);
                                if (type == SELECT_PROFILE_PIC) {
                                    profileImageRecyclerViewAdapter.addNewItem(item);
                                    profileImageRecyclerViewAdapter.notifyDataSetChanged();
                                } else {
                                    bannerImageRecyclerViewAdapter.addNewItem(item);
                                    bannerImageRecyclerViewAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                        AppUtils.showDialog(ProfileActivity.this, getString(R.string.err_something_wrong), null);
                        Log.e("Upload error:", t.getMessage());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showDialogSignedUp(getString(R.string.err_session_expire_to_chat), null);
        }

    }

    @Override
    public void onProfileLoaded(ProviderModel item) {
        userData = item;
        if (item != null) {

            if(smf !=null)
            smf.getMapAsync(this);

            setDataToSpinners();
            description.setText(item.getProfessionalStatements());
            firstName.setText(item.getFirstName());
            lastName.setText(item.getLastName());
            tagLine.setText(item.getTagLine());
            address.setText(item.getAddress());
            fax.setText(item.getFax());
            phone.setText(item.getPhone());
            website.setText(item.getWebsite());
            zipCode.setText(item.getZip());
            longitude.setText(item.getLongitude());
            latitude.setText(item.getLatitude());
            shortDescription.setText(item.getDescription());

            category.setPrompt(item.getCategory());
            country.setPrompt(item.getCountry());
            city.setPrompt(item.getCity());


            ImageUploadResponse pic = new ImageUploadResponse(-1,
                    item.getAvatar(), "Profile Photo", true);
            profileImageRecyclerViewAdapter.addNewItem(pic);
            profileImageRecyclerViewAdapter.notifyDataSetChanged();

            ImageUploadResponse banner = new ImageUploadResponse(-1,
                    item.getBanner(), "Banner Photo", true);

            bannerImageRecyclerViewAdapter.addNewItem(banner);
            bannerImageRecyclerViewAdapter.notifyDataSetChanged();

        }
        hideProgressDialog();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add_photo:
                PickImage(SELECT_PROFILE_PIC);
                break;
            case R.id.btn_add_banner:
                PickImage(SELECT_BANNER_PIC);
                break;
            case R.id.btn_update:
                updateProfile();
                break;
            case R.id.img_location:
                getUserLocation();
                break;
            case R.id.info_location:
                pickLocation();
                break;
            case R.id.btn_add_categories:
                updateCategories();
                break;
        }
    }




    private void updateCategories() {
            for(SubCategory item : subCategories) {
                if (item.getTitle().equals(category.getSelectedItem().toString())){
                    if (userData.getSubCategory() != null) {
                        if (!userData.getSubCategory().contains(item.getSlug())) {
                            addSubCategory(item);
                            break;
                        }
                    }else{
                        addSubCategory(item);
                        break;
                    }

            }
        }
    }

    private void addSubCategory(SubCategory item) {
        Tag tag = new Tag(category.getSelectedItem().toString());
        tag.isDeletable = true;
        tagGroup.addTag(tag);

        userData.getSubCategory().add(item.getSlug());
    }

    protected void setLocation(String name, Place plac) {
        address.setText(name);
        latitude.setText(plac.getLatLng().latitude + "");
        longitude.setText(plac.getLatLng().longitude + "");

        if (gMap != null) {
            LatLng newLatLong = new LatLng(plac.getLatLng().latitude,
                    plac.getLatLng().longitude);
            gMap.addMarker(new MarkerOptions().position(newLatLong));
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLong, 15f));
        }
    }

    protected void setLocation(String name, String lat, String lng) {
        address.setText(name);
        latitude.setText(lat);
        longitude.setText(lng);

        if (gMap != null) {
            LatLng newLatLong = new LatLng(Double.parseDouble(lat),
                    Double.parseDouble(lng));
            gMap.addMarker(new MarkerOptions().position(newLatLong));
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLong, 15f));
        }
    }

    private void updateProfile() {

        showProgressDialog(getString(R.string.msg_updating));

       // userData.setID(this.userData.getID());
        userData.setProfessionalStatements(description.getText().toString());
        userData.setFirstName(firstName.getText().toString());
        userData.setLastName(lastName.getText().toString());
        userData.setTagLine(tagLine.getText().toString());
        userData.setAddress(address.getText().toString());
        userData.setLatitude(latitude.getText().toString());
        userData.setLongitude(longitude.getText().toString());
        userData.setWebsite(website.getText().toString());
        userData.setFax(fax.getText().toString());
        userData.setPhone(phone.getText().toString());
        userData.setZip(zipCode.getText().toString());
        userData.setDescription(shortDescription.getText().toString());
        userData.setCountry(country.getSelectedItem().toString());

        if(city != null && city.getSelectedItem() !=  null)
        userData.setCity(city.getSelectedItem().toString());

        userData.setCategory(userData.getCategoryId());
        int pid = profileImageRecyclerViewAdapter.getSelectedItem().getId();

        if (pid != -1) {
            userData.setAvatarObject(profileImageRecyclerViewAdapter.getSelectedItem());
            userData.setAvatar(profileImageRecyclerViewAdapter.getSelectedItem().getSourceUrl());
        }


        int id = bannerImageRecyclerViewAdapter.getSelectedItem().getId();
        if (id != -1) {
            userData.setBannerObject(bannerImageRecyclerViewAdapter.getSelectedItem());
            userData.setBanner(bannerImageRecyclerViewAdapter.getSelectedItem().getSourceUrl());
        }

        RetrofitUtil.createProviderAPI().updateUserProfile(this.userData.getID(), userData)
                .enqueue(sendRequest(this));
    }

    private void PickImage(int type) {
        if (SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, type);
            } else {
                getImageFromGallery(type);
            }
        } else {
            getImageFromGallery(type);
        }
    }

    private void getImageFromGallery(int type) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, type);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SELECT_PROFILE_PIC:
                PickImage(SELECT_PROFILE_PIC);
                break;
            case SELECT_BANNER_PIC:
                PickImage(SELECT_BANNER_PIC);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PROFILE_PIC:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    uploadFile(filepath, uri, SELECT_PROFILE_PIC);

                }
                break;
            case SELECT_BANNER_PIC:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    uploadFile(filepath, uri, SELECT_BANNER_PIC);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(String msg) {
        super.onSuccess(msg);
        DatabaseUtil.getInstance().updateUser(userData.getBanner(),userData.getAvatar());
    }
}
