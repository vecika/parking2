package com.vecika.parking.activities;

import com.google.android.gms.common.api.BooleanResult;
import com.vecika.parking.models.novo.Grad;

/**
 * Created by Vedran on 18.09.2017..
 */

public interface Repository {

	void saveSelectedCity(Grad grad);

	Grad fetchSelectedCity();

	void saveLicencePlate(String licencePlate);

	String getLicencePlate();

	void saveLocationSwitch(boolean isSwitched);

	Boolean getLocationSwitch();

	void saveUseGeo(boolean useGeo);

	Boolean getUseGeo();

	void saveSelectedCityIndex(int index);

	int getSelectedCityIndex();

	void saveTempSelectedCity(String grad);

	String getTempSelectedCity();

	void clearTempSelectedCity();

	void saveGeoLocation(String geoLocation);

	String getGeoLocation();

}
