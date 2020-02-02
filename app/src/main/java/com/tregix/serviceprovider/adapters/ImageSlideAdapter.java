package com.tregix.serviceprovider.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.Provider.ImageData;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.TouchImageView;

import java.util.List;

public class ImageSlideAdapter extends PagerAdapter {

    Context context;
    private List<ImageData> data;

    public ImageSlideAdapter(Context context, List<ImageData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView imageView = new TouchImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.text_margin);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.get()
                .load(data.get(position).getFull())
                .placeholder(R.drawable.placeholder)
                .into(imageView);

        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
