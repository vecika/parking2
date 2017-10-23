package com.vecika.parking.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Vedran on 24.06.2017..
 */

public class TypeFace {

	private HashMap<String, Typeface> 	mHashMapTypeface;

	public Typeface getTypeface(Context context, String asset) {
		if (asset == null) return null;
		if (this.mHashMapTypeface == null) this.mHashMapTypeface = new HashMap<>();

		String location = ConstantsHelper.ASSETS_FONTS_PACKAGE_NAME + asset;

		if (this.mHashMapTypeface.containsKey(location)) return this.mHashMapTypeface.get(location);

		try {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), location);
			this.mHashMapTypeface.put(location, typeface);
			return typeface;
		} catch (Exception e) {
			return null;
		}
	}


}
