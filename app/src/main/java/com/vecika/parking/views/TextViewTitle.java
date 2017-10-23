package com.vecika.parking.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vecika.parking.utils.ConstantsHelper;

/**
 * Created by Vedran on 24.06.2017..
 */

public class TextViewTitle extends TextView {

	//region CONSTRUCTORS
	public TextViewTitle(Context context) {
		super(context);
	}

	public TextViewTitle(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		setCustomStuff(context);
	}

	public TextViewTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setCustomStuff(context);
	}

	public TextViewTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		setCustomStuff(context);
	}

	//endregion

	//region CUSTOM METHODS
	private void setCustomStuff(Context context) {
		if (isInEditMode()) return;

		Typeface typeface = Typeface.createFromAsset(context.getAssets(), ConstantsHelper.ASSETS_FONTS_PACKAGE_NAME + "MuseoSans_900.otf");
		this.setTypeface(typeface);

	}

	//endregion
}
