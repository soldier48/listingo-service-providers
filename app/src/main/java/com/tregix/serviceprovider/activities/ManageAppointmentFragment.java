package com.tregix.serviceprovider.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.ManageAppointmentRequest;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.adapters.ManageAppointmentsAdapter;
import com.tregix.serviceprovider.fragments.BaseFragment;
import com.tregix.serviceprovider.swipeController.SwipeController;
import com.tregix.serviceprovider.swipeController.SwipeControllerActions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getUserAppointments;

public class ManageAppointmentFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<Appointment> data;
    private List<Appointment> filteredData;
    private Spinner sort;
    private Button filterDate;
    private ManageAppointmentsAdapter adapter;
    private int pos;
    Calendar myCalendar;
    private TextView clearFilter;
    private TextView noData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_manage_appointment, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_manage_appointments));

        initViews(view);
        setupRecyclerView();
        fetchData();
        setListeners();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.list);
        sort = view.findViewById(R.id.appointment_sort);
        filterDate = view.findViewById(R.id.apt_filter_Date);
        myCalendar = Calendar.getInstance();
        data = new ArrayList<>();
        filteredData = new ArrayList<>();
        clearFilter = view.findViewById(R.id.apt_filter_clear);
        noData = view.findViewById(R.id.list_no_data);
    }

    private void setListeners() {
        sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    sortID();
                    setData(filteredData);
                }
                if (position == 2) {
                    sortTitle();
                    setData(filteredData);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        filterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        clearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filteredData.clear();
                filteredData.addAll(data);
                setData(data);
                clearFilter.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void fetchData() {
        User user = DatabaseUtil.getInstance().getUser();

        showProgressDialog(getString(R.string.fetching_appointments));
        RetrofitUtil.createProviderAPI().getProviderAppointments(user.getData().getID()).
                enqueue(getUserAppointments(this));
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            myCalendar.set(Calendar.MILLISECOND, 0);
            myCalendar.set(Calendar.SECOND, 0);
            myCalendar.set(Calendar.MINUTE, 0);
            myCalendar.set(Calendar.HOUR_OF_DAY, 0);
            filterData();
        }

    };


    private void filterData() {

        long seletectedDate = myCalendar.getTimeInMillis() / 1000;
        long dataRange = seletectedDate + 86400;

        if (filteredData != null) filteredData.clear();

        for (Appointment apt : data) {
            if (apt.getAptDate() != null && !apt.getAptDate().isEmpty() &&
                    (Long.parseLong(apt.getAptDate()) >= seletectedDate &&
                            Long.parseLong(apt.getAptDate()) <= dataRange)) {
                filteredData.add(apt);
            }
        }
        setData(filteredData);
        clearFilter.setVisibility(View.VISIBLE);

    }


    private void sortID() {
        if (data != null) {
            Collections.sort(filteredData, new Comparator<Appointment>() {
                @Override
                public int compare(Appointment lhs, Appointment rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.getPostId() > rhs.getPostId() ? -1 : (lhs.getPostId() < rhs.getPostId()) ? 1 : 0;
                }
            });
        }
    }

    private void sortTitle() {
        if (data != null) {
            Collections.sort(filteredData, new Comparator<Appointment>() {
                @Override
                public int compare(Appointment lhs, Appointment rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.getUsername().compareToIgnoreCase(rhs.getUsername());
                }
            });
        }
    }

    private void setupRecyclerView() {

        final SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.DATA, filteredData.get(position));
                Intent intent = new Intent(getActivity(), RejectAppointmentActivity.class);
                intent.putExtra(Constants.DATA, bundle);
                startActivityForResult(intent, 10);
                pos = position;
            }

            @Override
            public void onLeftClicked(int position) {
                showProgressDialog("Updating...");
                RetrofitUtil.createProviderAPI().updateProviderAppointments
                        (new ManageAppointmentRequest(filteredData.get(position).getPostId(), "publish",
                                DatabaseUtil.getInstance().getUserID()))
                        .enqueue(RetrofitUtil.sendRequest(ManageAppointmentFragment.this));
                pos = position;
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    @Override
    public void onAppointmentsLoad(List<Appointment> items) {
        hideProgressDialog();
        if (items != null) {
            data.clear();
            filteredData.clear();
            data.addAll(items);
            filteredData.addAll(items);
        }
        setData(items);
    }

    @Override
    public void onSuccess(String msg) {
        super.onSuccess(msg);

        removeItem();
    }

    private void removeItem() {
        try {
            if (adapter != null) {
                Appointment item = filteredData.get(pos);
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getPostId() == item.getPostId()) {
                        data.remove(i);
                        break;
                    }
                }

                adapter.removeItem(pos);
                filteredData = adapter.getDataList();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            removeItem();
        }
    }

    private void setData(List<Appointment> items) {
        if (items != null && items.size() > 0) {
            adapter = new ManageAppointmentsAdapter(items, this);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setAdapter(null);
            noData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAppointmentInteraction(Appointment item, int pos) {
        this.pos = pos;
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATA, item);
        Intent intent = new Intent(getActivity(), AppointmentDetailActivity.class);
        intent.putExtra(Constants.DATA, bundle);
        startActivityForResult(intent, 10);
    }
}
