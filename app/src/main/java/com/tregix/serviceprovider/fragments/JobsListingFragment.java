package com.tregix.serviceprovider.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.activities.JobDetailActivity;
import com.tregix.serviceprovider.activities.NavigationDrawerActivity;
import com.tregix.serviceprovider.adapters.JobsListingAdapter;

import java.util.List;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getAllJobs;

/**
 * Created by Gohar Ali on 2/21/2018.
 */

public class JobsListingFragment extends BaseFragment {

    private ShimmerRecyclerView recyclerView;
    private JobsListingAdapter jobsListingAdapter;
    private TextView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_provider_list, container, false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle("Jobs");

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noData = view.findViewById(R.id.list_no_data);


        RetrofitUtil.createProviderAPI().getAllJobs()
                    .enqueue(getAllJobs(this));

        return view;
    }

    @Override
    public void onJobsLoaded(List<JobItem> items) {
        if(items != null && items.size() > 0) {
            jobsListingAdapter = new JobsListingAdapter(items, this);
            recyclerView.setAdapter(jobsListingAdapter);
        }else{
            recyclerView.setAdapter(null);
            noData.setVisibility(View.VISIBLE);
        }
    }

    public void onJobItemSelection(JobItem item){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATA,item);

        openAcitivty(bundle, JobDetailActivity.class);
    }

}
