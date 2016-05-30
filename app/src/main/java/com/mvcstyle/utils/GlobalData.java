package com.mvcstyle.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.DisplayMetrics;

public class GlobalData {
	SharedPreferences sh_Pref;
	Editor toEdit;

	public static final String TABLE_NUMBER = "table_number";

	public static final int NUM_OF_TABLE_ROW = 12;

	public static final String TABLES_ARRAY = "tables_array";

	public static void setSharedPrefernces(Boolean val, Context context) {
		SharedPreferences pref = context.getSharedPreferences("MyPref", 0);
		Editor editor = pref.edit();
		editor.putBoolean("VAL", val);
		editor.commit();
	}

	public static Boolean getSharedPrefernces(Context context) {
		SharedPreferences pref = context.getSharedPreferences("MyPref", 0);
		return pref.getBoolean("VAL", false);
	}

	public static boolean isTablet(Activity activity) {

		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		float yInches = metrics.heightPixels / metrics.ydpi;
		float xInches = metrics.widthPixels / metrics.xdpi;
		double diagonalInches = Math
				.sqrt(xInches * xInches + yInches * yInches);

//		Log.i("GlobalData", "diagonalInches::" + diagonalInches);

		if (diagonalInches >= 6.8) {
			// 7inch device or bigger
			return true;
		} else {
			// smaller device
			return false;
		}
	}

	public static final String RAW_IDS_ADDED = "RAW_IDS_ADDED";
	public static final String IS_FREE_TABLE_FIRST_TIME = "IS_FREE_TABLE_FIRST_TIME";

}
