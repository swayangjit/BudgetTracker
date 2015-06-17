package com.skytopper.budgettracker.customviews;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.skytopper.budgettracker.util.Constants;

public class BudgetTrackerEditText extends EditText
{	
	public BudgetTrackerEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		int[] attrsArray = {android.R.attr.textStyle};
		TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
		int style = ta.getInt(0, -1);
		Typeface tf;

		if( style == Typeface.NORMAL)
		{
			tf = Typeface.createFromAsset(context.getAssets(), "fonts/"+ Constants.ROBOTO_REGULAR);
		}
		
		else if( style == (Typeface.BOLD))
		{
			tf = Typeface.createFromAsset(context.getAssets(), "fonts/"+ Constants.ROBOTO_MEDIUM);
		}
		
		else
		{
			tf = Typeface.createFromAsset(context.getAssets(), "fonts/"+ Constants.ROBOTO_REGULAR);
		}
		
		setTypeface(tf);
		
		ta.recycle();
	}
	
	
}
