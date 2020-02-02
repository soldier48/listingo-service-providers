package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.ReviewProvider;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.adapters.ProviderReviewRatingAdapter;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.sendRequest;

/**
 * Created by Gohar Ali on 2/19/2018.
 */

public class ReviewProviderActivity extends BaseActivity {

    private Spinner waitTime;
    private TextView reviewTitle;
    private TextView reviewDescription;
    private Button recommendYes;
    private Button recommendNo;
    private Button submit;

    private RecyclerView recyclerView;

    private String watingTime = Constants.EMPTY_STRING;
    private String isRecommended = "yes";

    private ProviderModel provider ;
    private ProviderReviewRatingAdapter ratingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_provider);

        getSupportActionBar().setTitle(R.string.tite_leave_review);

        provider = (ProviderModel) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        initViews();
        setListeners();


        if(provider.getRatingTitles() != null) {
            ratingAdapter = new ProviderReviewRatingAdapter(provider.getRatingTitles().getLeaveRating());
            recyclerView.setAdapter(ratingAdapter);
        }
    }

    private void setListeners() {
        waitTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(provider.getRatingTitles() != null) {
                    watingTime = provider.getRatingTitles().getTotalWaitTime().get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recommendYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRecommended = "yes";
                recommendYes.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                recommendNo.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });

        recommendNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRecommended = "no";
                recommendNo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                recommendYes.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReviewProvider reviewProvider = new ReviewProvider();

                if(watingTime.isEmpty()){
                    Toast.makeText(ReviewProviderActivity.this,
                            R.string.msg_select_wait_time,Toast.LENGTH_LONG).show();
                    return;
                }
                if(reviewTitle.getText().toString().isEmpty()){
                    reviewTitle.setError(getString(R.string.required_field));
                    return;
                }
                if(reviewDescription.getText().toString().isEmpty()){
                    reviewDescription.setError(getString(R.string.required_field));
                    return;
                }

                showProgressDialog(getString(R.string.dlg_msg_submit_review));

                reviewProvider.setReviewTitle(reviewTitle.getText().toString());
                reviewProvider.setReviewDescription(reviewDescription.getText().toString());
                reviewProvider.setReviewWaitTime(watingTime);

                reviewProvider.setRatingData (ratingAdapter.ratingMap);

                reviewProvider.setRecommended(isRecommended);
                reviewProvider.setProviderId(provider.getID());
                reviewProvider.setUserId(DatabaseUtil.getInstance().getUser().getData().getID());


                RetrofitUtil.createProviderAPI().saveProviderRating(reviewProvider)
                        .enqueue(sendRequest(ReviewProviderActivity.this));
            }
        });
    }

    private void initViews() {

        waitTime = findViewById(R.id.review_wait_time);


        if(  provider.getRatingTitles() != null) {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (ReviewProviderActivity.this, android.R.layout.simple_spinner_item,
                            provider.getRatingTitles().getTotalWaitTime());
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                    .simple_spinner_dropdown_item);
            waitTime.setAdapter(spinnerArrayAdapter);
        }
        reviewTitle = findViewById(R.id.review_title);
        reviewDescription = findViewById(R.id.review_description);

        recommendYes = findViewById(R.id.review_recommend_yes);
        recommendNo = findViewById(R.id.review_recommend_no);

        recyclerView = findViewById(R.id.review_rating_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        submit = findViewById(R.id.review_submit);
    }
}
