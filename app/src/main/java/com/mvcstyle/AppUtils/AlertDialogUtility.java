package com.mvcstyle.AppUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.mvcstyle.R;


public class AlertDialogUtility {

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showAlert(Context context, String msg) {
		new AlertDialog.Builder(context).setIcon(0)
				.setTitle(context.getString(R.string.app_name)).setMessage(msg)
				.setCancelable(false).setNeutralButton("OK", null).show();
	}

	public static void showInternetAlert(Context context) {
//		new MaterialDialog.Builder(context)
//				.title(AppConstants.STR_INTERNET_ALERT_TITLE)
//				.content(AppConstants.STR_INETRNET_ALERT_MESSAGE)
//				.positiveText("Ok")
//				.callback(new MaterialDialog.ButtonCallback() {
//					@Override
//					public void onPositive(MaterialDialog dialog) {
//						dialog.dismiss();
//					}
//
//					@Override
//					public void onNeutral(MaterialDialog dialog) {
//					}
//
//					@Override
//					public void onNegative(MaterialDialog dialog) {
//					}
//				})
//				.show();

		new AlertDialog.Builder(context).setIcon(0)
				.setTitle(AppConstants.STR_INTERNET_ALERT_TITLE)
				.setMessage(AppConstants.STR_INETRNET_ALERT_MESSAGE)
				.setCancelable(false).setNeutralButton("OK", null).show();
	}

	public static void CustomAlert(Context context, String title,
			String message, String Positive_text, String Negative_text,
			DialogInterface.OnClickListener PositiveListener,
			DialogInterface.OnClickListener NegativeListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(title).setMessage(message)
				.setPositiveButton(Positive_text, PositiveListener)
				.setNegativeButton(Negative_text, NegativeListener);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	

	public static void showConfirmAlert(Context context, String msg,
			DialogInterface.OnClickListener onYesClick) {
		new AlertDialog.Builder(context).setIcon(0)
				.setTitle(context.getString(R.string.app_name)).setMessage(msg)
				.setCancelable(false).setNegativeButton("NO", null)
				.setPositiveButton("YES", onYesClick).show();
	}
}