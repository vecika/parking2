package com.vecika.parking.utils;

/**
 * Created by Vedran on 11.07.2017..
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by vecika on 11/07/17.
 */

public class GPSTracker implements LocationListener {

	Context context;

	public GPSTracker(Context context) {
		this.context = context;
	}

	public Location getLocation() {
		if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			Log.e("fist", "error");
			return null;
		}
		try {
			LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
			boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if (isGPSEnabled) {
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
				return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			} else {
				Log.e("sec", "errpr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}
}