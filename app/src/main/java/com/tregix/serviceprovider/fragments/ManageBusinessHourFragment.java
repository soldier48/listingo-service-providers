package com.tregix.serviceprovider.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Model.ManagePrivacyRequest;
import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.Model.Provider.Friday;
import com.tregix.serviceprovider.Model.Provider.Monday;
import com.tregix.serviceprovider.Model.Provider.Saturday;
import com.tregix.serviceprovider.Model.Provider.Sunday;
import com.tregix.serviceprovider.Model.Provider.Thursday;
import com.tregix.serviceprovider.Model.Provider.Tuesday;
import com.tregix.serviceprovider.Model.Provider.Wednesday;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.adapters.ProviderManageBusinessHoursAdapter;

import java.util.ArrayList;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getBusinessHours;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.sendRequest;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ManageBusinessHourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageBusinessHourFragment extends BaseFragment {

    private Switch monday;
    private Switch tuesday;
    private Switch wednesday;
    private Switch thursday;
    private Switch friday;
    private Switch saturday;
    private Switch sunday;

    private View viewMonday;
    private View viewTuesday;
    private View viewWednesday;
    private View viewThursday;
    private View viewFriday;
    private View viewSaturday;
    private View viewSunday;

    private TextView textViewMonday;
    private TextView textViewTuesday;
    private TextView textViewWednesday;
    private TextView textViewThursday;
    private TextView textViewFriday;
    private TextView textViewSaturday;
    private TextView textViewSunday;

    private TextView expandMonday;
    private TextView expandTuesday;
    private TextView expandWednesday;
    private TextView expandThursday;
    private TextView expandFriday;
    private TextView expandSaturday;
    private TextView expandSunday;

    private RecyclerView recyclerViewMonday;
    private RecyclerView recyclerViewTuesday;
    private RecyclerView recyclerViewWednesday;
    private RecyclerView recyclerViewThursday;
    private RecyclerView recyclerViewFriday;
    private RecyclerView recyclerViewSaturday;
    private RecyclerView recyclerViewSunday;

    private ProviderManageBusinessHoursAdapter adapterMonday;
    private ProviderManageBusinessHoursAdapter adapterTuesday;
    private ProviderManageBusinessHoursAdapter adapterWednesday;
    private ProviderManageBusinessHoursAdapter adapterThursday;
    private ProviderManageBusinessHoursAdapter adapterFriday;
    private ProviderManageBusinessHoursAdapter adapterSaturday;
    private ProviderManageBusinessHoursAdapter adapterSunday;

    BusinessHours item;

    public ManageBusinessHourFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ManagePrivacyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageBusinessHourFragment newInstance() {
        ManageBusinessHourFragment fragment = new ManageBusinessHourFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_business_hour, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.tab_business_hours));

        initViews(view);
        addNewItemListener();
        setHideShowListener();

        showProgressDialog(getString(R.string.loading_settings));

        RetrofitUtil.createProviderAPI().getBusinessHour
                (DatabaseUtil.getInstance().getUserID()).enqueue(getBusinessHours(this));

        view.findViewById(R.id.business_hour_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusinessHours item = new BusinessHours();
                item.setMonday(new Monday());
                item.setTuesday(new Tuesday());
                item.setWednesday(new Wednesday());
                item.setThursday(new Thursday());
                item.setFriday(new Friday());
                item.setSunday(new Sunday());
                item.setSaturday(new Saturday());

                if (monday.isChecked()) {
                    if(adapterMonday != null) {
                        item.getMonday().setStarttime(adapterMonday.getStartTime());
                        item.getMonday().setEndtime(adapterMonday.getEndTime());
                    }
                }else{
                    item.getMonday().setOffDay("on");
                }
                if (tuesday.isChecked()) {
                    if(adapterTuesday != null) {
                        item.getTuesday().setStarttime(adapterTuesday.getStartTime());
                        item.getTuesday().setEndtime(adapterTuesday.getEndTime());
                    }
                }else{
                    item.getTuesday().setOffDay("on");
                }
                if (wednesday.isChecked()) {
                    if(adapterWednesday != null) {
                        item.getWednesday().setStarttime(adapterWednesday.getStartTime());
                        item.getWednesday().setEndtime(adapterWednesday.getEndTime());
                    }
                }else{
                    item.getWednesday().setOffDay("on");
                }
                if (thursday.isChecked()) {
                    if(adapterThursday != null) {
                        item.getThursday().setStarttime(adapterThursday.getStartTime());
                        item.getThursday().setEndtime(adapterThursday.getEndTime());
                    }
                }else{
                    item.getThursday().setOffDay("on");
                }
                if (friday.isChecked()) {
                    if(adapterFriday != null) {
                        item.getFriday().setStarttime(adapterFriday.getStartTime());
                        item.getFriday().setEndtime(adapterFriday.getEndTime());
                    }
                }else{
                    item.getFriday().setOffDay("on");
                }
                if (saturday.isChecked()) {
                    if(adapterSaturday != null) {
                        item.getSaturday().setStarttime(adapterSaturday.getStartTime());
                        item.getSaturday().setEndtime(adapterSaturday.getEndTime());
                    }
                }else{
                    item.getSaturday().setOffDay("on");
                }
                if(sunday.isChecked()){
                    if(adapterSunday != null) {
                        item.getSunday().setStarttime(adapterSunday.getStartTime());
                        item.getSunday().setEndtime(adapterSunday.getEndTime());
                    }
                }else{
                    item.getSunday().setOffDay("on");
                }

                ManagePrivacyRequest req = new ManagePrivacyRequest
                        (DatabaseUtil.getInstance().getUserID(), item);

                showProgressDialog("Updating Business Hours");
                RetrofitUtil.createProviderAPI().updateBusinessHour(req).
                        enqueue(sendRequest(ManageBusinessHourFragment.this));
            }
        });

        return view;
    }

    private void initViews(View view) {
        monday = view.findViewById(R.id.business_hour_monday);
        tuesday = view.findViewById(R.id.business_hour_tuesday);
        wednesday = view.findViewById(R.id.business_hour_wednesday);
        thursday = view.findViewById(R.id.business_hour_thursday);
        friday = view.findViewById(R.id.business_hour_friday);
        saturday = view.findViewById(R.id.business_hour_sat);
        sunday = view.findViewById(R.id.business_hour_sunday);

        viewMonday = view.findViewById(R.id.hours_monday);
        viewTuesday = view.findViewById(R.id.hours_tuesday);
        viewWednesday = view.findViewById(R.id.hours_wednesday);
        viewThursday = view.findViewById(R.id.hours_thursday);
        viewFriday = view.findViewById(R.id.hours_friday);
        viewSaturday = view.findViewById(R.id.hours_saturday);
        viewSunday = view.findViewById(R.id.hours_sunday);

        expandMonday = view.findViewById(R.id.expand_monday);
        expandTuesday = view.findViewById(R.id.expand_tuesday);
        expandWednesday = view.findViewById(R.id.expand_wednesday);
        expandThursday = view.findViewById(R.id.expand_thursday);
        expandFriday = view.findViewById(R.id.expand_friday);
        expandSaturday = view.findViewById(R.id.expand_saturday);
        expandSunday = view.findViewById(R.id.expand_sunday);

        textViewMonday = viewMonday.findViewById(R.id.hour_add_item);
        textViewTuesday = viewTuesday.findViewById(R.id.hour_add_item);
        textViewWednesday = viewWednesday.findViewById(R.id.hour_add_item);
        textViewThursday = viewThursday.findViewById(R.id.hour_add_item);
        textViewFriday = viewFriday.findViewById(R.id.hour_add_item);
        textViewSaturday = viewSaturday.findViewById(R.id.hour_add_item);
        textViewSunday = viewSunday.findViewById(R.id.hour_add_item);

        recyclerViewMonday = viewMonday.findViewById(R.id.list);
        recyclerViewTuesday = viewTuesday.findViewById(R.id.list);
        recyclerViewWednesday = viewWednesday.findViewById(R.id.list);
        recyclerViewThursday = viewThursday.findViewById(R.id.list);
        recyclerViewFriday = viewFriday.findViewById(R.id.list);
        recyclerViewSaturday = viewSaturday.findViewById(R.id.list);
        recyclerViewSunday = viewSunday.findViewById(R.id.list);

        recyclerViewMonday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTuesday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewWednesday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewThursday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFriday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSaturday.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSunday.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void setHideShowListener() {
        expandMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMonday.setVisibility(viewMonday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        expandTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewTuesday.setVisibility(viewTuesday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        expandWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewWednesday.setVisibility(viewWednesday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        expandThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewThursday.setVisibility(viewThursday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        expandFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFriday.setVisibility(viewFriday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        expandSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSaturday.setVisibility(viewSaturday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        expandSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSunday.setVisibility(viewSunday.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void addNewItemListener() {
        textViewMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterMonday == null) {
                    adapterMonday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewMonday.setAdapter(adapterMonday);
                }
                adapterMonday.addNewItem();
            }
        });
        textViewTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterTuesday == null) {
                    adapterTuesday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewTuesday.setAdapter(adapterTuesday);
                }
                adapterTuesday.addNewItem();
            }
        });

        textViewWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterWednesday == null) {
                    adapterWednesday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewWednesday.setAdapter(adapterWednesday);
                }
                adapterWednesday.addNewItem();
            }
        });

        textViewThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterThursday == null) {
                    adapterThursday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewThursday.setAdapter(adapterThursday);
                }
                adapterThursday.addNewItem();
            }
        });

        textViewFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterFriday == null) {
                    adapterFriday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewFriday.setAdapter(adapterFriday);
                }
                adapterFriday.addNewItem();
            }
        });

        textViewSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterSaturday == null) {
                    adapterSaturday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewSaturday.setAdapter(adapterSaturday);
                }
                adapterSaturday.addNewItem();
            }
        });
        textViewSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterSunday == null) {
                    adapterSunday = new ProviderManageBusinessHoursAdapter(new ArrayList<String>(),
                            new ArrayList<String>(), ManageBusinessHourFragment.this);

                    recyclerViewSunday.setAdapter(adapterSunday);
                }
                adapterSunday.addNewItem();
            }
        });
    }

    @Override
    public void onBusinessHoursLoaded(BusinessHours item) {

        this.item = item;

        if (item.getMonday() != null && (item.getMonday().getOffDay() == null
                                            || !item.getMonday().getOffDay().equals("on"))) {
            monday.setChecked(true);
            adapterMonday = new ProviderManageBusinessHoursAdapter(item.getMonday().getStarttime(),
                    item.getMonday().getEndtime(), this);
            recyclerViewMonday.setAdapter(adapterMonday);
        }
        if (item.getTuesday() != null && (item.getTuesday().getOffDay() == null
                || !item.getTuesday().getOffDay().equals("on"))) {
            tuesday.setChecked(true);
            adapterTuesday = new ProviderManageBusinessHoursAdapter(item.getTuesday().getStarttime(),
                    item.getTuesday().getEndtime(), this);
            recyclerViewTuesday.setAdapter(adapterTuesday);
        }
        if (item.getWednesday() != null && (item.getWednesday().getOffDay() == null
                || !item.getWednesday().getOffDay().equals("on"))) {
            wednesday.setChecked(true);
            adapterWednesday = new ProviderManageBusinessHoursAdapter(item.getWednesday().getStarttime(),
                    item.getWednesday().getEndtime(), this);
            recyclerViewWednesday.setAdapter(adapterWednesday);
        }
        if (item.getThursday() != null && (item.getThursday().getOffDay() == null
                || !item.getThursday().getOffDay().equals("on"))) {
            thursday.setChecked(true);
            adapterThursday = new ProviderManageBusinessHoursAdapter(item.getThursday().getStarttime(),
                    item.getThursday().getEndtime(), this);
            recyclerViewThursday.setAdapter(adapterThursday);
        }
        if (item.getFriday() != null && (item.getFriday().getOffDay() == null
                || !item.getFriday().getOffDay().equals("on"))) {
            friday.setChecked(true);
            adapterFriday = new ProviderManageBusinessHoursAdapter(item.getFriday().getStarttime(),
                    item.getFriday().getEndtime(), this);
            recyclerViewFriday.setAdapter(adapterFriday);
        }
        if (item.getSaturday() != null && (item.getSaturday().getOffDay() == null
                || !item.getSaturday().getOffDay().equals("on"))) {
            saturday.setChecked(true);
            adapterSaturday = new ProviderManageBusinessHoursAdapter(item.getSaturday().getStarttime(),
                    item.getSaturday().getEndtime(), this);
            recyclerViewSaturday.setAdapter(adapterSaturday);
        }
        if (item.getSunday() != null && (item.getSunday().getOffDay() == null
                || !item.getSunday().getOffDay().equals("on"))) {
            sunday.setChecked(true);
            adapterSunday = new ProviderManageBusinessHoursAdapter(item.getSunday().getStarttime(),
                    item.getSunday().getEndtime(), this);
            recyclerViewSunday.setAdapter(adapterSunday);
        }

        hideProgressDialog();
    }

}
