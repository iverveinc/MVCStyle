package com.mvcstyle.AppUtils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.mvcstyle.Model.UserData;

import java.util.Calendar;
import java.util.HashMap;

//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResult;
//import com.google.android.gms.location.LocationSettingsStatusCodes;




public class SessionManager {
    //public static final String PREF_NAME = "RequestAndReceive_user_Session";
    //public static final String PREF_NAME = "RequestAndReceive_user_Session_pass";
    //public static final String PREF_NAME = "RequestAndReceive_pro_Session_pass";
    // public static final String PREF_NAME = "RequestAndReceive_pro_Session";

    public static final String PREF_NAME = "RequestAndReceive_Session";
    public static final String AP_DATE = "date";

    public static final String KEY_PASSWORD_USER = "passwordUser";

    public static final String KEY_EMAIL_USER = "emailUser";

    public static final String KEY_USER_NAME = "userUserName";

    public static final String KEY_IS_REM_USER = "isRememberUser";

    public static final String KEY_IS_FROM_USER = "isFromUser";

    public static final String KEY_IS_LOGIN_USER = "isLoginUser";

    public static final String KEY_IS_SIGNUP_USER = "isSignUpUser";

    public static final String KEY_IS_FIRST_TIME = "isFirstTime";
    public static final String KEY_IS_UPDATED = "isUpdated";

    public static final String KEY_USERNAME = "userName";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_PRO_MOBILE = "mobilePro";

    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_EMAIL = "emailId";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LONGI = "longi";
    public static final String KEY_IMG_URL = "imgUrl";


    public static final String KEY_USER_REMEMBER_NEW = "User Remember";
    public static final String KEY_USER_NAME_NEW = "User Name";
    public static final String KEY_USER_PASS_NEW = "User Pass";


    static GoogleApiClient googleApiClient;
    static AlarmManager alarmManager;
    static PendingIntent pendingIntent;

    public static HashMap<String, String> user;

