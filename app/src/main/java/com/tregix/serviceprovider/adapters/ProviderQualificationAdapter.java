package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.Qualification;
import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class ProviderQualificationAdapter extends RecyclerView.Adapter<ProviderQualificationAdapter.ViewHolder> {
    private final List<Qualification> mValues;
    private final OnListInteractionListener mListener;

    public ProviderQualificationAdapter(List<Qualification> items,
                                        OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ProviderQualificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_provider_services, parent, false);
        return new ProviderQualificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderQualificationAdapter.ViewHolder holder, int position) {
        // holder.mItem = mValues.get(position);
        holder.rate.setText(Html.fromHtml(mValues.get(position).getInstitute()));
        holder.title.setText(Html.fromHtml(mValues.get(position).getTitle()));
        holder.description.setText(Html.fromHtml(mValues.get(position).getDescription()));

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

        public Qualification mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            rate = (TextView) view.findViewById(R.id.service_rate);
            title = (TextView) view.findViewById(R.id.service_title);
            description = view.findViewById(R.id.service_detail);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
