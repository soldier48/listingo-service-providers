package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Provider.Qualification;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ProviderQualificationAdapter;

import java.util.List;

public class ProviderQualificationActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Qualification> services = (List<Qualification>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderQualificationAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
