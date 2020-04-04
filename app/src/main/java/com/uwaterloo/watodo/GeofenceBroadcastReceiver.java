package com.uwaterloo.watodo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.application.isradeleon.notify.Notify;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "GeofenceBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.e(TAG, "GEOFENCE BROADCAST ERROR");
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            String geofenceTaskTitle = triggeringGeofences.get(0).getRequestId();
            Log.i(TAG, "GEOFENCE TRIGGERED: " + geofenceTaskTitle);
            //TODO: Send a notification with the geoFenceTaskTitle
            Notify.create(MainActivity.getAppContext())
                    .setTitle("Location Reminder")
                    //.setContent(placeName) //phone version
                    .setContent(geofenceTaskTitle)
                    .setColor(R.color.colorPrimary)
                    .setAction(new Intent(MainActivity.getAppContext(), MainActivity.class))
                    .setImportance(Notify.NotificationImportance.MAX)
                    .show(); // Finally showing the notification
        }
    }
}