package com.tregix.serviceprovider.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tregix.serviceprovider.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WizardActivty extends BaseActivity {

    private TextView join;
    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wizard_activty);


        skip = findViewById(R.id.skip);
        join = findViewById(R.id.join);


        // Set up the user interaction to manually show or hide the system UI.
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(WizardActivty.this,SignupActivity.class));
              finish();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WizardActivty.this,NavigationDrawerActivity.class));
                finish();
            }
        });


    }

}
