package com.tregix.serviceprovider.fragments;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.packages.PackageItem;
import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PackageItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class PackagesRecyclerViewAdapter extends RecyclerView.Adapter<PackagesRecyclerViewAdapter.ViewHolder> {

    private final List<PackageItem> mValues;
    private final OnListInteractionListener mListener;

    public PackagesRecyclerViewAdapter(List<PackageItem> items, OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_packageslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position).getName());
        holder.price.setText(Html.fromHtml(mValues.get(position).getPriceHtml()));

        if(mValues.get(position).getMetaData() != null && mValues.get(position).getMetaData().size() >=2) {
            holder.limit.setText(" for " + Html.fromHtml(mValues.get(position).getMetaData().get(1).getValue().toString()) + " days");
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onPackageSelection(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView price;
        public final TextView limit;

        public PackageItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.tv_package_title);
            price = (TextView) view.findViewById(R.id.tv_package_price);
            limit = (TextView) view.findViewById(R.id.tv_package_limit);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + price.getText() + "'";
        }
    }
}
