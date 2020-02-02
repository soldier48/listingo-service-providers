package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class JobsListingAdapter extends RecyclerView.Adapter<JobsListingAdapter.ViewHolder> {
    private final List<JobItem> mValues;
    private final OnListInteractionListener mListener;

    public JobsListingAdapter(List<JobItem> items,
                              OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public JobsListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_job_list_item, parent, false);
        return new JobsListingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final JobsListingAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.salary.setText(Html.fromHtml(mValues.get(position).getSalary()));
        holder.title.setText(Html.fromHtml(mValues.get(position).getTitle()));
        holder.category.setText(Html.fromHtml(mValues.get(position).getCareerLevel()));
        holder.date.setText(mValues.get(position).getExpiryDate());
        holder.type.setText(mValues.get(position).getJobType());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onJobItemSelection(holder.mItem);
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
        public final TextView salary;
        public final TextView title;
        public final TextView category;
        public final TextView date;
        public final TextView type;
        public JobItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            salary = (TextView) view.findViewById(R.id.job_salary);
            title = (TextView) view.findViewById(R.id.job_title);
            category = view.findViewById(R.id.job_category);
            date = view.findViewById(R.id.job_date);
            type = view.findViewById(R.id.job_type);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
