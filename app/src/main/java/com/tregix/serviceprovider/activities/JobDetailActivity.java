package com.tregix.serviceprovider.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;

public class JobDetailActivity extends BaseActivity implements OnMapReadyCallback {

    private TextView jobTitle;
    private TextView jobCategory;
    private TextView address;
    private TextView fax;
    private LinearLayout website;
    private LinearLayout mEmail;
    private TextView mPhone;
    private LinearLayout mPhoneView;
    private ImageView mThumb;
    private TextView detail;
    private Button direction;
    private Button applyNow;
    private TextView jobLevel;
    private TextView jobExp;
    private TextView jobSalary;
    private TextView jobType;
    private TextView jobQualification;
    private TextView jobLanguages;
    private TextView jobRequirements;
    private JobItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        initViews();
        setData();
        getSupportActionBar().setTitle(Html.fromHtml(item.getTitle()));

    }

    private void initViews() {
        jobTitle = (TextView) findViewById(R.id.job_title);
        jobCategory = (TextView) findViewById(R.id.job_category);
        mEmail = (LinearLayout) findViewById(R.id.email_job);
        mPhone = (TextView) findViewById(R.id.job_phone);
        mPhoneView = findViewById(R.id.job_phone_view);
        mThumb = findViewById(R.id.job_thumbail);
        website = findViewById(R.id.job_website);
        fax = findViewById(R.id.job_fax);
        address = findViewById(R.id.job_address);
        detail = findViewById(R.id.job_description);
        direction = findViewById(R.id.direction);
        jobLevel = findViewById(R.id.job_level);
        jobExp = findViewById(R.id.job_experience);
        jobSalary = findViewById(R.id.job_salary);
        jobType = findViewById(R.id.job_type);
        jobQualification = findViewById(R.id.job_qualification);
        jobLanguages = findViewById(R.id.job_language);
        jobRequirements = findViewById(R.id.job_requirements);
        applyNow = findViewById(R.id.job_apply_now);

        MapFragment smf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        smf.getMapAsync(this);

        mPhoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall(JobDetailActivity.this, item.getPhone());
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmail(item.getEmail());
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.openWebview(JobDetailActivity.this,
                        "http://" + item.getUserUrl(), item.getUserUrl());
            }
        });

        applyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.openWebview(JobDetailActivity.this,
                        item.getLink(), item.getTitle());
            }
        });

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + item.getLatitude() + "," + item.getLongitude()));
                startActivity(intent);
            }
        });
    }


    private void setData() {
        try {

            item = (JobItem) getIntent().getBundleExtra(Constants.DATA).getSerializable(Constants.DATA);

            jobTitle.setText(Html.fromHtml(item.getTitle()));
            jobRequirements.setText(Html.fromHtml(item.getRequirements()));
            mPhone.setText(item.getPhone());
            fax.setText(item.getFax());
            address.setText(item.getProfileAddress());
            detail.setText(Html.fromHtml(item.getDescription()));
            jobLevel.setText(item.getCareerLevel());
            jobExp.setText(item.getExperience());
            jobSalary.setText(item.getSalary());
            jobType.setText(item.getJobType());
            jobQualification.setText(item.getQualification());
            jobLanguages.setText(TextUtils.join(", ", item.getLanguages()));

            Picasso.get().load(item.getAvatar()).into(mThumb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null && item.getLatitude() != null
                && item.getLongitude() != null
                && !item.getLatitude().isEmpty()
                && !item.getLongitude().isEmpty()) {
            LatLng newLatLong = new LatLng(Double.parseDouble(item.getLatitude()),
                    Double.parseDouble(item.getLongitude()));
            googleMap.addMarker(new MarkerOptions().position(newLatLong));
            googleMap.setIndoorEnabled(false);
           googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLatLong, 15f));
        }
    }
}
