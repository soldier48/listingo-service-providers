package com.tregix.serviceprovider.activities;

import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.adapters.MyAppointmentsRecyclerViewAdapter;

import java.util.List;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getUserAppointments;


public class MyAppointmentsActivity extends CommonProviderInfoActivity {

    @Override
    public void onAppointmentsLoad(List<Appointment> items) {
        if(items != null && items.size() >0) {
            getRecyclerView().setAdapter(new MyAppointmentsRecyclerViewAdapter(items,this));
        }else{
            showNoData();
        }
    }

    @Override
    protected void setAdapter() {
        User user = DatabaseUtil.getInstance().getUser();
        RetrofitUtil.createProviderAPI().getUserAppointments(user.getData().getID()).
                enqueue(getUserAppointments(MyAppointmentsActivity.this));
    }

    @Override
    public void onAppointmentInteraction(Appointment item, int pos) {
        AppUtils.showDialog(this,item.toString(),null);
    }
}
