package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.Experience;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.AppUtils;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class ProviderExperienceAdapter extends RecyclerView.Adapter<ProviderExperienceAdapter.ViewHolder> {
    private final List<Experience> mValues;
    private final OnListInteractionListener mListener;

    public ProviderExperienceAdapter(List<Experience> items,
                                     OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ProviderExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_experience_item, parent, false);
        return new ProviderExperienceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderExperienceAdapter.ViewHolder holder, int position) {
        // holder.mItem = mValues.get(position);
        holder.rate.setText(Html.fromHtml(mValues.get(position).getCompany()));
        holder.title.setText(Html.fromHtml(mValues.get(position).getTitle()));
        holder.description.setText(Html.fromHtml(mValues.get(position).getDescription()));
        holder.date.setText(AppUtils.longToDate(mValues.get(position).getStartDate()+"","MMM yyyy",1000) + " - " +
                AppUtils.longToDate(mValues.get(position).getEndDate()+"","MMM yyyy",1000));

    }

    @Override
    public int getItemCount() {
        return  mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView rate;
        public final TextView title;
        public final TextView description;
        public final TextView date;
        public Experience mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            rate = (TextView) view.findViewById(R.id.service_rate);
            title = (TextView) view.findViewById(R.id.service_title);
            description = view.findViewById(R.id.service_detail);
            date = view.findViewById(R.id.experience_date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
