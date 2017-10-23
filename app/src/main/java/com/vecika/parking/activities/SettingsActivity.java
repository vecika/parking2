package com.vecika.parking.activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.vecika.parking.R;
import com.vecika.parking.models.novo.Grad;
import com.vecika.parking.models.novo.LatLngModel;
import com.vecika.parking.models.novo.Zona;
import com.vecika.parking.utils.ConstantsHelper;
import com.vecika.parking.utils.TinyDB;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by Vedran on 24.06.2017..
 */

public class SettingsActivity extends BaseActivity {

	//region PARAMS
	@BindView(R.id.editTextLicencePlate)
	EditText mEditTextLicencePlate;
	@BindView(R.id.switchGpsLocation)
	Switch mSwitchGpsLocation;
	@BindView(R.id.buttonSave)
	Button mButtonSave;
	@BindView(R.id.spinner)
	Spinner mSpinner;
	@BindView(R.id.textViewGPSTitle)
	TextView mTextViewGPSTitle;

	private TinyDB tinyDB;
	private ArrayList<String> cityList = new ArrayList<>();
	private Repository repository;
	private ArrayList<Grad> listAllCities = new ArrayList<>();
	private Grad savedCity;

	//endregion

	//region LIFECYCLE METHODS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Window window = getWindow();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
		}
		ButterKnife.bind(this);
		init();
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	//endregion

	//region CUSTOM METHODS

	private void init() {

		repository = new RepositoryPrefs(this);

		//region REGION STUFF

		ArrayList<LatLngModel> latLngZona1Osijek = new ArrayList<>();
		ArrayList<LatLngModel> latLngZona2Osijek = new ArrayList<>();
		ArrayList<LatLngModel> latLngZona3Osijek = new ArrayList<>();

		latLngZona1Osijek.add(new LatLngModel(45.560721, 18.684429));
		latLngZona1Osijek.add(new LatLngModel(45.559226, 18.684525));
		latLngZona1Osijek.add(new LatLngModel(45.559091, 18.682659));
		latLngZona1Osijek.add(new LatLngModel(45.553171, 18.683506));
		latLngZona1Osijek.add(new LatLngModel(45.553141, 18.682530));
		latLngZona1Osijek.add(new LatLngModel(45.554494, 18.680770));
		latLngZona1Osijek.add(new LatLngModel(45.554539, 18.678303));
		latLngZona1Osijek.add(new LatLngModel(45.556267, 18.678163));
		latLngZona1Osijek.add(new LatLngModel(45.557701, 18.675588));
		latLngZona1Osijek.add(new LatLngModel(45.560594, 18.676028));
		latLngZona1Osijek.add(new LatLngModel(45.562201, 18.680298));
		latLngZona1Osijek.add(new LatLngModel(45.562246, 18.682637));
		latLngZona1Osijek.add(new LatLngModel(45.560849, 18.684311));
		latLngZona1Osijek.add(new LatLngModel(45.561889, 18.682252));
		latLngZona1Osijek.add(new LatLngModel(45.561876, 18.682251));
		latLngZona1Osijek.add(new LatLngModel(45.554710, 18.682989));
		latLngZona1Osijek.add(new LatLngModel(45.557291, 18.682700));
		latLngZona1Osijek.add(new LatLngModel(45.557382, 18.710713));
		latLngZona1Osijek.add(new LatLngModel(45.557819, 18.710896));
		latLngZona1Osijek.add(new LatLngModel(45.558246, 18.710576));
		latLngZona1Osijek.add(new LatLngModel(45.558076, 18.709622));
		latLngZona1Osijek.add(new LatLngModel(45.557892, 18.708618));
		latLngZona1Osijek.add(new LatLngModel(45.558506, 18.676039));
		latLngZona1Osijek.add(new LatLngModel(45.558502, 18.676972));
		latLngZona1Osijek.add(new LatLngModel(45.557766, 18.678265));

		latLngZona2Osijek.add(new LatLngModel(45.563846, 18.675063));
		latLngZona2Osijek.add(new LatLngModel(45.563486, 18.676425));
		latLngZona2Osijek.add(new LatLngModel(45.563125, 18.676640));
		latLngZona2Osijek.add(new LatLngModel(45.562321, 18.676382));
		latLngZona2Osijek.add(new LatLngModel(45.562284, 18.674086));
		latLngZona2Osijek.add(new LatLngModel(45.561412, 18.673711));
		latLngZona2Osijek.add(new LatLngModel(45.561217, 18.675406));
		latLngZona2Osijek.add(new LatLngModel(45.560669, 18.675288));
		latLngZona2Osijek.add(new LatLngModel(45.560969, 18.672509));
		latLngZona2Osijek.add(new LatLngModel(45.561976, 18.670911));
		latLngZona2Osijek.add(new LatLngModel(45.562464, 18.671093));
		latLngZona2Osijek.add(new LatLngModel(45.561833, 18.672734));
		latLngZona2Osijek.add(new LatLngModel(45.562652, 18.673035));
		latLngZona2Osijek.add(new LatLngModel(45.563320, 18.671404));
		latLngZona2Osijek.add(new LatLngModel(45.564590, 18.672284));
		latLngZona2Osijek.add(new LatLngModel(45.563850, 18.675044));
		latLngZona2Osijek.add(new LatLngModel(45.557356, 18.675234));
		latLngZona2Osijek.add(new LatLngModel(45.554569, 18.674419));
		latLngZona2Osijek.add(new LatLngModel(45.554494, 18.675653));
		latLngZona2Osijek.add(new LatLngModel(45.557416, 18.676264));
		latLngZona2Osijek.add(new LatLngModel(45.557360, 18.675288));
		latLngZona2Osijek.add(new LatLngModel(45.561795, 18.682380));
		latLngZona2Osijek.add(new LatLngModel(45.561495, 18.682401));
		latLngZona2Osijek.add(new LatLngModel(45.561585, 18.685062));
		latLngZona2Osijek.add(new LatLngModel(45.556717, 18.685727));
		latLngZona2Osijek.add(new LatLngModel(45.556507, 18.682637));
		latLngZona2Osijek.add(new LatLngModel(45.555816, 18.682723));
		latLngZona2Osijek.add(new LatLngModel(45.556026, 18.686221));
		latLngZona2Osijek.add(new LatLngModel(45.556567, 18.687079));
		latLngZona2Osijek.add(new LatLngModel(45.556297, 18.688302));
		latLngZona2Osijek.add(new LatLngModel(45.555335, 18.687658));
		latLngZona2Osijek.add(new LatLngModel(45.554584, 18.687937));
		latLngZona2Osijek.add(new LatLngModel(45.554509, 18.688796));
		latLngZona2Osijek.add(new LatLngModel(45.555996, 18.689203));
		latLngZona2Osijek.add(new LatLngModel(45.555004, 18.693581));
		latLngZona2Osijek.add(new LatLngModel(45.558821, 18.695018));
		latLngZona2Osijek.add(new LatLngModel(45.560564, 18.686264));
		latLngZona2Osijek.add(new LatLngModel(45.561555, 18.685963));
		latLngZona2Osijek.add(new LatLngModel(45.562036, 18.688109));
		latLngZona2Osijek.add(new LatLngModel(45.562787, 18.687809));
		latLngZona2Osijek.add(new LatLngModel(45.561818, 18.682417));
		latLngZona2Osijek.add(new LatLngModel(45.561690, 18.682420));
		latLngZona2Osijek.add(new LatLngModel(45.556200, 18.682915));

		latLngZona3Osijek.add(new LatLngModel(45.562111, 18.678340));
		latLngZona3Osijek.add(new LatLngModel(45.562239, 18.677308));
		latLngZona3Osijek.add(new LatLngModel(45.562719, 18.677541));
		latLngZona3Osijek.add(new LatLngModel(45.562588, 18.678464));
		latLngZona3Osijek.add(new LatLngModel(45.562117, 18.678344));
		latLngZona3Osijek.add(new LatLngModel(45.561240, 18.675374));
		latLngZona3Osijek.add(new LatLngModel(45.561307, 18.674376));
		latLngZona3Osijek.add(new LatLngModel(45.562051, 18.674526));
		latLngZona3Osijek.add(new LatLngModel(45.561901, 18.675535));
		latLngZona3Osijek.add(new LatLngModel(45.561241, 18.675379));
		latLngZona3Osijek.add(new LatLngModel(45.556267, 18.680508));
		latLngZona3Osijek.add(new LatLngModel(45.556248, 18.679268));
		latLngZona3Osijek.add(new LatLngModel(45.556905, 18.679322));
		latLngZona3Osijek.add(new LatLngModel(45.556894, 18.680438));
		latLngZona3Osijek.add(new LatLngModel(45.556267, 18.680510));

		Zona zona1Osijek = new Zona("Zona 1", "4,00 kn", "708311", R.drawable.zona1, latLngZona1Osijek);
		Zona zona2Osijek = new Zona("Zona 2", "3,00 kn", "708312", R.drawable.zona2, latLngZona2Osijek);
		Zona zona3Osijek = new Zona("Zona 3", "2,00 kn", "708313", R.drawable.zona3, latLngZona3Osijek);

		ArrayList<Zona> zoneOsijek = new ArrayList<>();
		zoneOsijek.add(zona1Osijek);
		zoneOsijek.add(zona2Osijek);
		zoneOsijek.add(zona3Osijek);

		Grad gradOsijek = new Grad(ConstantsHelper.CITY_OSIJEK, zoneOsijek);


		//region dummyZG
		ArrayList<LatLngModel> latLngZona1ZG = new ArrayList<>();
		latLngZona1ZG.add(new LatLngModel(23.3213, 123.323));


		Zona zona1ZG = new Zona("I. zona", "6,00 kn", "700101", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona2ZG= new Zona("II. 1. zona", "3,00 kn", "700102", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona3ZG= new Zona("II. 2. zona", "3,00 kn", "700102", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona4ZG= new Zona("II. 3. zona", "3,00 kn", "700108", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona5ZG= new Zona("III. zona", "1,50 kn", "700103", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona6ZG= new Zona("IV. 1. zona", "5,00 kn", "700105", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona7ZG= new Zona("IV. 2. zona", "10,00 kn", "700104", R.drawable.zagreb_zone, latLngZona1ZG);
		Zona zona8ZG= new Zona("IV. 3. zona", "10,00 kn", "700107", R.drawable.zagreb_zone, latLngZona1ZG);


		ArrayList<Zona> zonezg = new ArrayList<>();
		zonezg.add(zona1ZG);
		zonezg.add(zona2ZG);
		zonezg.add(zona3ZG);
		zonezg.add(zona4ZG);
		zonezg.add(zona5ZG);
		zonezg.add(zona6ZG);
		zonezg.add(zona7ZG);
		zonezg.add(zona8ZG);

		Grad gradzg = new Grad(ConstantsHelper.CITY_ZAGREB, zonezg);
		//endregion



		// TODO: 18.09.2017. UBACIT OVDJE SVE GRADOVE U ARRAY
		listAllCities.add(gradOsijek);
		listAllCities.add(gradzg);

		//endregion

		if (repository.getLicencePlate() != null && !repository.getLicencePlate().equals("")) {
			mEditTextLicencePlate.setText(repository.getLicencePlate());
		}
		mSwitchGpsLocation.setChecked(repository.getLocationSwitch());



		// populating spinner with city names
		for (Grad listAllCity : listAllCities) {
			cityList.add(listAllCity.getIme());
		}


		setUpSpinner();

		if(repository.fetchSelectedCity()!=null && repository.fetchSelectedCity().getIme() !=null && repository.fetchSelectedCity().getIme().equals(ConstantsHelper.CITY_ZAGREB)){
			mSwitchGpsLocation.setVisibility(View.GONE);
			mTextViewGPSTitle.setVisibility(View.GONE);
		}else {
			mSwitchGpsLocation.setVisibility(View.VISIBLE);
			mTextViewGPSTitle.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * Sets up the custom spinner
	 * Font, color, and behaviour
	 */
	private void setUpSpinner() {

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList);
		mSpinner.setSelection(repository.getSelectedCityIndex(),true);
		mSpinner.setBackgroundColor(ContextCompat.getColor(this, R.color.android_white));
		mSpinner.setAdapter(arrayAdapter);
		if(repository.fetchSelectedCity().getIme()!=null){
			int spinnerPos = arrayAdapter.getPosition(repository.fetchSelectedCity().getIme());
			mSpinner.setSelection(spinnerPos);
		}
	}


	//endregion


	//region LISTENERS

	@OnClick(R.id.buttonSave)
	public void onButtonSaveClicked() {
		Log.d(getActivityTag(), "onButtonSaveClicked()");

		repository.clearTempSelectedCity();
		repository.saveSelectedCity(savedCity);
		repository.saveSelectedCityIndex(mSpinner.getSelectedItemPosition());



		if (this.mEditTextLicencePlate.getText().toString().length() == 0) {
			Snackbar snackbar = Snackbar.make(mEditTextLicencePlate, "Molimo unesite registarsku oznaku vozila.", Snackbar.LENGTH_LONG);
			snackbar.show();
		} else {
			repository.saveLicencePlate(this.mEditTextLicencePlate.getText().toString());
			repository.saveLocationSwitch(mSwitchGpsLocation.isChecked());
			repository.saveUseGeo(mSwitchGpsLocation.isChecked());

			startActivity(new Intent(this, MainActivity.class));
			finish();
		}

	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@OnCheckedChanged(R.id.switchGpsLocation)
	public void onChecked(boolean checked) {
		if (checked) {
			int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
			if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
			}
		}

	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == 123) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

			} else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
				Snackbar snackbar = Snackbar.make(mEditTextLicencePlate, "Za korištenje mogućnosti plačanja parkinga uporabom GPS lokacije, morate odobriti korištenje GPS usluga.", Snackbar.LENGTH_LONG);
				snackbar.show();
				mSwitchGpsLocation.setChecked(false);
			} else {
				Snackbar snackbar = Snackbar.make(mEditTextLicencePlate, "Za korištenje mogućnosti plačanja parkinga uporabom GPS lokacije, morate odobriti korištenje GPS usluga.", Snackbar.LENGTH_LONG);
				snackbar.show();
				mSwitchGpsLocation.setChecked(false);
			}
		}
	}

	@OnItemSelected(R.id.spinner)
	public void onSpinnerSelected() {
		repository.saveSelectedCityIndex(mSpinner.getSelectedItemPosition());
		repository.saveTempSelectedCity(mSpinner.getSelectedItem().toString());
		if(repository.getTempSelectedCity().equals(ConstantsHelper.CITY_ZAGREB)){
			mSwitchGpsLocation.setVisibility(View.GONE);
			mTextViewGPSTitle.setVisibility(View.GONE);
		}else {
			mSwitchGpsLocation.setVisibility(View.VISIBLE);
			mTextViewGPSTitle.setVisibility(View.VISIBLE);
		}

		for (int i = 0; i < listAllCities.size(); i++) {
			if (mSpinner.getSelectedItem().toString().equals(listAllCities.get(i).getIme())) {
				savedCity = listAllCities.get(i);
			}
		}

	}

//endregion

}
