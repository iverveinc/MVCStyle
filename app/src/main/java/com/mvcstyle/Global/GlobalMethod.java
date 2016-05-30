package com.mvcstyle.Global;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.widget.EditText;

import com.mvcstyle.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GlobalMethod {


    /* Shared preference keys */
    public static final String TWITTER_PREF = "twitter_pref";
    public static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    public static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    public static final String PREF_KEY_TWITTER_LOGIN = "is_twitter_loggedin";
    public static final String PREF_USER_NAME = "twitter_user_name";
    static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";
    public static SharedPreferences mSharedPreferences;
    public static final String Consumer_key = "qdPUIQgQvnP2lJCF6838PMArm";
    public static final String Consumer_Secret_key = "I5GmEZYJUNC1C4KKJ0ZNK5rRPggV847IFJTiGHQ45rHnHnDSRW";
    public static final String twitter_callback = "http://javatechig.android.app/";
    public static final String twitter_oauth_verifier = "oauth_verifier";

    public static void showError(String error, EditText id) {
        int ecolor = Color.parseColor("#F2E08B");
        // String estring =
        // getResources().getString(R.string.edttxt_err_enter_email);
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(error);
        ssbuilder.setSpan(fgcspan, 0, error.length(), 0);
        id.setError(ssbuilder);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }

    public static void showAlert(Context context, String msg) {
        new AlertDialog.Builder(context).setIcon(0)
                .setTitle(context.getString(R.string.app_name)).setMessage(msg)
                .setCancelable(false).setNeutralButton("OK", null).show();
    }

    public static void showConfirmAlertSession(Context context, String msg, DialogInterface.OnClickListener onYesClick)
    {
        new AlertDialog.Builder(context).setIcon(0).setTitle(context.getString(R.string.app_name)).setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", onYesClick).show();
    }

    public final static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    public static String getDate(String dateString) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parsed = null;
        try {
            parsed = sourceFormat.parse(dateString); // => Date is in UTC now
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        String result = dateFormatter.format(parsed);

        return result;
    }

    public static String getUTCDate(String dateString) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        sourceFormat.setTimeZone(TimeZone.getDefault());
        Date parsed = null;
        try {
            parsed = sourceFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String result = dateFormatter.format(parsed);

        return result;
    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }
}
