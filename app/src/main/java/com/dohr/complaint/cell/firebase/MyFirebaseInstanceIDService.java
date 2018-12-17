package com.dohr.complaint.cell.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.LOGIN_PREF;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = null;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("DeviceToken", "" + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);

        storeRegIdInPref(refreshedToken);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences prefences = getApplicationContext().getSharedPreferences(UserPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefences.edit();
        editor.putString("token_key", token);
        Log.e("storeRegIdInPref:", token);
        editor.apply();
    }
}
