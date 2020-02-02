package com.tregix.serviceprovider.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.ImageUpload.ImageUploadResponse;
import com.tregix.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileImageRecyclerViewAdapter extends RecyclerView.Adapter<ProfileImageRecyclerViewAdapter.ViewHolder> {
    private List<ImageUploadResponse> uriList;

    public ProfileImageRecyclerViewAdapter(List<ImageUploadResponse> list) {
        this.uriList = list;
    }

    @NonNull
    @Override
    public ProfileImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item, parent, false);
        return new ProfileImageRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileImageRecyclerViewAdapter.ViewHolder holder, final int position) {
        Picasso.get().load(uriList.get(position).getMediaDetails().getSizes().getThumbnail().getSourceUrl()).into(holder.mThumb);
        String imageName = uriList.get(position).getTitle().getRendered();
        holder.mCategoryView.setText(imageName);

        if(uriList.get(position).isSelected()){
            holder.mCategoryView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
        }else{
            holder.mCategoryView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSelection(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public void addNewItem(ImageUploadResponse model){
            if(uriList == null){
                uriList = new ArrayList<>();
            }

            for(ImageUploadResponse item: uriList){
                item.setSelected(false);
            }

            uriList.add(model);
    }

    private void updateSelection(int position){
        for(ImageUploadResponse item: uriList){
            item.setSelected(false);
        }

        uriList.get(position).setSelected(true);

        notifyDataSetChanged();
    }

    public ImageUploadResponse getSelectedItem() {
        for(ImageUploadResponse item: uriList){
            if(item.isSelected()){
                return item;
            }
        }

        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mCategoryView;
        public final ImageView mThumb;

        public ViewHolder(View view) {
            super(view);
            mCategoryView = (TextView) view.findViewById(R.id.profile_title);
            mThumb = view.findViewById(R.id.profile_icon);

        }
    }
}
