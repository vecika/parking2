package com.vecika.parking.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.vecika.parking.R;
import com.vecika.parking.views.TextViewTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vedran on 24.06.2017..
 */

public class AlertDialogFragmentOld extends DialogFragment {

	public static final String TAG = AlertDialogFragmentOld.class.getSimpleName();

	private static final String BUNDLE_KEY_ALERT_MESSAGE = "com.vecika.parking.fragments.AlertDialogFragmentOld.bundle_key_alert_dialog_fragment_message";
	private static final String BUNDLE_KEY_ALERT_REGISTRACIJA = "com.vecika.parking.fragments.AlertDialogFragmentOld.bundle_key_alert_dialog_fragment_registracija";
	private static final String BUNDLE_KEY_ALERT_IME_ZONE = "com.vecika.parking.fragments.AlertDialogFragmentOld.bundle_key_alert_dialog_fragment_ime_zone";
	private static final String BUNDLE_KEY_ALERT_CIJENA = "com.vecika.parking.fragments.AlertDialogFragmentOld.bundle_key_alert_dialog_fragment_cijena";

	@BindView(R.id.buttonDialogCancel)
	Button mButtonDialogCancel;
	@BindView(R.id.buttonDialogOk)
	Button mButtonDialogOk;
	@BindView(R.id.textViewRegistracija)
	TextViewTitle mTextViewRegistracija;
	@BindView(R.id.textViewZona)
	TextViewTitle mTextViewZona;
	@BindView(R.id.textViewCijena)
	TextViewTitle mTextViewCijena;

	private IOnTwoButtonAlert mCallback;
	private String mRegistracija;
	private String mImeZone;
	private String mCijena;

	//endregion
	//region CONSTRUCTOR
	public static AlertDialogFragmentOld newInstance(String registracija, String imeZone, String cijena) {
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_KEY_ALERT_REGISTRACIJA, registracija);
		bundle.putString(BUNDLE_KEY_ALERT_IME_ZONE, imeZone);
		bundle.putString(BUNDLE_KEY_ALERT_CIJENA, cijena);

		AlertDialogFragmentOld fragment = new AlertDialogFragmentOld();
		fragment.setArguments(bundle);
		return fragment;
	}
	//endregion
	//region LIFECYCLE METHODS


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView()");

		View view = inflater.inflate(R.layout.two_button_dialog_fragment, container, false);
		ButterKnife.bind(this, view);
		init();

		return view;
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// The only reason you might override this method when using onCreateView() is
		// to modify any dialog characteristics. For example, the dialog includes a
		// title by default, but your custom layout might not need it. So here you can
		// remove the dialog title, but you must call the superclass to get the Dialog.
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.setCancelable(false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (dialog.getWindow() != null)
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		return dialog;
	}


	//endregion
	//region CUSTOM METHODS
	private void init() {

		if (getArguments() != null) {
			if (getArguments().containsKey(BUNDLE_KEY_ALERT_REGISTRACIJA)) {
				this.mRegistracija = getArguments().getString(BUNDLE_KEY_ALERT_REGISTRACIJA);
				this.mTextViewRegistracija.setText(this.mRegistracija);
			}
		}
		if (getArguments() != null) {
			if (getArguments().containsKey(BUNDLE_KEY_ALERT_IME_ZONE)) {
				this.mImeZone = getArguments().getString(BUNDLE_KEY_ALERT_IME_ZONE);
				this.mTextViewZona.setText(this.mImeZone);
			}
		}
		if (getArguments() != null) {
			if (getArguments().containsKey(BUNDLE_KEY_ALERT_CIJENA)) {
				this.mCijena = getArguments().getString(BUNDLE_KEY_ALERT_CIJENA);
				this.mTextViewCijena.setText(this.mCijena);
			}
		}
	}

	//endregion
	//region INTERFACE METHODS


	@OnClick({R.id.buttonDialogCancel, R.id.buttonDialogOk})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.buttonDialogCancel:
				if (this.mCallback != null)
					this.mCallback.cancelButtonClicked();
				break;
			case R.id.buttonDialogOk:
				if (this.mCallback != null)
					this.mCallback.okButtonClicked();
				break;
		}
	}

	//endregion
	//region INTEFACE CLASS

	/**
	 * Inteface for handling clicks on alert dialog fragments with two choices
	 */
	public interface IOnTwoButtonAlert {

		void okButtonClicked();

		void cancelButtonClicked();

	}

	//endregion
	//region SETTER

	public void setmCallback(IOnTwoButtonAlert mCallback) {
		this.mCallback = mCallback;
	}


	//endregion


}