    public static boolean getRememberUser(Context context) {
        boolean blAutoSave = false;
        try {
            SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            blAutoSave = pref.getBoolean(KEY_IS_REM_USER, false);


            Log.e("blAutoSave", "blAutoSave  ::" + blAutoSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blAutoSave;
    }


    public static void setLoginUser(Context context, boolean value) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putBoolean(KEY_IS_LOGIN_USER, value);


            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRememberUser(Context context, boolean value) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putBoolean(KEY_IS_REM_USER, value);
            Log.e("value", "value  ::" + value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setRememberUserData(Context context, String username, String password) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(KEY_USER_REMEMBER_NEW, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putString(KEY_USER_NAME_NEW, username);
            editor.putString(KEY_USER_PASS_NEW, password);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setLoginIdPassUser(Context context, String id, String pass) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putString(KEY_IS_LOGIN_USER, id);
            editor.putString(KEY_PASSWORD_USER, pass);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setLoginPassUser(Context context, boolean bool) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putBoolean(KEY_IS_LOGIN_USER, bool);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean isLoginUser(Context context) {
        try {

//            return context.getSharedPreferences(PREF_NAME, 0).getBoolean(KEY_IS_LOGIN_USER, false);

            if (context.getSharedPreferences(PREF_NAME, 0).getBoolean(KEY_IS_LOGIN_USER, false) == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFromUser(Context context) {
        try {

//            return context.getSharedPreferences(PREF_NAME, 0).getBoolean(KEY_IS_LOGIN_USER, false);

            if (context.getSharedPreferences(PREF_NAME, 0).getBoolean(KEY_IS_FROM_USER, false) == true) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setFromUser(Context context, boolean value) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putBoolean(KEY_IS_FROM_USER, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean isRememberUser(Context context) {
        try {
//            SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, 0);
//            return preferences.getBoolean(KEY_IS_REM_USER, false);
            if (context.getSharedPreferences(PREF_NAME, 0).getBoolean(KEY_IS_REM_USER, false) == true)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void updated(Context context, boolean value) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
            editor.putBoolean(KEY_IS_UPDATED, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getSignUp(Context context) {
        try {
            SharedPreferences prefSignupData = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = prefSignupData.edit();
//            editor.putBoolean(KEY_IS_FIRST_TIME, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void saveUserCred(Context context, String userName, String userPassword, Boolean sign_up) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_PASSWORD_USER, userPassword);
            editor.putString(KEY_USER_NAME, userName);
            editor.putBoolean(KEY_IS_SIGNUP_USER, sign_up);
            editor.putBoolean(KEY_IS_LOGIN_USER, sign_up);
            editor.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getUserCred(Context context) {
        try {
//            SharedPreferences editor = (SharedPreferences) context.getSharedPreferences(PREF_NAME, 0).edit();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            preferences.getString(KEY_PASSWORD_USER, null);
            preferences.getString(KEY_USER_NAME, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void logOutUser(Context context, Boolean isLogin) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putBoolean(KEY_IS_LOGIN_USER, isLogin);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void saveUserDetail(Context context, String userName, String userPassword, Boolean sign_up) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_PASSWORD_USER, userPassword);
            editor.putString(KEY_USER_NAME, userName);
            editor.putBoolean(KEY_IS_SIGNUP_USER, sign_up);
            editor.putBoolean(KEY_IS_LOGIN_USER, sign_up);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static UserData getUserData(Context context) {
        UserData userData = new UserData();
        try {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

            userData.setUserName(preferences.getString(SessionManager.KEY_USERNAME, ""));
            userData.setFirstname(preferences.getString(SessionManager.KEY_FIRSTNAME, ""));
            userData.setLastname(preferences.getString(SessionManager.KEY_LASTNAME, ""));
            userData.setUserEmail(preferences.getString(SessionManager.KEY_EMAIL, ""));
            userData.setAddress(preferences.getString(SessionManager.KEY_ADDRESS, ""));
            userData.setMobile(preferences.getString(SessionManager.KEY_MOBILE, ""));
            userData.setGender(preferences.getString(SessionManager.KEY_GENDER, ""));
            userData.setImgUrl(preferences.getString(SessionManager.KEY_IMG_URL, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userData;
    }


    public static void saveUserData(Context context, UserData userData) {
        try {
//            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(SessionManager.KEY_USERNAME, userData.getUserName());
            editor.putString(SessionManager.KEY_FIRSTNAME, userData.getFirstname());
            editor.putString(SessionManager.KEY_LASTNAME, userData.getLastname());
            editor.putString(SessionManager.KEY_EMAIL, userData.getUserEmail());
            editor.putString(SessionManager.KEY_ADDRESS, userData.getAddress());
            editor.putString(SessionManager.KEY_PASSWORD_USER, userData.getUserPassword());
            editor.putString(SessionManager.KEY_MOBILE, userData.getMobile());
            editor.putString(SessionManager.KEY_GENDER, userData.getGender());
            editor.putString(SessionManager.KEY_IMG_URL, userData.getImgUrl());

            Log.e("","=====MNo====== "+userData.getMobile() );
            Log.e("URL", "URL USER" + userData.getImgUrl());
            Log.e("", "====e======" + editor);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rememberMeUser(Context context, Boolean rem) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putBoolean(KEY_IS_REM_USER, rem);

            editor.putBoolean("saveLogin", true);
            editor.putString("username", "");
            editor.putString("password", "");

            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getlattitude(Context context) {
        String point = "";
        try {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            point = preferences.getString(AppConstants.LATTITUDE, "0.0");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return point;
    }

    public static String getlongitude(Context context) {
        String point = "";
        try {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            point = preferences.getString(AppConstants.LONGITUDE, "0.0");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return point;
    }

    public static void saveLocation(double lat, double lng, Context context) {
        try {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(AppConstants.LATTITUDE, "" + lat);
            editor.putString(AppConstants.LONGITUDE, "" + lng);
            editor.commit();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public static void setLocationService(final Activity activity) {
        // TODO Auto-generated method stub

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(activity).addApi(
                    LocationServices.API).build();
            googleApiClient.connect();
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                .checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                // final LocationSettingsStates state =
                // result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        updateLatLong(activity);
                        Applog.v("result", "Success");

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed
                        // by showing the user
                        // a dialog.
                        Applog.v("result ",
                                "RESOLUTION_REQUIRED");
                        try {
                            // Show the dialog by calling
                            // startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(activity, 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Applog.v("result ",
                                "SETTINGS_CHANGE_UNAVAILABLE");
                        // Location settings are not satisfied. However, we have no
                        // way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    public static ProgressDialog progressDialog;

    public static void updateLatLong(Activity activity) {
        try {
            progressDialog = new ProgressDialog(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            progressDialog.setMessage("Please Wait ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            Applog.v("updateLatLong called", "*******************");
            long longCurrentTimeInMilli = Calendar.getInstance()
                    .getTimeInMillis();
            Intent intent = new Intent(activity,
                    MyLocationBroadcastReceiver.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			Bundle b = new Bundle();
//			b.putInt("CALLFROM", callFrom);
//			intent.putExtras(b);
            pendingIntent = PendingIntent.getBroadcast(activity, 0,
                    intent, 0);

            alarmManager = (AlarmManager) activity.getSystemService(
                    Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    longCurrentTimeInMilli, 30000, pendingIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopLocationService(Context context) {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            context.stopService(new Intent(context, MyLocationBroadcastReceiver.class));
            Applog.v("service stopped", "*************");
        }
    }

    public static int setPassword(Context context, String status, String message) {
        int i = 0;
        try {
            SharedPreferences pref = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;

            editor = pref.edit();
            editor.putString(AppConstants.STATUS, status);
            editor.putString(AppConstants.MESSAGE, message);

            Log.i("IsSuccess", "" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void setApDate(Context context, String bookingId) {
        try {
            Log.e("Session ","session :: "+bookingId);
            SharedPreferences pref = context
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;

            editor = pref.edit();
            editor.putString(AP_DATE, bookingId);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getApDate(Context context) {
        String point = "";
        try {
            SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            point = pref.getString(AP_DATE, "");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return point;
    }





}