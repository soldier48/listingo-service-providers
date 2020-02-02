package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ProviderBusinessHoursAdapter;

public class BusinessHoursActivity extends CommonProviderInfoActivity {
    @Override
    protected void setAdapter() {
        BusinessHours services = (BusinessHours)(getIntent().getBundleExtra(Constants.DATA)).getSerializable(Constants.DATA);
        if(services != null) {
            getRecyclerView().setAdapter(new ProviderBusinessHoursAdapter
                    (services, null));
        }else{
            showNoData();
        }
    }
}
