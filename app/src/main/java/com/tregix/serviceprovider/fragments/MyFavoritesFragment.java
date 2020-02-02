package com.tregix.serviceprovider.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.tregix.serviceprovider.Model.Provider.MarkFavoriteParam;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.activities.NavigationDrawerActivity;
import com.tregix.serviceprovider.adapters.ProviderListRecyclerViewAdapter;

import java.util.List;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getProviders;

/**
 * Created by Gohar Ali on 2/21/2018.
 */

public class MyFavoritesFragment extends BaseFragment {

    private ShimmerRecyclerView recyclerView;
    private ProviderListRecyclerViewAdapter manageServicesAdapter;
    private int uid;
    private TextView noData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_provider_list, container, false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle("My Favorites");

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noData = view.findViewById(R.id.list_no_data);


        if(DatabaseUtil.getInstance().getUser() != null) {
            uid = DatabaseUtil.getInstance().getUser().getData().getID();

            RetrofitUtil.createProviderAPI().getUserFavorites(uid)
                    .enqueue(getProviders(this));
        }

        return view;
    }

    @Override
    public void onProviderLoad(List<ProviderModel> items) {
        if(items != null && items.size() > 0) {
            manageServicesAdapter = new ProviderListRecyclerViewAdapter(items, this);
            recyclerView.setAdapter(manageServicesAdapter);
        }else{
            recyclerView.setAdapter(null);
            noData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void notifyChange() {
        if( recyclerView.getAdapter() != null){
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onUpdateFavorites(ProviderModel item) {
        item.setIsfav(!item.isfav());
        if( recyclerView.getAdapter() != null){
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        hideProgressDialog();
    }

    @Override
    public void onProviderFavorite(ProviderModel item) {
        super.onProviderFavorite(item);
        showProgressDialog(getString(R.string.msg_updating_fvrt));
        RetrofitUtil.createProviderAPI().updateUserFavorites(
                new MarkFavoriteParam(item.getID(),
                        DatabaseUtil.getInstance().getUser().getData().getID(),
                        !item.isfav())).enqueue(RetrofitUtil.updateUserFavorites(item,this));
    }
}
