package com.tregix.serviceprovider.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.viewpager.widget.ViewPager;

import com.tregix.serviceprovider.Model.Provider.ImageData;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.ImageSlideAdapter;

import java.util.List;

public class ImageSlideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
        setContentView(R.layout.activity_image_slide);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(android.R.color.black)));
        }
        List<ImageData> photos = (List<ImageData>) getIntent().getBundleExtra(Constants.DATA)
                .getSerializable(Constants.DATA);

        int position = getIntent().getBundleExtra(Constants.DATA).getInt(Constants.LOCATION);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageSlideAdapter adapter = new ImageSlideAdapter(this,photos);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

    }
}
