package com.vecika.parking.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vecika.parking.R;
import com.vecika.parking.adapters.ZonePickerAdapter;
import com.vecika.parking.fragments.AlertDialogFragment;
import com.vecika.parking.fragments.AlertDialogFragmentOld;
import com.vecika.parking.fragments.InfoDialogFragment;
import com.vecika.parking.models.ZoneTocke;
import com.vecika.parking.models.novo.Grad;
import com.vecika.parking.models.novo.Zona;
import com.vecika.parking.rest.RestProvider;
import com.vecika.parking.utils.ConstantsHelper;
import com.vecika.parking.utils.GPSTracker;
import com.vecika.parking.utils.Singletons;
import com.vecika.parking.utils.UtilsMethods;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vedran on 24.06.2017..
 */

public class MainActivity extends BaseActivity implements AlertDialogFragmentOld.IOnTwoButtonAlert, ZonePickerAdapter.IAdapterImageClicked {


	private static final String TAG = MainActivity.class.getSimpleName();

	//region PARAMS
	@BindView(R.id.imageViewSettings)
	ImageView mImageViewSettings;
	@BindView(R.id.linearLayoutParent)
	LinearLayout mLinearLayoutParent;
	@BindView(R.id.buttonPay)
	Button buttonPay;
	@BindView(R.id.textViewOdabranoVozilo)
	TextView mTextViewOdabranoVozilo;
	@BindView(R.id.textViewTitle)
	TextView mTextViewTitle;
	@BindView(R.id.recyclerListZones)
	RecyclerView mRecyclerViewZones;
	@BindView(R.id.buttonSearch)
	LinearLayout mButtonSearch;
	@BindView(R.id.textViewToolbar)
	TextView mTextViewToolbarTitle;


	private boolean doubleBackToExitPressedOnce = false;
	private AlertDialogFragmentOld dialog;
	private LocationManager locationManager;
	private ArrayList<ZoneTocke> arrayListZone = new ArrayList<>();
	private ArrayList<Zona> arrayListZoneNovo = new ArrayList<>();
	private Repository repository;
	private ZonePickerAdapter adapter;
	private Zona selectedZona;
	private Zona gpsZona;
	private MainPresenter presenter;


	//endregion

