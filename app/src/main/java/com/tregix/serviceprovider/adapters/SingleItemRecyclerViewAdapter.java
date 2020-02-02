package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.R;

import java.util.List;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public class SingleItemRecyclerViewAdapter extends RecyclerView.Adapter  {


    private final List<String> mValues;


    public SingleItemRecyclerViewAdapter(List<String> items) {
        mValues = items;
    }

    @Override
    public SingleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acitivty_single_item, parent, false);

        return new SingleItemViewHolder(itemView);
    }
    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SingleItemViewHolder holder = (SingleItemViewHolder) viewHolder;
        holder.textView.setText(capitalize(Html.fromHtml(mValues.get(position)).toString()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class SingleItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;


        public SingleItemViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_item);
        }
    }
}
