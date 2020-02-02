package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Provider.Experience;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ProviderExperienceAdapter;

import java.util.List;

public class ProviderExperienceActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Experience> services = (List<Experience>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderExperienceAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
