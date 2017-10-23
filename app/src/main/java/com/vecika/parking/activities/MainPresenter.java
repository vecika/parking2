package com.vecika.parking.activities;

import com.vecika.parking.BasePresenter;
import com.vecika.parking.rest.RestInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * \brief
 * \details
 *
 * @author vedran
 * @version 1.0
 *          \date 23.10.2017.
 *          <p>
 *          \copyright
 *          This code and information is provided "as is" without warranty of
 *          any kind, either expressed or implied, including but not limited to
 *          the implied warranties of merchantability and/or fitness for a
 *          particular purpose.
 *          <p>
 *          \par
 *          Copyright (c) Gauss d.o.o. All rights reserved
 */
public class MainPresenter extends BasePresenter<MainView> {

		private RestInterface restInterface;

	public MainPresenter(RestInterface restInterface) {
		this.restInterface = restInterface;
	}

	public void sendLocation(String licence, String geoLocation, String date, String grad, String zona){
		addSubscription(restInterface.silentLocation(licence, geoLocation, date, grad, zona)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {

					}
				}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {

			}
		}));
	}


}
