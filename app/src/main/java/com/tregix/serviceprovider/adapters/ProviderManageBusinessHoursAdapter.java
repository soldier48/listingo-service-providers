package com.tregix.serviceprovider.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Confiz123 on 11/29/2017.
 */

public class ProviderManageBusinessHoursAdapter extends RecyclerView.Adapter<ProviderManageBusinessHoursAdapter.ViewHolder> {
    private final OnListInteractionListener mListener;
    private List<String> startTime;
    private List<String> endTime;

    public ProviderManageBusinessHoursAdapter(List<String> startTime,List<String> endTime,
                                              OnListInteractionListener listener) {
        this.startTime = startTime;
        this.endTime = endTime;
        mListener = listener;
    }

    @Override
    public ProviderManageBusinessHoursAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manage_business_hour_item, parent, false);
        return new ProviderManageBusinessHoursAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderManageBusinessHoursAdapter.ViewHolder holder, final int position) {

        holder.startTime.setText(startTime.get(position));
        holder.endTime.setText(endTime.get(position));

        holder.startTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                startTime.set(position,s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        holder.endTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                endTime.set(position,s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
            }
        });

    }

    public void removeAt(int position) {
        startTime.remove(position);
        endTime.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, startTime.size());
    }

    public void addNewItem(){
        if (startTime == null)
            startTime = new ArrayList();

        if(endTime == null)
            endTime = new ArrayList<>();

        startTime.add("");
        endTime.add("");

        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return startTime != null?startTime.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText startTime;
        public final EditText endTime;
        ImageView remove;
        public BusinessHours mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            startTime =  view.findViewById(R.id.start_time);
            endTime =  view.findViewById(R.id.end_time);
            remove = view.findViewById(R.id.remove);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + startTime.getText() + "'";
        }
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public List<String> getEndTime() {
        return endTime;
    }

}
