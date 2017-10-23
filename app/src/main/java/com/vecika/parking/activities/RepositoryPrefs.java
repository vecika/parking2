package com.vecika.parking.activities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vecika.parking.models.novo.Grad;
import com.vecika.parking.models.novo.Zona;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by Vedran on 18.09.2017..
 */

public class RepositoryPrefs implements Repository {

	private Context mContext;
	private SharedPreferences mSharedPreferences;
	private Gson gson;
	private static final String SHARED_PREFERENCES_KEY = "shared_preferences_key";

	private static final String ARRAY_LIST_ZONES = "shared_preferences_zones";
	private static final String CITY_NAME = "shared_preferences_city_name";
	private static final String LICENCE_PLATES = "shared_preferences_licence_plate";
	private static final String SWITCH_TOGGLE_STATE = "shared_preferences_toggle_switch_state";
	private static final String SHOULD_USE_GEO = "shared_preferences_should_use_geo";
	private static final String SELECTED_CITY_INDEX = "shared_preferences_selected_city_index";
	private static final String TEMP_SELECTED_CITY = "shared_preferences_selected_temporary_city_index";
	private static final String GEO_LOCATION = "shared_preferences_selected_geo_location";


	public RepositoryPrefs(Context mContext) {
		this.mContext = mContext;
		this.mSharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
		gson = new Gson();
	}


	@Override
	public void saveSelectedCity(Grad grad) {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putString(CITY_NAME, grad.getIme());
		editPrefernces.putString(ARRAY_LIST_ZONES, gson.toJson(grad.getZone())).apply();

	}

	@Override
	public Grad fetchSelectedCity() {

		Grad grad = new Grad();
		String jsonZone = mSharedPreferences.getString(ARRAY_LIST_ZONES, null);
		Type type = new TypeToken<ArrayList<Zona>>() {}.getType();
		ArrayList<Zona> zone = gson.fromJson(jsonZone, type);
		grad.setIme(mSharedPreferences.getString(CITY_NAME, null));
		grad.setZone(zone);

		return grad;
	}

	@Override
	public void saveLicencePlate(String licencePlate) {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putString(LICENCE_PLATES, licencePlate).apply();

	}

	@Override
	public String getLicencePlate() {
		return mSharedPreferences.getString(LICENCE_PLATES, null);
	}

	@Override
	public void saveLocationSwitch(boolean isSwitched) {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putBoolean(SWITCH_TOGGLE_STATE, isSwitched).apply();
	}

	@Override
	public Boolean getLocationSwitch() {
		return mSharedPreferences.getBoolean(SWITCH_TOGGLE_STATE, false);
	}

	@Override
	public void saveUseGeo(boolean useGeo) {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putBoolean(SHOULD_USE_GEO, useGeo).apply();
	}

	@Override
	public Boolean getUseGeo() {
		return mSharedPreferences.getBoolean(SHOULD_USE_GEO, false);
	}

	@Override
	public void saveSelectedCityIndex(int index) {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putInt(SELECTED_CITY_INDEX, index).apply();
	}

	@Override
	public int getSelectedCityIndex() {
		return mSharedPreferences.getInt(SELECTED_CITY_INDEX,0);
	}

	@Override
	public void saveTempSelectedCity(String grad) {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putString(TEMP_SELECTED_CITY, grad).apply();
	}

	@Override
	public String getTempSelectedCity() {
		return mSharedPreferences.getString(TEMP_SELECTED_CITY,null);
	}

	@Override
	public void clearTempSelectedCity() {
		SharedPreferences.Editor editPrefernces = mSharedPreferences.edit();
		editPrefernces.putString(TEMP_SELECTED_CITY, null).apply();
	}

	@Override
	public void saveGeoLocation(String geoLocation) {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(GEO_LOCATION , geoLocation).apply();
	}

	@Override
	public String getGeoLocation() {
		return mSharedPreferences.getString(GEO_LOCATION, null);
	}
}
