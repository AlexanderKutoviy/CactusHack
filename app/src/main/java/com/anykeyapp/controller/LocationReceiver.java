package com.anykeyapp.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import br.com.safety.locationlistenerhelper.core.SettingsLocationTracker;

public class LocationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("my.action")) {
            Location locationData = intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.e("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());
        }
    }
}