	//region LIFECYCLE METHODS

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window window = getWindow();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
		}
		ButterKnife.bind(this);
		init();
	}


	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Snackbar snackbar = Snackbar.make(mLinearLayoutParent, R.string.press_again_to_exit, Snackbar.LENGTH_SHORT);
		snackbar.show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 3000);
	}

	//endregion


	//region CUSTOM METHODS
	private void init() {
		presenter = new MainPresenter(RestProvider.getInstance());
		repository = new RepositoryPrefs(this);
		mTextViewToolbarTitle.setText(repository.fetchSelectedCity().getIme() + " - PARKING");
		mButtonSearch.setVisibility(repository.getLocationSwitch() ? View.VISIBLE : View.GONE);
		if(repository.fetchSelectedCity().getIme().equals(ConstantsHelper.CITY_ZAGREB)){
			mButtonSearch.setVisibility(View.GONE);
		}



		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		this.mTextViewOdabranoVozilo.setText(repository.getLicencePlate());

		this.adapter = new ZonePickerAdapter(repository.fetchSelectedCity().getZone());
		this.adapter.setmCallback(this);
		this.mRecyclerViewZones.setLayoutManager(new LinearLayoutManager(this));
		this.mRecyclerViewZones.setAdapter(adapter);


	}


	public void sendSMS(String phoneNo, String msg) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, msg, null, null);
			Snackbar snackbar = Snackbar.make(mLinearLayoutParent, "Zahtjev za parking poslan! \n Molimo pročitajte poruku sa detaljima.", Snackbar.LENGTH_LONG);
			snackbar.show();
			try {
				presenter.sendLocation(
						repository.getLicencePlate(),
						repository.getGeoLocation(),
						UtilsMethods.longToString(Calendar.getInstance().getTimeInMillis()),
						repository.fetchSelectedCity().getIme(),
						selectedZona.getNaziv());
			}catch (Exception e){
//				ok
			}
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
					Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}


	//endregion
	//region LISTENERS

	@OnClick(R.id.buttonSearch)
	public void onButtonSearchClicked() {
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && repository.getLocationSwitch()) {
			GPSTracker gt = new GPSTracker(getApplicationContext());
			Location l = new Location("");
			if (gt.getLocation() != null) {
				l.setLatitude(gt.getLocation().getLatitude());
				l.setLongitude(gt.getLocation().getLongitude());
			}
			Log.d(TAG, "onButtonSearchClicked: " + l.getLatitude() + " " + l.getLongitude());

			Grad selectedGrad = repository.fetchSelectedCity();

			Location closestLoc = null;
			Location currentLoc = new Location("");

			for (int i = 0; i < selectedGrad.getZone().size(); i++) {
				for (int j = 0; j < selectedGrad.getZone().get(i).getLatLngModels().size(); j++) {

					currentLoc.setLatitude(selectedGrad.getZone().get(i).getLatLngModels().get(j).getLatitude());
					currentLoc.setLongitude(selectedGrad.getZone().get(i).getLatLngModels().get(j).getLongitude());

					if (closestLoc == null) {
						closestLoc = new Location("");
						closestLoc.setLatitude(currentLoc.getLatitude());
						closestLoc.setLongitude(currentLoc.getLongitude());
					}

					float distanceToCurrentLoc = currentLoc.distanceTo(l);
					float distanceToClosestLoc = closestLoc.distanceTo(l);

					if (distanceToClosestLoc > distanceToCurrentLoc) {
						closestLoc.setLatitude(currentLoc.getLatitude());
						closestLoc.setLongitude(currentLoc.getLongitude());

						repository.saveGeoLocation("" + closestLoc.getLatitude() + " " + closestLoc.getLongitude());

						gpsZona = new Zona(
								selectedGrad.getZone().get(i).getNaziv(),
								selectedGrad.getZone().get(i).getCijena(),
								selectedGrad.getZone().get(i).getBrojTelefona(),
								selectedGrad.getZone().get(i).getDrawable(),
								selectedGrad.getZone().get(i).getLatLngModels()
						);

						adapter.setSelectedZone(i);
					}
				}
			}

			Snackbar.make(mLinearLayoutParent, "Pronađena najbliža zona", Snackbar.LENGTH_LONG).show();

		} else {
			Snackbar.make(mLinearLayoutParent, "Morate dozvoliti uporabu GPS lokacije.", Snackbar.LENGTH_LONG).show();
		}
	}


	@OnClick(R.id.buttonPay)
	public void onClickButtonPay() {
		Log.d(getActivityTag(), "onClickButtonPay()");
		selectedZona = adapter.getSelectedZone();
		if (selectedZona != null && selectedZona.getNaziv() != null) {
			dialog = AlertDialogFragmentOld.newInstance(
					repository.getLicencePlate(),
					selectedZona.getNaziv(),
					selectedZona.getCijena()
			);

			dialog.setmCallback(this);
			dialog.show(getSupportFragmentManager(), "Alert_dialog");
		} else {
			Snackbar snackbar = Snackbar.make(mLinearLayoutParent, "Molimo odaberite zonu.", Snackbar.LENGTH_LONG);
			snackbar.show();
		}

	}

	@OnClick(R.id.imageViewSettings)
	public void onClickSettings() {
		Log.d(getActivityTag(), "onClickSettings()");
		Log.d(getActivityTag(), "onClickSettings()");

		startActivity(new Intent(this, SettingsActivity.class));
		finish();
	}

	/**
	 * On DialogFragment OK button clicked
	 */
	@Override
	public void okButtonClicked() {
		int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
		} else {
			if (selectedZona != null) {
				if (!repository.getLicencePlate().equals("")) {
					sendSMS(selectedZona.getBrojTelefona(), repository.getLicencePlate());

					new AlertDialogFragment.Builder()
							.withTitle("Poruka poslana!")
							.withMessage("Poruka je uspješno poslana, molimo provjerite Vašu povratnu poruku s detaljima!")
							.withPositiveButton("U Redu")
							.build()
							.show(getSupportFragmentManager(), AlertDialogFragment.class.getSimpleName());

				}
			}
			dialog.dismiss();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == 123) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				if (Singletons.getInstance().getSelectedZone() != null) {

					if (!repository.getLicencePlate().equals("")) {
						sendSMS(Singletons.getInstance().getSelectedZone().getTelefonskiBroj(), repository.getLicencePlate());
					}
				}
				dialog.dismiss();
			}
		} else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
			Snackbar.make(mLinearLayoutParent, "Za plačanje parkinga morate odobriti zahtjev za slanje SMS poruka.", Snackbar.LENGTH_LONG).show();
		} else {
			Snackbar.make(mLinearLayoutParent, "Za plačanje parkinga morate odobriti zahtjev za slanje SMS poruka.", Snackbar.LENGTH_LONG).show();
		}
	}

	/**
	 * On DialogFragment Cancel button clicked
	 */
	@Override
	public void cancelButtonClicked() {
		if (dialog.isVisible())
			dialog.dismiss();
	}

	@Override
	public void imageClicked(int drawable) {
		InfoDialogFragment infoDialogFragment = InfoDialogFragment.newInstance(drawable);
		infoDialogFragment.show(getSupportFragmentManager(), InfoDialogFragment.class.getSimpleName());
	}

	//endregion
}