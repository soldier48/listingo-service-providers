package com.tregix.serviceprovider;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import androidx.multidex.MultiDex;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Migration;
import com.tregix.serviceprovider.chat.NotificationOpenedHandler;

import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Gohar Ali on 12/14/2017.
 */

public class ServiceProviderApplication extends Application {

    public static final int SCHEMA_VERSION = 1;
    private static FirebaseAnalytics mFirebaseAnalytics;
    private static ServiceProviderApplication instance;
    private Locale locale = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .migration(new Migration())
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        MultiDex.install(this);

        instance = this;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationOpenedHandler())
                .init();

        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
                    AppUtils.sendUserTokenRequest(status.getSubscriptionStatus().getUserId());

        MobileAds.initialize(this, getString(R.string.ad_app_id));

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("locale", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang))
        {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (locale != null)
        {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }
    public static ServiceProviderApplication getInstance(){
        return instance;
    }

    public static FirebaseAnalytics getFirebaseAnalytics(){
        return mFirebaseAnalytics;
    }

}