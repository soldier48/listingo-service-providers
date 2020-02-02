package com.tregix.serviceprovider.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;

public class SearchResultActivity extends ProviderListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Search Result");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_filter_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                Intent newIntent = new Intent(this, AdvanceSearch.class);
                if(getIntent() != null) {
                    Bundle bundle = getIntent().getBundleExtra(Constants.DATA);
                    if(bundle != null) {
                        newIntent.putExtra(Constants.DATA,bundle);
                    }
                    }
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
