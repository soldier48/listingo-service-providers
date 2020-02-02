package com.tregix.serviceprovider.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.AppUtils;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 */
public class MyAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<MyAppointmentsRecyclerViewAdapter.ViewHolder> {

    private final List<Appointment> mValues;
    private OnListInteractionListener listener;

    public MyAppointmentsRecyclerViewAdapter(List<Appointment> items, OnListInteractionListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.date.setText(AppUtils.longToDate(mValues.get(position).getAptDate(),"MMM d, yyyy",1000));
        holder.time.setText(mValues.get(position).getAptTime());
        holder.name.setText(mValues.get(position).getProvider());
        holder.description.setText(Html.fromHtml(mValues.get(position).getAptDescription()));

        holder.postId.setText(mValues.get(position).getPostId() +"");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onAppointmentInteraction(holder.mItem,position );
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
        public final TextView postId;
        public final TextView date;
        public final TextView time;
        public final TextView name;
        public final TextView description;
        public Appointment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            date = (TextView) view.findViewById(R.id.date);
            time = (TextView) view.findViewById(R.id.time);
            name = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            postId = (TextView) view.findViewById(R.id.apt_id);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}
