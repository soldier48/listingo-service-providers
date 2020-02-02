package com.tregix.serviceprovider.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.R;

/**
 * Created by Gohar Ali on 2/12/2018.
 */

public class ManageAppointmentViewHolder extends RecyclerView.ViewHolder {

    public TextView name, service, type;
    public ImageView avatar;
    public Appointment mItem;
    public View view;


    public ManageAppointmentViewHolder(View view) {
        super(view);
        this.view = view;
        name =  view.findViewById(R.id.customer_name);
        service =  view.findViewById(R.id.customer_service);
        type = view.findViewById(R.id.customer_service_type);
        avatar = view.findViewById(R.id.provider_thumbail);
    }

}


