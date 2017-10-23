package com.vecika.parking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.vecika.parking.R;
import com.vecika.parking.utils.ConstantsHelper;
import com.vecika.parking.utils.TinyDB;

public class SplashActivity extends BaseActivity {

	private Repository repository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		init();

		int secondsDelayed = 1;
		new Handler().postDelayed(new Runnable() {
			public void run() {

				if (repository.getLicencePlate() != null && !repository.getLicencePlate().equals("") && repository.fetchSelectedCity()!=null) {
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
					finish();
				} else {
					startActivity(new Intent(SplashActivity.this, SettingsActivity.class));
					finish();
				}


			}
		}, secondsDelayed * 1500);
	}

	private void init() {
		repository = new RepositoryPrefs(this);

	}


}
