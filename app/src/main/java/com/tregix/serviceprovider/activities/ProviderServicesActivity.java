package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ProviderServicesRecyclerViewAdapter;

import java.util.List;

public class ProviderServicesActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<ProfileServices> services = (List<ProfileServices>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderServicesRecyclerViewAdapter
                    (services, null));
        } else {
            showNoData();
        }
    }
}
