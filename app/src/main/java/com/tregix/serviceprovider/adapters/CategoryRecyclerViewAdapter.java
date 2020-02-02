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
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.Model.categories.SubCategory;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;

import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    private final List<Category> mValues;
    private final OnListInteractionListener mListener;

    public CategoryRecyclerViewAdapter(List<Category> items,
                                       OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new CategoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCategoryView.setText(Html.fromHtml(mValues.get(position).getTitle()));


        if(mValues.get(position).getSubCategories() != null &&
                !mValues.get(position).getSubCategories().isEmpty() ) {
            String subTitle = Constants.EMPTY_STRING;
            for(SubCategory subCategory : mValues.get(position).getSubCategories()){
                subTitle = subTitle.concat(subCategory.getTitle()+ ", ");
            }
            if ( !subTitle.isEmpty() && subTitle.charAt(subTitle.length() - 2) == ',') {
                subTitle = subTitle.substring(0, subTitle.length() - 2);
            }
            holder.description.setText(subTitle);

        }

        Picasso.get().load("http:"+mValues.get(position).getCategoryImage().
                getUrl()).placeholder(R.drawable.placeholder).into(holder.mThumb);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onCategoryListInteraction(holder.mItem);
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
        public final TextView mCategoryView;
        public final TextView description;
        public final ImageView mThumb;
        public Category mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCategoryView = (TextView) view.findViewById(R.id.cateogry_title);
            description = (TextView) view.findViewById(R.id.cateogry_desc);
            mThumb =  view.findViewById(R.id.cateogry_icon);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + description.getText() + "'";
        }
    }
}
