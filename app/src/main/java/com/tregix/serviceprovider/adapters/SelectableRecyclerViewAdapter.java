package com.tregix.serviceprovider.adapters;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.SelectableItem;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.ViewHolders.SelectableViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public class SelectableRecyclerViewAdapter extends RecyclerView.Adapter implements SelectableViewHolder.OnItemSelectedListener {


    private final List<SelectableItem> mValues;
    private boolean isMultiSelectionEnabled = false;
    SelectableViewHolder.OnItemSelectedListener listener;


    public SelectableRecyclerViewAdapter(SelectableViewHolder.OnItemSelectedListener listener,
                                         List<String> items, boolean isMultiSelectionEnabled, String seletecItem) {
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;

        mValues = new ArrayList<>();
        for (String item : items) {
            if(item.equals(seletecItem)) {
                mValues.add(new SelectableItem(item, true));
            }else{
                mValues.add(new SelectableItem(item, false));
            }
        }
    }

    @Override
    public SelectableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checked_item, parent, false);

        return new SelectableViewHolder(itemView, this);
    }

    public void clearSelections(){
        for (SelectableItem item : mValues) {
            item.setSelected(false);
        }

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        SelectableItem selectableItem = mValues.get(position);
        String name = selectableItem.getName();
        holder.textView.setText(name);
        if (isMultiSelectionEnabled) {
            TypedValue value = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
            int checkMarkDrawableResId = value.resourceId;
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        } else {
            TypedValue value = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
            int checkMarkDrawableResId = value.resourceId;
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        }

        holder.mItem = selectableItem;
        holder.setChecked(holder.mItem.isSelected());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public ArrayList<String> getSelectedItems() {

        ArrayList<String> selectedItems = new ArrayList<>();
        for (SelectableItem item : mValues) {
            if (item.isSelected()) {
                selectedItems.add(item.getName());
            }
        }
        return selectedItems;
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
    public void onItemSelected(SelectableItem item) {
        if (!isMultiSelectionEnabled) {

            for (SelectableItem selectableItem : mValues) {
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
