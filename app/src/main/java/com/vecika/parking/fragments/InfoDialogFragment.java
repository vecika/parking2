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

import com.vecika.parking.R;
import com.vecika.parking.utils.TouchImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vedran on 27.06.2017..
 */

public class InfoDialogFragment extends DialogFragment {

	public static final String TAG = InfoDialogFragment.class.getSimpleName();
	private static final String BUNDLE_KEY_INFO_IMAGE = "com.vecika.parking.fragments.AlertDialogFragmentOld.bundle_key_image";

	@BindView(R.id.touchImageView)	TouchImageView 	mTouchImageView;

	private int mImage;

	//endregion
	//region InfoDialogFragment
	public static InfoDialogFragment newInstance(int image) {
		Bundle bundle = new Bundle();
		bundle.putInt(BUNDLE_KEY_INFO_IMAGE, image);

		InfoDialogFragment fragment = new InfoDialogFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	//endregion
	//region LIFECYCLE METHODS


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView()");

		View view = inflater.inflate(R.layout.info_dialog_fragment, container, false);
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
			if (getArguments().containsKey(BUNDLE_KEY_INFO_IMAGE)) {
				this.mImage = getArguments().getInt(BUNDLE_KEY_INFO_IMAGE);

				this.mTouchImageView.setImageResource(mImage);

			}
		}

	}

	//endregion



}
