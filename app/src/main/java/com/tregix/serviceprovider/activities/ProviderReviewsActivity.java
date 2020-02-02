package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Provider.ProviderReviewListData;
import com.tregix.serviceprovider.Model.ReviewProvider;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ProviderReviewAdapter;

import java.util.List;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getProviderReviews;

public class ProviderReviewsActivity extends CommonProviderInfoActivity {

    @Override
    protected void setAdapter() {
        if(getIntent().getBundleExtra(Constants.DATA) != null){
            ReviewProvider provider = new ReviewProvider();
            provider.setProviderId(getIntent().getBundleExtra(Constants.DATA).getInt(Constants.ID));
            RetrofitUtil.createProviderAPI().getProviderReviews(provider).enqueue(getProviderReviews(this));
        }
    }

    @Override
    public void onReviewsLoad(List<ProviderReviewListData> items) {
        if (items != null && !items.isEmpty()) {
            getRecyclerView().setAdapter(new ProviderReviewAdapter
                    (items));
        } else {
            showNoData();
        }
    }
}
