package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.Provider.ProviderReviewListData;
import com.tregix.serviceprovider.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class ProviderReviewAdapter extends RecyclerView.Adapter<ProviderReviewAdapter.ViewHolder> {

    public final List<ProviderReviewListData> mValues;

    public ProviderReviewAdapter(List<ProviderReviewListData> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provider_review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(Html.fromHtml(mValues.get(position).getTitle()));
        if(mValues.get(position).getTotalRatings() != null) {
            holder.ratingBar.setRating(Float.parseFloat(mValues.get(position).getTotalRatings()));
        }
        holder.detail.setText(Html.fromHtml(mValues.get(position).getDescription()));
        holder.name.setText(Html.fromHtml(mValues.get(position).getReviewerName()));
        holder.date.setText(mValues.get(position).getReviewDate());

        Picasso.get().load(mValues.get(position).getAvatar()).into(holder.thumbnail);

    }

    @Override
    public void onViewRecycled(ProviderReviewAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title,name,date,detail;
        public final CircleImageView thumbnail;
        public final MaterialRatingBar ratingBar;
        public ProviderReviewListData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ratingBar = view.findViewById(R.id.review_rating);
            title = view.findViewById(R.id.review_title);
            name = view.findViewById(R.id.reviewer_name);
            date = view.findViewById(R.id.review_date);
            detail = view.findViewById(R.id.review_detail);
            thumbnail = view.findViewById(R.id.reviewer_thumbnail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
