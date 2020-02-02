package com.tregix.serviceprovider.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class ProviderServicesRecyclerViewAdapter extends RecyclerView.Adapter<ProviderServicesRecyclerViewAdapter.ViewHolder> {
    private final List<ProfileServices> mValues;
    private final OnListInteractionListener mListener;

    public ProviderServicesRecyclerViewAdapter(List<ProfileServices> items,
                                               OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ProviderServicesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_provider_services, parent, false);
        return new ProviderServicesRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderServicesRecyclerViewAdapter.ViewHolder holder, int position) {

                holder.rate.setText(mValues.get(position).getPrice() + "/ " + mValues.get(position).getType());
                holder.title.setText(mValues.get(position).getTitle());
                holder.description.setText(mValues.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView rate;
        public final TextView title;
        public final TextView description;
        public ProfileServices mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            rate = (TextView) view.findViewById(R.id.service_rate);
            title = (TextView) view.findViewById(R.id.service_title);
            description =  view.findViewById(R.id.service_detail);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
