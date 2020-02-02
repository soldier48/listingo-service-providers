package com.tregix.serviceprovider.Utils;

import android.os.Bundle;

import com.tregix.serviceprovider.ServiceProviderApplication;

/**
 * Created by Gohar Ali on 2/1/2018.
 */

public class UtilFirebaseAnalytics {

    public static void logEvent(String event,String key, String value){
        Bundle params = new Bundle();
        params.putString(key, value);
        ServiceProviderApplication.getFirebaseAnalytics().logEvent(event, params);
    }

    public static void logEvent(String event, Bundle params){
        ServiceProviderApplication.getFirebaseAnalytics().logEvent(event, params);
    }

    public static void logProviderEvent(String event,String provider, String user){
        Bundle params = new Bundle();
        params.putString("Provider", provider);
        params.putString("User", user);
        ServiceProviderApplication.getFirebaseAnalytics().logEvent(event, params);
    }
}
