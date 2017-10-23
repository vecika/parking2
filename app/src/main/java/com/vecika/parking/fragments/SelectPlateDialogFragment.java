package com.vecika.parking.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.vecika.parking.R;
import com.vecika.parking.adapters.PlatesDialogAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vedran on 08.07.2017..
 */

public class SelectPlateDialogFragment extends DialogFragment {

	public static final String TAG = InfoDialogFragment.class.getSimpleName();
	private static final String BUNDLE_KEY_PLATES = "com.vecika.parking.fragments.SelectPlateDialog_plates_array_list";

	@BindView(R.id.recyclerListPlates)	RecyclerView 	mRecyclerListPlates;

	private ArrayList<String> mArrayListPlates = new ArrayList<>();
	private PlatesDialogAdapter mAdapter;
	private Context mContext;

	//endregion
	//region InfoDialogFragment
	public static SelectPlateDialogFragment newInstance(ArrayList<String> arrayListPlates) {
		Bundle bundle = new Bundle();
		bundle.putStringArrayList(BUNDLE_KEY_PLATES, arrayListPlates);


		SelectPlateDialogFragment fragment = new SelectPlateDialogFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	//endregion
	//region LIFECYCLE METHODS


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView()");

		View view = inflater.inflate(R.layout.select_plates_dialog_fragment, container, false);
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
			if (getArguments().containsKey(BUNDLE_KEY_PLATES)) {
				this.mArrayListPlates = getArguments().getStringArrayList(BUNDLE_KEY_PLATES);
			}
		}

		if(this.mArrayListPlates != null && !this.mArrayListPlates.isEmpty()){
			if(mAdapter == null) {
				mAdapter = new PlatesDialogAdapter(mContext, mArrayListPlates);
				this.mRecyclerListPlates.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
				this.mRecyclerListPlates.setAdapter(this.mAdapter);
			}
		}

	}

	//endregion



}
