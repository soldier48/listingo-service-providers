package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.Award;
import com.tregix.serviceprovider.Model.Provider.Qualification;
import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class ProviderAwardsAdapter extends RecyclerView.Adapter<ProviderAwardsAdapter.ViewHolder> {
    private final List<Award> mValues;
    private final OnListInteractionListener mListener;

    public ProviderAwardsAdapter(List<Award> items,
                                 OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ProviderAwardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_provider_services, parent, false);
        return new ProviderAwardsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderAwardsAdapter.ViewHolder holder, int position) {
        // holder.mItem = mValues.get(position);
        holder.rate.setText(mValues.get(position).getDate());
        holder.title.setText(Html.fromHtml(mValues.get(position).getTitle()));
        holder.description.setText(Html.fromHtml(mValues.get(position).getDescription()));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //  mListener.onCategoryListInteraction(holder.mItem);
                }
            }
        });

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
