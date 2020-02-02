package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;

/*
 * Created by Gohar Ali on 12/28/2017.
 */

public class CommonProviderInfoActivity extends BaseActivity {


    private RecyclerView recyclerView;
    private TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_provider_list);

        if( getSupportActionBar() != null && getIntent().getBundleExtra(Constants.DATA) != null
                &&getIntent().getBundleExtra(Constants.DATA).getString(Constants.TITLE) != null) {
            getSupportActionBar().setTitle(Html.fromHtml(getIntent().getBundleExtra(Constants.DATA).getString(Constants.TITLE)));
        }

        initViews();
        setAdapter();

        AdView mAdView = findViewById(R.id.adView);
        if(getString(R.string.ad_enabled).equals("true")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }else{
            mAdView.setVisibility(View.GONE);
        }

    }

    private void initViews() {
        recyclerView = (ShimmerRecyclerView) findViewById(R.id.list);
        noData = findViewById(R.id.list_no_data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

    }

    protected void setAdapter() {
    }

    protected void showNoData(){
        recyclerView.setAdapter(null);
        noData.setVisibility(View.VISIBLE);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onError(Constants.Errors type,String error) {
        super.onError(type,error);
        showNoData();
    }
}
