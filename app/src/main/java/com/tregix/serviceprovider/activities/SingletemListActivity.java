package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.adapters.SingleItemRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public class SingletemListActivity extends BaseActivity  {

    private RecyclerView recyclerView;
    private SingleItemRecyclerViewAdapter adapter;
    private TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selectable);
        initViews();
        initAdapter();
        setAdapter();

        getSupportActionBar().setTitle(getIntent().getBundleExtra(Constants.DATA).getString(Constants.TITLE));

    }

    private void initViews() {
        findViewById(R.id.selection_title).setVisibility(View.GONE);
        findViewById(R.id.selection_clear).setVisibility(View.GONE);
        noData = findViewById(R.id.selection_no_record);
    }

    private void initAdapter() {
        recyclerView = (RecyclerView) this.findViewById(R.id.selection_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        List<String> selectableItems = getIntent().getBundleExtra(Constants.DATA).getStringArrayList(Constants.DATA);
        if(selectableItems != null && !selectableItems.isEmpty()) {
            adapter = new SingleItemRecyclerViewAdapter(selectableItems);
            recyclerView.setAdapter(adapter);
        }else{
            recyclerView.setAdapter(null);
            noData.setVisibility(View.VISIBLE);
        }

    }


}
