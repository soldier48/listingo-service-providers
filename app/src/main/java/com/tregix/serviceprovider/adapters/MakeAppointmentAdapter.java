package com.tregix.serviceprovider.adapters;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.AppointmentSlot;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.ViewHolders.SelectableViewHolder;
import com.tregix.serviceprovider.ViewHolders.SlotsViewHolder;

import java.util.List;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public class MakeAppointmentAdapter extends RecyclerView.Adapter implements SlotsViewHolder.OnItemSelectedListener {

    private final List<AppointmentSlot> mValues;
    private boolean isMultiSelectionEnabled = false;
    SlotsViewHolder.OnItemSelectedListener listener;

    public MakeAppointmentAdapter(SlotsViewHolder.OnItemSelectedListener listener,
                                  List<AppointmentSlot> slots, boolean isMultiSelectionEnabled) {
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;
        mValues = slots;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_slot_item, parent, false);

        return new SlotsViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        SlotsViewHolder holder = (SlotsViewHolder) viewHolder;
        AppointmentSlot selectableItem = mValues.get(position);
        String name = selectableItem.getTimeSlot();
        holder.slotTime.setText(name);
        holder.slotAvailable.setText(holder.slotAvailable.getContext().getString(R.string.txt_space_left) + selectableItem.getRemaining());
        if (isMultiSelectionEnabled) {
            TypedValue value = new TypedValue();
            holder.view.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
        } else {
            TypedValue value = new TypedValue();
            holder.view.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
        }

        holder.mItem = selectableItem;
        holder.setChecked(holder.mItem.isSelected());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isMultiSelectionEnabled) {
            return SelectableViewHolder.MULTI_SELECTION;
        } else {
            return SelectableViewHolder.SINGLE_SELECTION;
        }
    }

    @Override
    public void onItemSelected(AppointmentSlot item) {
        if (!isMultiSelectionEnabled) {

            for (AppointmentSlot selectableItem : mValues) {
                if (!selectableItem.equals(item)
                        && selectableItem.isSelected()) {
                    selectableItem.setSelected(false);
                } else if (selectableItem.equals(item)
                        && item.isSelected()) {
                    selectableItem.setSelected(true);
                }
            }
            notifyDataSetChanged();
        }
        listener.onItemSelected(item);
    }

}
