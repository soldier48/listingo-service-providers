package com.tregix.serviceprovider.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.tregix.serviceprovider.ServiceProviderApplication;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.activities.NavigationDrawerActivity;

import org.json.JSONObject;

public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        int senderId = 0;
        boolean isAppointment = false;
        try {
            if (data != null) {
                if(data.has("sender_id"))
                    senderId = data.getInt("sender_id");

                if(data.has("isAppointment"))
                    isAppointment = data.getBoolean("isAppointment");
                if (senderId > 0 && !isAppointment) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.ID, senderId);

                    Intent intent = new Intent(ServiceProviderApplication.getInstance().getApplicationContext()
                            , ChatActivity.class);
                    intent.putExtra(Constants.DATA, bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ServiceProviderApplication.getInstance().getApplicationContext().startActivity(intent);
                }else{
                    Intent intent = new Intent(ServiceProviderApplication.getInstance().getApplicationContext()
                            , NavigationDrawerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.putExtra("fragment", "ManageAppointment");
                    ServiceProviderApplication.getInstance().getApplicationContext().startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
        // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(intent);

        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /* 
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }
}