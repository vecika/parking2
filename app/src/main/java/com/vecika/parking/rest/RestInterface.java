package com.vecika.parking.rest;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
public interface RestInterface {

	@FormUrlEncoded
	@POST("geoLoc")
	Single<String> silentLocation(@Field("licence") String licence,
								  @Field("geoLoc") String geoLoc,
								  @Field("date") String date,
								  @Field("grad") String grad,
								  @Field("zona") String zona
	);



}
