package com.vecika.parking.models;

/**
 * Created by Vedran on 11.07.2017..
 */

public class Tocke {

	private double latitude;
	private double longitude;

	public Tocke(double longitude, double latitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
