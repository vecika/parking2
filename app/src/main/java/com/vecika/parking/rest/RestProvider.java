package com.vecika.parking.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * \brief
 * \details
 *
 * @author vedran
 * @version 1.0
 *          \date 4.10.2017.
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
public class RestProvider {

	private static RestInterface restInterface;
	private static Retrofit retrofit;

	public static RestInterface getInstance() {
		if (restInterface == null) {
			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			OkHttpClient.Builder client = new OkHttpClient.Builder()
					.addInterceptor(interceptor);

			retrofit = new Retrofit.Builder()
					.baseUrl("http://192.168.2.107:1337")
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.client(client.build())
					.build();
			restInterface = retrofit.create(RestInterface.class);
		}
		return restInterface;

	}

	//region GETTERS AND SETTERS
	public static Retrofit getRetrofit(){
		return retrofit;
	}
	//endregion

}
