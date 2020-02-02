package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.tregix.serviceprovider.Model.AppointmentSlot;
import com.tregix.serviceprovider.Model.BookingRequest;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.RequestSlots;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.ViewHolders.SlotsViewHolder;
import com.tregix.serviceprovider.adapters.MakeAppointmentAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.makeAppointement;

public class BookAppointmentActivity extends BaseActivity implements SlotsViewHolder.OnItemSelectedListener {

    private ShimmerRecyclerView recyclerView;
    private CalendarView simpleCalendarView;
    private long selectedDate;
    private TextView date ;
    private Button submit;
    private String dayOfWeek;
    private AppointmentSlot timeSlot;
    private String providerId;
    private ProviderModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        getSupportActionBar().setTitle(R.string.make_appointment);
        initViews();

        selectedDate = simpleCalendarView.getDate();
        dayOfWeek = AppUtils.longToDate(selectedDate+"","dd-MM-yyyy",1);

        user = (ProviderModel) getIntent().getSerializableExtra(Constants.DATA);
        providerId = user.getID().toString();

        date.setText(DateFormat.format("MMM d, yyyy", selectedDate));


        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                     int monthNum = month + 1;
                     dayOfWeek = dayOfMonth+"-"+monthNum+"-"+year;
                     recyclerView.showShimmerAdapter();
                     fetchSlot(dayOfWeek);
                     date.setText(AppUtils.getParsedData(dayOfWeek));
                     timeSlot= null;
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookingRequest request = new BookingRequest();
                User user = DatabaseUtil.getInstance().getUser();
                if(user != null) {
                    request.setUserId(user.getData().getID().toString());
                }
                request.setAuthorId(providerId);

                request.setDateSlot(dayOfWeek);
                if(timeSlot != null ) {
                    request.setTimeSlot(timeSlot.getKey());
                }else{
                    AppUtils.showDialog(BookAppointmentActivity.this,getString(R.string.select_time_slot), null);
                    return;
                }
                showProgressDialog(getString(R.string.msg_book_appointment));
                RetrofitUtil.createProviderAPI().makeAppointment(request).enqueue(makeAppointement(BookAppointmentActivity.this));
            }
        });

        fetchSlot(dayOfWeek);

    }

    private void fetchSlot(String selectedDate) {
        RequestSlots slot = new RequestSlots(selectedDate,providerId);
        RetrofitUtil.createProviderAPI().getSlotsOfDate(slot).enqueue(loadSlots());
    }

    private void initViews() {
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        date = findViewById(R.id.appointment_Date);
        submit = findViewById(R.id.book_appointment);

        recyclerView = (ShimmerRecyclerView) findViewById(R.id.appointment_slots);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public void onItemSelected(AppointmentSlot item) {
        timeSlot = item;
    }

    @Override
    public void onSuccess(String msg) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATA,user);
        hideProgressDialog();
        openAcitivty(bundle,BookAppointmentDetailActivity.class);
        finish();
    }

    public Callback<List<AppointmentSlot>> loadSlots() {

        return new Callback<List<AppointmentSlot>>() {
            @Override
            public void onResponse(Call<List<AppointmentSlot>> call, Response<List<AppointmentSlot>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        recyclerView.hideShimmerAdapter();
                        recyclerView.setAdapter(new MakeAppointmentAdapter
                                (BookAppointmentActivity.this,response.body(),false));
                    }
                } else {
                    onError(Constants.Errors.APPOINTMENT_FAILED,getString(R.string.err_something_wrong));
                }
            }

            @Override
            public void onFailure(Call<List<AppointmentSlot>> call, Throwable t) {
                t.printStackTrace();
                onError(Constants.Errors.APPOINTMENT_FAILED,t.getLocalizedMessage());
            }
        };

    }
}
