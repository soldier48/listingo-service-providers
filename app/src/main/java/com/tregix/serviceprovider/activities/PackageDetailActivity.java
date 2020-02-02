package com.tregix.serviceprovider.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.tregix.serviceprovider.Model.packages.PackageItem;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.PackageDetailsAdapter;

public class PackageDetailActivity extends BaseActivity {

    ShimmerRecyclerView recyclerView;
    PackageItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);

        item = (PackageItem) getIntent().getSerializableExtra(Constants.DATA);

        recyclerView = findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PackageDetailsAdapter(item.getMetaData()));

    }
}
