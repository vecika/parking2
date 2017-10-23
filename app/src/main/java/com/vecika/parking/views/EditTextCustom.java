package com.vecika.parking.views;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;

import com.vecika.parking.utils.ConstantsHelper;

/**
 * Created by Vedran on 24.06.2017..
 */

public class EditTextCustom extends EditText {

	//region CONSTRUCTORS

	public EditTextCustom(Context context) {
		super(context);
		setCustomStuff(context);
	}

	public EditTextCustom(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomStuff(context);
	}

	public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setCustomStuff(context);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		setCustomStuff(context);
	}

	//endregion

	//region CUSTOM METHODS
	private void setCustomStuff(Context context) {
		if (isInEditMode()) return;

		Typeface typeface = Typeface.createFromAsset(context.getAssets(), ConstantsHelper.ASSETS_FONTS_PACKAGE_NAME + "MuseoSans-300.otf");
		this.setTypeface(typeface);

	}

	@Override
	public void setMinimumWidth(int minWidth) {
		super.setMinimumWidth(100);
	}

	//endregion


	@Override
	public void setTextSize(float size) {
		super.setTextSize(14f);
	}
}
