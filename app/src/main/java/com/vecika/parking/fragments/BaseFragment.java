package com.vecika.parking.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vedran on 24.06.2017..
 */

public class BaseFragment extends Fragment {
	//region CLASS PARAMETERS


	/**
	 * Instance of {@link IBaseInterface} used for fragment interaction with the
	 * parent activity. This will be used in onAttach and onDetach lifecycle methods
	 * to ease the transfer and handling for all fragments that extend this class.
	 */
	protected IBaseInterface	mIBaseInterfaceListener;
	protected Context mContext;


	//endregion
	//region GETTERS AND SETTERS


	/**
	 * Gets the TAG of a fragment. Used easier fragment management of fragment instances
	 * to avoid using expensive instance of comparison.
	 *
	 * @return Simple name of the class extending {@link BaseFragment}
	 */
	public String getFragmentTag() {
		return this.getClass().getSimpleName();
	}


	//endregion
	//region LIFECYCLE METHODS


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		 Log.i(getFragmentTag(), "onAttach()");

		try {
			this.mIBaseInterfaceListener	= (IBaseInterface) context;
			this.mContext 					= context.getApplicationContext();
		} catch (ClassCastException e) {
			throw new ClassCastException(context.toString()
					+ " must implement onFragmentInteractionListener");
		}
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Log.i(getFragmentTag(), "onCreate()");
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		 Log.i(getFragmentTag(), "onCreateView()");
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		 Log.i(getFragmentTag(), "onActivityCreated()");
	}


	@Override
	public void onStart() {
		super.onStart();
		 Log.i(getFragmentTag(), "onStart()");
	}


	@Override
	public void onResume() {
		super.onResume();
		 Log.i(getFragmentTag(), "onResume()");

	}


	@Override
	public void onPause() {
		super.onPause();
		 Log.i(getFragmentTag(), "onPause()");

	}


	@Override
	public void onStop() {
		super.onStop();
		 Log.i(getFragmentTag(), "onStop()");
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		 Log.i(getFragmentTag(), "onDestroyView()");
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		 Log.i(getFragmentTag(), "onDestroy()");
	}


	@Override
	public void onDetach() {
		super.onDetach();
		 Log.i(getFragmentTag(), "onDetach()");

		this.mIBaseInterfaceListener 	= null;
		this.mContext 					= null;
	}

	//endregion
	//region INTERFACE


	/**
	 * \brief   	Base Fragment Interface
	 * \details     This interface class contains all methods used for fragment interaction with the
	 * 				parent activity. It's called in the BaseFragment class, and is used to
	 * 				communicate to the parent activity what button is clicked, event executed etc.,
	 * 				so that every action can be executed by the activity, and not by the fragment
	 * 				itself. Core implementation is done in the {@link com.vecika.parking.activities.BaseActivity}
	 *
	 * @author Gudelj Ivan
	 * @version 1.0
	 *          \date 12.05.2016
	 *          \copyright
	 *          This code and information is provided "as is" without warranty of
	 *          any kind, either expressed or implied, including but not limited to
	 *          the implied warranties of merchantability and/or fitness for a
	 *          particular purpose.
	 *          \par
	 *          Copyright (c) Gauss d.o.o. All rights reserved
	 */
	public interface IBaseInterface {
		/**
		 * Communicate the URI between {@link BaseFragment} and {@link com.vecika.parking.activities.BaseActivity}
		 *
		 * @param uri Uri to be communicated between classes
		 */
		void onFragmentInteraction(Uri uri);

		/**
		 * Communicate the title between {@link BaseFragment} and {@link com.vecika.parking.activities.BaseActivity}
		 * @param title	Title of the fragment
		 */
		void onFragmentInitialization(String title);

		/**
		 * Communicate base fragment between a fragment and an activity. Use this for navigation purposes only.
		 * @param baseFragment	{@link BaseFragment to be navigated to}
		 */
		void onFragmentInteraction(BaseFragment baseFragment);

		/**
		 * Pass an intent from fragment to activity. Used to start new screens.
		 * @param intent	Intent of the screen
		 */
		void onFragmentInteraction(Intent intent);

		/**
		 * Communicate the callButton between {@link BaseFragment} and {@link com.vecika.parking.activities.BaseActivity}
		 * @param showButton visibility of callButton
		 */
		void onFragmentInitialization(boolean showButton);
	}


	//endregion
}