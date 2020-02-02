package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.RatingData;
import com.tregix.serviceprovider.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class ProviderReviewRatingAdapter extends RecyclerView.Adapter<ProviderReviewRatingAdapter.ViewHolder> {

    public final List<RatingData> mValues;
    public Map<String,Integer> ratingMap;

    public ProviderReviewRatingAdapter(List<RatingData> items) {
        mValues = items;
        ratingMap = new HashMap<>();

        for(RatingData data : items){
            ratingMap.put(data.getSlug(),1);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provider_review_rating_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(Html.fromHtml(mValues.get(position).getTitle()));
        holder.ratingBar.setRating(mValues.get(position).getRating());
    }

    @Override
    public void onViewRecycled(ProviderReviewRatingAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.ratingBar.setRating(1);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final MaterialRatingBar ratingBar;
        public RatingData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ratingBar = view.findViewById(R.id.review_rating);
            title = view.findViewById(R.id.review_title);

            ratingBar .setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    mItem.setRating(ratingBar.getRating());
                    ratingMap.put(mItem.getSlug(),(int)ratingBar.getRating());
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
