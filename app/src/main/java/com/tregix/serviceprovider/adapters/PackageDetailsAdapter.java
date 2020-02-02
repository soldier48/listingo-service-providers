package com.tregix.serviceprovider.adapters;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.packages.MetaDatum;
import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class PackageDetailsAdapter extends RecyclerView.Adapter<PackageDetailsAdapter.ViewHolder> {
    private final List<MetaDatum> mValues;

    public PackageDetailsAdapter(List<MetaDatum> items) {
        mValues = items;
    }

    @Override
    public PackageDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_hours_item, parent, false);
        return new PackageDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PackageDetailsAdapter.ViewHolder holder, int position) {
            holder.time.setText(mValues.get(position).getValue().toString());

            String val = mValues.get(position).getKey().replace("sp_","").replace("_"," ");

            String upperString = val.substring(0,1).toUpperCase() + val.substring(1);

            holder.day.setText(upperString);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView day;
        public final TextView time;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            day = (TextView) view.findViewById(R.id.business_hour_day);
            time = (TextView) view.findViewById(R.id.business_hour_time);

            day.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + day.getText() + "'";
        }
    }
}
