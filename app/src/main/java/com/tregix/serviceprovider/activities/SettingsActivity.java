package com.tregix.serviceprovider.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.SharedPreferenceUtil;

public class SettingsActivity extends BaseActivity {

    Spinner spinnerctrl;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        boolean isUserLoggedIn = SharedPreferenceUtil.getBoolen(this, Constants.ISUSERLOGGEDIN);

        findViewById(R.id.nav_logout).setVisibility(isUserLoggedIn?View.VISIBLE:View.INVISIBLE);

        spinnerctrl = (Spinner) findViewById(R.id.spinner1);

        String[] mTestArray= getResources().getStringArray(R.array.locale);
        spinnerctrl.setPrompt(mTestArray[SharedPreferenceUtil.getIntValue(this,Constants.POSITION)]);

        spinnerctrl.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 1) {
                    setLocale("en",pos);
                } else if (pos == 2) {
                    setLocale("es",pos);
                } else if (pos == 3) {
                    setLocale("in",pos);
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }


    public void onClick(View item) {
        // Handle navigation view item clicks here.
        int id = item.getId();

        if (id == R.id.nav_aboutus) {
            openWebview(Constants.ABOUTUS,getString(R.string.about_us));
        } else if (id == R.id.nav_contact_support) {
            openWebview(Constants.CONTACTUS,getString(R.string.contact_us));
        } else if (id == R.id.nav_term_of_use) {
            openWebview(Constants.TERMSOFUSE,getString(R.string.terms_of_use));
        }  else if (id == R.id.nav_help_support) {
            openWebview(Constants.HELPSUPPORT,getString(R.string.help_support));
        }else if (id == R.id.nav_logout) {
            confirmLogout();
        }else if(id == R.id.nav_invite){
            shareApp();
        }else if(id == R.id.nav_rate){
            showRateDialog(this);
        }
    }

    public void setLocale(String lang,int pos) {
        SharedPreferenceUtil.storeStringValue(this, Constants.LOCALE,lang);
        SharedPreferenceUtil.storeIntValue(this, Constants.POSITION,pos);
        updateLocale(lang);
        openAcitivty( NavigationDrawerActivity.class);
        finish();
    }


}
