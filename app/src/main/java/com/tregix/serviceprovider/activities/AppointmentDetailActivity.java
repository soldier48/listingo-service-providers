package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.ManageAppointmentRequest;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;

public class AppointmentDetailActivity extends BaseActivity {

    private TextView name;
    private TextView aptType;
    private TextView aptService;
    private TextView aptDate;
    private TextView aptTime;
    private TextView aptReason;
    private TextView aptDescription;
    private Button approve;
    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmet_detail);
        initViews();
        setData();
        setListener();
    }

    private void setListener() {

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog("Updating...");
                RetrofitUtil.createProviderAPI().updateProviderAppointments
                        (new ManageAppointmentRequest(postId,"publish",
                                DatabaseUtil.getInstance().getUserID()))
                        .enqueue(RetrofitUtil.sendRequest(AppointmentDetailActivity.this));
            }
        });
    }

    private void initViews() {
        name = findViewById(R.id.apt_cust_name);
        aptTime = findViewById(R.id.apt_time);
        aptType = findViewById(R.id.apt_type);
        aptService = findViewById(R.id.apt_service);
        aptReason = findViewById(R.id.apt_reason);
        aptDescription = findViewById(R.id.apt_description);
        aptDate = findViewById(R.id.apt_date);
        approve = findViewById(R.id.apt_approve);
    }

    private void setData() {
        Appointment apt = (Appointment) getIntent().getBundleExtra(Constants.DATA).getSerializable(Constants.DATA);

        if(apt != null) {
            name.setText(apt.getUsername());
            aptTime.setText(apt.getAptTime());
            aptType.setText(apt.getAptTypes());
            aptService.setText(apt.getAptServices());
            aptReason.setText(apt.getAptReasons());
            aptDescription.setText(Html.fromHtml(apt.getAptDescription()));
            aptDate.setText(AppUtils.longToDate(apt.getAptDate(),"MMM d, yyyy",1000));
            postId = apt.getPostId();

            if (!apt.getStatus().equals("pending")) {
                approve.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPositiveClick(String msg) {
        super.onPositiveClick(msg);
        setResult(RESULT_OK);
        finish();
    }

}
