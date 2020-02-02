package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.R;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class FeaturedProviderListRecyclerViewAdapter extends RecyclerView.Adapter<FeaturedProviderListRecyclerViewAdapter.ViewHolder> {

    private final List<ProviderModel> mValues;
    private final OnListInteractionListener mListener;

    public FeaturedProviderListRecyclerViewAdapter(List<ProviderModel> items, OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featured_provider_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCategoryView.setText(Html.fromHtml(mValues.get(position).getCategory()));
        holder.mCompanyView.setText(Html.fromHtml(mValues.get(position).getUsername()));

        Picasso.get().load(mValues.get(position).getAvatar()).placeholder(R.drawable.placeholder).into(holder.mThumb);
        if(mValues.get(position).getReviewData()!=null ) {
            if(mValues.get(position).getReviewData().getSpAverageRating() != null) {
                holder.ratingBar.setRating(mValues.get(position).getReviewData().getSpAverageRating().floatValue());
            }
            if(mValues.get(position).getReviewData().getSpTotalPercentage() != 0 &&
                    mValues.get(position).getReviewData().getSpTotalRating() != null ) {

                String text = mValues.get(position).getReviewData().getSpTotalPercentage() + "% "
                        + "(" + mValues.get(position).getReviewData().getSpTotalRating() +
                        holder.ratingReview.getContext().getResources().getQuantityString(R.plurals.numberOfVotes,
                                mValues.get(position).getReviewData().getSpTotalRating())+")";
                holder.ratingReview.setText(text);
            }
        }

        if(holder.mItem.isfav()){
            holder.favorite.setBackground(holder.favorite.getContext().getResources().getDrawable(R.drawable.ic_fav_filled));
        }else{
            holder.favorite.setBackground(holder.favorite.getContext().getResources().getDrawable(R.drawable.ic_heart));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onProviderListInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public void onViewRecycled(FeaturedProviderListRecyclerViewAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mCategoryView.setText("");
        holder.mCompanyView.setText("");
        holder.ratingBar.setRating(0);
        holder.ratingReview.setText(R.string.no_vote);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCategoryView;
        public final TextView mCompanyView;
        public final ImageView mThumb;
        public final MaterialRatingBar ratingBar;
        public final TextView ratingReview;
        public ProviderModel mItem;
        public ImageView favorite;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCategoryView = (TextView) view.findViewById(R.id.provider_category);
            mCompanyView = (TextView) view.findViewById(R.id.provider_company);
            mThumb = view.findViewById(R.id.provider_thumbail);
            ratingBar = view.findViewById(R.id.provider_rating);
            ratingReview = view.findViewById(R.id.provider_rating_votes);
            favorite = view.findViewById(R.id.provider_fvrt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCompanyView.getText() + "'";
        }
    }
}
