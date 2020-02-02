package com.tregix.serviceprovider.activities;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.Utils.SharedPreferenceUtil;
import com.tregix.serviceprovider.Utils.StickyService;
import com.tregix.serviceprovider.fragments.HomeFragment;
import com.tregix.serviceprovider.fragments.JobsListingFragment;
import com.tregix.serviceprovider.fragments.ManageBusinessHourFragment;
import com.tregix.serviceprovider.fragments.ManagePrivacyFragment;
import com.tregix.serviceprovider.fragments.ManageServicesFragment;
import com.tregix.serviceprovider.fragments.MyFavoritesFragment;
import com.tregix.serviceprovider.fragments.MyInboxFragment;
import com.tregix.serviceprovider.fragments.NearByActivity;
import com.tregix.serviceprovider.fragments.UpdatePackageFragment;


public class NavigationDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private TextView name;
    private TextView msg;
    private CircularImageView pic;
    private ImageView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        initViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(HomeFragment.newInstance());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH));
        }

        Intent stickyService = new Intent(this, StickyService.class);
        startService(stickyService);

        if (!AppUtils.isconnected()) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.err_no_internet)
                    , Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        if (getIntent() != null) {
            String fragment = getIntent().getStringExtra("fragment");

            if (fragment != null && fragment.equals("ManageAppointment")) {
                replaceFragment(new ManageAppointmentFragment());
            }
        }

        AdView mAdView = findViewById(R.id.adView);
        if(getString(R.string.ad_enabled).equals("true")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }else{
            mAdView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String fragment = intent.getStringExtra("fragment");

        if (fragment.equals("ManageAppointment")) {
            replaceFragment(new ManageAppointmentFragment());
        }

    }

    @Override
    protected void setActionBar() {

    }

    private void initViews() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        name = (TextView) header.findViewById(R.id.nav_username);
        msg = (TextView) header.findViewById(R.id.nav_user_msg);
        pic = header.findViewById(R.id.user_profile_pic);
        banner = header.findViewById(R.id.user_banner_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideShowItems();
    }

    private void hideShowItems() {
        User user = DatabaseUtil.getInstance().getUser();
        boolean isUserLoggedIn = SharedPreferenceUtil.getBoolen(this, Constants.ISUSERLOGGEDIN);
        navigationView.getMenu().findItem(R.id.nav_register).setVisible(!isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_login).setVisible(!isUserLoggedIn);
        //navigationView.getMenu().findItem(R.id.nav_logout).setVisible(isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_appointments).setVisible(isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_manage_services).setVisible(isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_favorites_listing).setVisible(isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_inbox).setVisible(isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_dashboard_item).setVisible(isUserLoggedIn);
        navigationView.getMenu().setGroupVisible(R.id.nav_dashboard, isUserLoggedIn);
        navigationView.getMenu().findItem(R.id.nav_manage_profile).setVisible(isUserLoggedIn);


        if (isUserLoggedIn) {
            if (user != null) {
                name.setText(user.getData().getData().getDisplayName());
                msg.setText(user.getData().getData().getUserEmail());
                Picasso.get().load(user.getData().getData().getAvatar()).into(pic);
                Picasso.get().load(user.getData().getData().getBanner()).fit().into(banner);

                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(NavigationDrawerActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                });

                if (user.getData().getRoles() != null &&
                        user.getData().getRoles().get(0).toLowerCase().equals("customer")) {
                    navigationView.getMenu().setGroupVisible(R.id.nav_dashboard, false);
                    navigationView.getMenu().findItem(R.id.nav_dashboard_item).setVisible(false);

                }
            }
        } else {
            name.setText(getString(R.string.guest));
            msg.setText(getString(R.string.greetings));
            Picasso.get().load(R.drawable.ic_user_circle).into(pic);
            Picasso.get().load(R.drawable.ic_user_background).fit().into(banner);
        }

        /*(navigationView.getMenu().findItem(R.id.nav_footer_1)).setTitle(
                getString(R.string.copyright_2017_the_listingo) + "" +
                getString(R.string.version) +BuildConfig.VERSION_NAME);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            getPrevious();
        }
    }


    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msg_exit)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            openAcitivty(SignupActivity.class);
        } else if (id == R.id.nav_login) {
            openAcitivty(LoginActivity.class);
        } else if (id == R.id.nav_appointments) {
            openAcitivty(MyAppointmentsActivity.class);
        } else if (id == R.id.nav_manage_profile) {
            openAcitivty(ProfileActivity.class);
        } else if (id == R.id.nav_aboutus) {
            openWebview(Constants.ABOUTUS, getString(R.string.about_us));
        } else if (id == R.id.nav_contact_support) {
            openWebview(Constants.CONTACTUS, getString(R.string.contact_us));
        } else if (id == R.id.nav_term_of_use) {
            openWebview(Constants.TERMSOFUSE, getString(R.string.terms_of_use));
        } else if (id == R.id.nav_help_support) {
            openWebview(Constants.HELPSUPPORT, getString(R.string.help_support));
        } else if (id == R.id.nav_logout) {
            confirmLogout();
        } else if (id == R.id.nav_invite) {
            shareApp();
        } else if (id == R.id.nav_rate) {
            showRateDialog(NavigationDrawerActivity.this);
        } else if (id == R.id.nav_manage_services) {
            replaceFragment(new ManageServicesFragment());
        } else if (id == R.id.nav_home) {
            replaceFragment(HomeFragment.newInstance());
        } else if (id == R.id.nav_favorites_listing) {
            replaceFragment(new MyFavoritesFragment());
        } else if (id == R.id.nav_manage_appointment) {
            replaceFragment(new ManageAppointmentFragment());
        } else if (id == R.id.nav_privacy_settings) {
            replaceFragment(ManagePrivacyFragment.newInstance());
        } else if (id == R.id.nav_jobs) {
            replaceFragment(new JobsListingFragment());
        } else if (id == R.id.nav_business_hours) {
            replaceFragment(ManageBusinessHourFragment.newInstance());
        } else if (id == R.id.nav_inbox) {
            replaceFragment(new MyInboxFragment());
        } else if (id == R.id.nav_nearby) {
            openAcitivty(NearByActivity.class);
        } else if (id == R.id.nav_settings) {
            openAcitivty(SettingsActivity.class);
        } else if (id == R.id.nav_package) {
            replaceFragment(new UpdatePackageFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void replaceFragment(Fragment newFragment) {

        String backStateName = newFragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.flContentRoot, newFragment);
            transaction.addToBackStack(backStateName);
            transaction.commit();
        }
    }

    private void getPrevious() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            showExitDialog();
        }
    }


}
