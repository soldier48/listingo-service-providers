package com.tregix.serviceprovider.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.Provider.ImageData;
import com.tregix.serviceprovider.Model.Provider.ProfileGalleryPhotos;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;

import java.io.Serializable;
import java.util.List;


public class GalleryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ProfileGalleryPhotos photos = (ProfileGalleryPhotos) getIntent().getBundleExtra(Constants.DATA)
                .getSerializable(Constants.DATA);

        if(photos != null && photos.getImageData() != null && !photos.getImageData().isEmpty()) {
            GalleryActivity.ImageGalleryAdapter adapter = new GalleryActivity.ImageGalleryAdapter(this, photos.getImageData());
            recyclerView.setAdapter(adapter);
        }else{
            findViewById(R.id.gallery_empty).setVisibility(View.VISIBLE);
        }

    }

    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the layout
            View photoView = inflater.inflate(R.layout.item_photo, parent, false);

            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            ImageData spacePhoto = mImageDatas.get(position);
            ImageView imageView = holder.mPhotoImageView;

            Picasso.get()
                    .load(spacePhoto.getThumb())
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (mImageDatas.size());
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView mPhotoImageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.DATA, (Serializable) mImageDatas);
                    bundle.putInt(Constants.LOCATION,position);

                    openAcitivty(bundle,ImageSlideActivity.class);
                }
            }
        }

        private List<ImageData> mImageDatas;
        private Context mContext;

        public ImageGalleryAdapter(Context context, List<ImageData> spacePhotos) {
            mContext = context;
            mImageDatas = spacePhotos;
        }
    }
}
