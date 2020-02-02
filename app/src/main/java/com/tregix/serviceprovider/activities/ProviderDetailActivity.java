package com.tregix.serviceprovider.activities;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.Provider.MarkFavoriteParam;
import com.tregix.serviceprovider.Model.Provider.ProfileAmenity;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.Utils.SharedPreferenceUtil;
import com.tregix.serviceprovider.Utils.UtilFirebaseAnalytics;
import com.tregix.serviceprovider.chat.ChatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ProviderDetailActivity extends BaseActivity implements OnMapReadyCallback {

    private User user;
    private TextView mCategoryView;
    private TextView mCompanyView;
    private TextView address;
    private TextView fax;
    private LinearLayout website;
    private LinearLayout mEmail;
    private LinearLayout mChat;
    private TextView mPhone;
    private LinearLayout mPhoneView;
    private ImageView mThumb;
    private TextView detail;
    private ProviderModel providerModel;
    private Button direction;
    private Button bookAppointment;
    private LinearLayout providerServices;
    private LinearLayout providerBusinessHours;
    private LinearLayout providerExperience;
    private LinearLayout providerQualification;
    private LinearLayout providerAwards;
    private LinearLayout providerLanguages;
    private LinearLayout providerAmenities;
    private MaterialRatingBar ratingBar;
    private TextView ratingReview;
    private ImageView favorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_provider_detail);
        initViews();
        setData();
        setListener();

        user = DatabaseUtil.getInstance().getUser();

        getSupportActionBar().setTitle(providerModel.getUsername());
    }

    private void setListener() {
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String website = providerModel.getWebsite() != null && providerModel.getWebsite().startsWith("http")
                        ? providerModel.getWebsite() : "http://" + providerModel.getWebsite();
                AppUtils.openWebview(ProviderDetailActivity.this, website, providerModel.getWebsite());
                if (user != null) {
                    UtilFirebaseAnalytics.logProviderEvent(Constants.EVENT_WEBSITE, providerModel.getID().toString(),
                            user.getData().getID().toString());
                } else {
                    UtilFirebaseAnalytics.logProviderEvent(Constants.EVENT_WEBSITE, providerModel.getID().toString(),
                            Constants.EMPTY_STRING);
                }
            }
        });
        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmail(providerModel.getEmail());
            }
        });

        mPhoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall(ProviderDetailActivity.this, providerModel.getPhone());
                if (user != null) {
                    UtilFirebaseAnalytics.logProviderEvent(Constants.EVENT_CALL, providerModel.getID().toString(),
                            user.getData().getID().toString());
                } else {
                    UtilFirebaseAnalytics.logProviderEvent(Constants.EVENT_CALL, providerModel.getID().toString(), Constants.EMPTY_STRING);
                }
            }
        });
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + providerModel.getLatitude() + "," + providerModel.getLongitude()));
                startActivity(intent);
            }
        });

        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getProfileService() == null || providerModel.getPrivacySettings().getProfileService().equals("on")) {
            providerServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAcitivty(getBundle(getString(R.string.tab_services), (Serializable) providerModel.getProfileServices()), ProviderServicesActivity.class);
                }
            });
        } else {
            providerServices.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getProfileHours() == null || providerModel.getPrivacySettings().getProfileHours().equals("on")) {

            providerBusinessHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAcitivty(getBundle(getString(R.string.tab_business_hours), (Serializable) providerModel.getBusinessHours()), BusinessHoursActivity.class);
                }
            });
        } else {
            providerBusinessHours.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getPrivacyQualification() == null || providerModel.getPrivacySettings().getPrivacyQualification().equals("on")) {

            providerExperience.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAcitivty(getBundle(getString(R.string.tab_experience), (Serializable) providerModel.getExperience()), ProviderExperienceActivity.class);
                }
            });
        } else {
            providerExperience.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getPrivacyQualification() == null || providerModel.getPrivacySettings().getPrivacyQualification().equals("on")) {
            providerQualification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAcitivty(getBundle(getString(R.string.tab_qualification), (Serializable) providerModel.getQualification()), ProviderQualificationActivity.class);
                }
            });
        } else {
            providerQualification.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getPrivacyAwards() == null || providerModel.getPrivacySettings().getPrivacyAwards().equals("on")) {
            providerAwards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAcitivty(getBundle(getString(R.string.tab_certificates), (Serializable) providerModel.getAwards()), ProviderAwardsActivity.class);
                }
            });
        } else {
            providerAwards.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getPrivacyLanguages() == null || providerModel.getPrivacySettings().getPrivacyLanguages().equals("on")) {
            providerLanguages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openAcitivty(getBundle(getString(R.string.tab_languages), (Serializable) providerModel.getProfileLanguages()), SingletemListActivity.class);
                }
            });
        } else {
            providerLanguages.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getPrivacyAmenity() == null || providerModel.getPrivacySettings().getPrivacyAmenity().equals("on")) {
            providerAmenities.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<String> amenity = new ArrayList<>();
                    for (ProfileAmenity profileAmenity : providerModel.getProfileAmenities()) {
                        amenity.add(profileAmenity.getName());
                    }
                    openAcitivty(getBundle(getString(R.string.tab_amenities), (Serializable) amenity), SingletemListActivity.class);
                }
            });
        } else {
            providerAmenities.setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getProfileGallery() == null || providerModel.getPrivacySettings().getProfileGallery().equals("on")) {
            findViewById(R.id.provider_media).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (providerModel.getProfileGalleryPhotos() != null) {
                        openAcitivty(getBundle(getString(R.string.txt_media), (Serializable) providerModel.getProfileGalleryPhotos()), GalleryActivity.class);
                    }else{
                        Toast.makeText(ProviderDetailActivity.this, getString(R.string.no_data_available), Toast.LENGTH_SHORT).show();
                    }                }
            });
        } else {
            findViewById(R.id.provider_media).setVisibility(View.GONE);
        }
        if (providerModel.getPrivacySettings() == null || providerModel.getPrivacySettings().getProfileAppointment() == null || providerModel.getPrivacySettings().getProfileAppointment().equals("on")) {
            bookAppointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (user != null) {
                        UtilFirebaseAnalytics.logProviderEvent(Constants.EVENT_APPOINTMENT,
                                providerModel.getID().toString(), user.getData().getID().toString());
                    } else {
                        UtilFirebaseAnalytics.logProviderEvent(Constants.EVENT_APPOINTMENT,
                                providerModel.getID().toString(), Constants.EMPTY_STRING);
                    }

                    openAppointmentActivity();
                }

            });
        } else {
            bookAppointment.setVisibility(View.INVISIBLE);
        }

        mChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferenceUtil.getBoolen(ProviderDetailActivity.this, Constants.ISUSERLOGGEDIN)) {
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constants.DATA, providerModel);
                        openAcitivty(bundle, ChatActivity.class);
                    } else {
                        showDialogSignedUp(getString(R.string.err_session_expire_to_chat), ProviderDetailActivity.this);
                    }
                } else {
                    showDialogSignedUp(getString(R.string.err_login_to_chat), ProviderDetailActivity.this);
                }
            }
        });
        findViewById(R.id.review_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ID, providerModel.getID());
                bundle.putString(Constants.TITLE, getString(R.string.title_reviews));
                openAcitivty(bundle, ProviderReviewsActivity.class);
            }
        });

        findViewById(R.id.provider_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferenceUtil.getBoolen(ProviderDetailActivity.this, Constants.ISUSERLOGGEDIN)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.DATA, providerModel);
                    openAcitivty(bundle, ReviewProviderActivity.class);
                } else {
                    showDialogSignedUp(getString(R.string.err_login_to_review), ProviderDetailActivity.this);
                }

            }
        });

        findViewById(R.id.provider_fvrt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferenceUtil.getBoolen(ProviderDetailActivity.this, Constants.ISUSERLOGGEDIN)) {
                    showProgressDialog(getString(R.string.msg_updating_fvrt));
                    RetrofitUtil.createProviderAPI().updateUserFavorites(
                            new MarkFavoriteParam(providerModel.getID(),
                                    DatabaseUtil.getInstance().getUser().getData().getID(),
                                    !providerModel.isfav())).enqueue
                            (RetrofitUtil.updateUserFavorites(providerModel, ProviderDetailActivity.this));

                } else {
                    showDialogSignedUp(getString(R.string.err_login_to_fvrt), ProviderDetailActivity.this);
                }
            }
        });
    }

    @Override
    public void onUpdateFavorites(ProviderModel item) {
        item.setIsfav(!item.isfav());
        updateFavorites(item);
        hideProgressDialog();
    }

    private void updateFavorites(ProviderModel item) {
        if (item.isfav()) {
            favorite.setBackground(getResources().getDrawable(R.drawable.ic_fav_filled));
        } else {
            favorite.setBackground(getResources().getDrawable(R.drawable.ic_heart));
        }
    }

    private void openAppointmentActivity() {
        if (SharedPreferenceUtil.getBoolen(ProviderDetailActivity.this, Constants.ISUSERLOGGEDIN)) {
            Intent intent = new Intent(ProviderDetailActivity.this, BookAppointmentActivity.class);
            intent.putExtra(Constants.DATA, providerModel);
            startActivity(intent);
        } else {
            showDialogSignedUp(getString(R.string.err_login_first), ProviderDetailActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_KEY_LOGIN && resultCode == RESULT_OK) {
            openAppointmentActivity();
        }
    }

    private Bundle getBundle(String title, Serializable data) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        bundle.putSerializable(Constants.DATA, data);
        return bundle;
    }

    @Override
    public void onPositiveClick(String msg) {
        super.onPositiveClick(msg);
        openActivityForResult(LoginActivity.class);
    }

    private void initViews() {
        mCategoryView = (TextView) findViewById(R.id.provider_category);
        mCompanyView = (TextView) findViewById(R.id.provider_company);
        mEmail = (LinearLayout) findViewById(R.id.email_provider);
        mPhone = (TextView) findViewById(R.id.provider_phone);
        mChat = findViewById(R.id.provider_chat);
        mPhoneView = findViewById(R.id.provider_phone_view);
        mThumb = findViewById(R.id.provider_thumbail);
        website = findViewById(R.id.provider_website);
        fax = findViewById(R.id.fax);
        address = findViewById(R.id.provder_address);
        detail = findViewById(R.id.provider_detail);
        direction = findViewById(R.id.direction);
        providerServices = findViewById(R.id.provider_services);
        providerBusinessHours = findViewById(R.id.provider_business_hours);
        providerExperience = findViewById(R.id.provider_experience);
        providerQualification = findViewById(R.id.provider_qualification);
        providerAwards = findViewById(R.id.provider_certificates);
        providerLanguages = findViewById(R.id.provider_languages);
        providerAmenities = findViewById(R.id.provider_amenities);
        bookAppointment = findViewById(R.id.provider_make_appointement);
        ratingBar = findViewById(R.id.provider_rating);
        ratingReview = findViewById(R.id.provider_rating_votes);
        favorite = findViewById(R.id.provider_fvrt);

    }

    private void setData() {
        providerModel = (ProviderModel) getIntent().getSerializableExtra(Constants.DATA);

        mCategoryView.setText(Html.fromHtml(providerModel.getCategory()));
        mCompanyView.setText(providerModel.getUsername());
        address.setText(providerModel.getAddress());
        fax.setText(providerModel.getFax());
        mPhone.setText(getString(R.string.txt_call) + providerModel.getPhone());
        detail.setText(Html.fromHtml(providerModel.getProfessionalStatements()));
        Picasso.get().load(providerModel.getAvatar()).placeholder(R.drawable.placeholder).into(mThumb);

        if (providerModel.getReviewData() != null) {
            if (providerModel.getReviewData().getSpAverageRating() != null) {
                ratingBar.setRating(providerModel.getReviewData().getSpAverageRating().floatValue());
            }
            if (providerModel.getReviewData().getSpTotalPercentage() != 0 &&
                    providerModel.getReviewData().getSpTotalRating() != null) {
                String text = providerModel.getReviewData().getSpTotalPercentage() + "% "
                        + "(" + providerModel.getReviewData().getSpTotalRating() +
                        getResources().getQuantityString(R.plurals.numberOfVotes,
                                providerModel.getReviewData().getSpTotalRating()) + ")";
                ratingReview.setText(text);
            }
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (providerModel.getBusinessHoursFormat().equals(Constants.EMPTY_STRING)) {
            bookAppointment.setVisibility(View.INVISIBLE);
        }
        updateFavorites(providerModel);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            if (googleMap != null
                    && providerModel != null
                    && providerModel.getLatitude() != null
                    && providerModel.getLongitude() != null
                    && !providerModel.getLatitude().isEmpty()
                    && !providerModel.getLongitude().isEmpty()) {
                LatLng newLatLong = new LatLng(Double.parseDouble(providerModel.getLatitude()),
                        Double.parseDouble(providerModel.getLongitude()));
                googleMap.addMarker(new MarkerOptions().position(newLatLong));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLong, 15f));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void makePhoneCall(Context context) {
        makePhoneCall(context, providerModel.getPhone());
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra(Constants.DATA, providerModel);
        setResult(RESULT_OK, intent);
        super.onBackPressed();

    }
}
