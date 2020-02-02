package com.tregix.serviceprovider.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.tregix.serviceprovider.Model.Login.User;

import io.realm.Realm;


public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onNewToken(String var1){
        // Get updated InstanceID token.
        Log.d(TAG, "Refreshed token: " + var1);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(var1);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        Realm backgroundRealm = Realm.getDefaultInstance();
        User user = backgroundRealm.where(User.class).findFirst();
        if(user != null) {
            //AppUtils.updateUserToken(user.getData().getID(),token);
            //AppUtils.updateTokenOnFirebase(token, user);
        }
    }



}
