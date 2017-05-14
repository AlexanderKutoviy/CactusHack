package com.anykeyapp.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.anykeyapp.BinApplication;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import br.com.safety.locationlistenerhelper.core.SettingsLocationTracker;

public class LocationReceiver extends BroadcastReceiver {

    private void getAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(BinApplication.getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.e("IGA", "Address" + add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("my.action")) {
            Location locationData = (Location) intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());

            getAddress(locationData.getLatitude(), locationData.getLongitude());
        }
    }
}