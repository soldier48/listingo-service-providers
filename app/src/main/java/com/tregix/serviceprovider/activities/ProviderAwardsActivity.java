package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Provider.Award;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ProviderAwardsAdapter;

import java.util.List;

public class ProviderAwardsActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        List<Award> services = (List<Award>) (getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if (services != null && !services.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderAwardsAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
