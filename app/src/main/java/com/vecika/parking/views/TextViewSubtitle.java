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

public class TextViewSubtitle extends TextView {

	//region CONSTRUCTORS
	public TextViewSubtitle(Context context) {
		super(context);
	}

	public TextViewSubtitle(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		setCustomStrufff(context,attrs);
	}

	public TextViewSubtitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setCustomStrufff(context, attrs);
	}

	public TextViewSubtitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		setCustomStrufff(context, attrs);
	}

	//endregion

	//region CUSTOM METHODS
	private void setCustomStrufff(Context context ,AttributeSet attrs) {

		if (isInEditMode()) return;

		Typeface typeface = Typeface.createFromAsset(context.getAssets(), ConstantsHelper.ASSETS_FONTS_PACKAGE_NAME + "MuseoSans-300.otf");
		this.setTypeface(typeface);

	}

	//endregion

}
