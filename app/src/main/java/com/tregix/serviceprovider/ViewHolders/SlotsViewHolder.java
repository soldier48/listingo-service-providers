package com.tregix.serviceprovider.ViewHolders;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.AppointmentSlot;
import com.tregix.serviceprovider.R;

/**
 * Created by Gohar Ali on 2/12/2018.
 */

public class SlotsViewHolder extends RecyclerView.ViewHolder {

    public static final int MULTI_SELECTION = 2;
    public static final int SINGLE_SELECTION = 1;
    public TextView slotTime;
    public TextView slotAvailable;
    public AppointmentSlot mItem;
    public OnItemSelectedListener itemSelectedListener;
    public View view;


    public SlotsViewHolder(View view, OnItemSelectedListener listener) {
        super(view);
        this.view = view;
        itemSelectedListener = listener;
        slotTime =  view.findViewById(R.id.slot_time);
        slotAvailable =  view.findViewById(R.id.slot_available);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItem.isSelected() && getItemViewType() == MULTI_SELECTION) {
                    setChecked(false);
                } else {
                    setChecked(true);
                }
                itemSelectedListener.onItemSelected(mItem);

            }
        });
    }

    public void setChecked(boolean value) {
        if (value) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.colorPrimary));
            slotAvailable.setTextColor(Color.WHITE);
            slotTime.setTextColor(Color.WHITE);
        } else {
            slotAvailable.setTextColor(Color.BLACK);
            slotTime.setTextColor(Color.BLACK);
            view.setBackgroundColor(Color.WHITE);
        }
        mItem.setSelected(value);

    }

    public interface OnItemSelectedListener {

        void onItemSelected(AppointmentSlot item);
    }

}


