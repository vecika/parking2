package com.vecika.parking.activities;

/**
 * Created by Vedran on 24.06.2017..
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.vecika.parking.fragments.BaseFragment;


public class BaseActivity extends AppCompatActivity implements BaseFragment.IBaseInterface{
	//region CLASS PARAMETERS



	//endregion
	//region GETTERS AND SETTERS

	/**
	 * Gets the TAG of an activity. Used easier fragment management of fragment instances
	 * to avoid using expensive instance of comparison.
	 *
	 * @return Simple name of the class extending {@link BaseActivity}
	 */
	public String getActivityTag() {
		return this.getClass().getSimpleName();
	}


	//endregion
	//region LIFECYCLE METHODS



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
		Log.i(getActivityTag(), "onCreate()");
	}


	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

		super.onCreate(savedInstanceState, persistentState);
		Log.i(getActivityTag(), "onCreate()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(getActivityTag(), "onRestart()");
	}


	@Override
	protected void onStart() {
		super.onStart();
		Log.i(getActivityTag(), "onStart()");
	}


	@Override
	protected void onResume() {
		super.onResume();
		Log.i(getActivityTag(), "onResume()");
	}


	@Override
	protected void onPause() {
		super.onPause();
		 Log.i(getActivityTag(), "onPause()");
	}


	@Override
	protected void onStop() {
		super.onStop();
	 Log.i(getActivityTag(), "onStop()");
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		 Log.i(getActivityTag(), "onDestroy()");
	}

	@Override
	public void onUserInteraction() {
		super.onUserInteraction();
	}

	//endregion
	//region INTERFACE METHODS

	@Override
	public void onFragmentInteraction(BaseFragment baseFragment) {
		 Log.i(getActivityTag(), "onFragmentInteraction -> Base Fragment: " + baseFragment.getFragmentTag());
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		 Log.i(getActivityTag(), "onFragmentInteraction.Path() -> " + uri.toString());


	}

	@Override
	public void onFragmentInitialization(String title) {
		Log.i(getActivityTag(), "onFragmentInitialization -> title: " + title);
	}

	@Override
	public void onFragmentInteraction(Intent intent) {
		 Log.i(getActivityTag(), "onFragmentInitialization -> intent: " + intent.getClass().getSimpleName());

		startActivity(intent);
//		overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);
	}
	@Override
	public void onFragmentInitialization(boolean showButton) {
		Log.i(getActivityTag(), "onFragmentInitialization -> showButton: " + showButton);
	}
	//endregion
}
