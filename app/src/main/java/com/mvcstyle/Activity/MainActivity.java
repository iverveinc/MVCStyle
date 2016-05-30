package com.mvcstyle.Activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.mvcstyle.AppUtils.AlertDialogUtility;
import com.mvcstyle.AppUtils.Applog;
import com.mvcstyle.AppUtils.ConnectivityDetector;
import com.mvcstyle.AppUtils.Fonts;
import com.mvcstyle.AppUtils.SessionManager;
import com.mvcstyle.Global.GlobalMethod;
import com.mvcstyle.Model.UserData;
import com.mvcstyle.R;
import com.mvcstyle.WebService.GetJsonWithCallBack;
import com.mvcstyle.WebService.OnUpdateListener;
import com.mvcstyle.WebService.WebField;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    private LinearLayout llEmail, llPass;
    private EditText edtPass, edtUsername;
    private TextView tvForgotPass;
    private Button btnSignUp, btnSignIn;
    private TextView imgShowPw;

    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static String regId = "";
    //    private GoogleCloudMessaging gcm;
    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.sel_text));
        }

        getId();
        setTypeFace();
        setListner();
    }

    private void getId() {
        try {
            imgShowPw =(TextView) findViewById(R.id.imgShowPw);
            btnSignUp = (Button) findViewById(R.id.btnSignUp);
            btnSignIn = (Button) findViewById(R.id.btnSignIn);
//            tvForgotPass = (TextView) findViewById(R.id.tvForgotPassAMAIN);
            edtUsername = (EditText) findViewById(R.id.edtUsername);
            edtPass = (EditText) findViewById(R.id.edtPass);

            setupUI(edtUsername);
            setupUI(edtPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTypeFace() {
        try {
            btnSignUp.setTypeface(Fonts.robotRegular(getApplicationContext()));
            btnSignIn.setTypeface(Fonts.robotRegular(getApplicationContext()));
//            tvForgotPass.setTypeface(Fonts.robotRegular(getApplicationContext()));
            edtUsername.setTypeface(Fonts.robotRegular(getApplicationContext()));
            edtPass.setTypeface(Fonts.robotRegular(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    private void setListner() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        imgShowPw.setOnTouchListener(this);
//        tvForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSignIn:
                checkLogin();
                break;

            case R.id.btnSignUp:
                Intent intentSignup = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentSignup);
                break;
        }
    }

    private void checkLogin() {
        if (edtUsername.getText().toString().isEmpty()) {
            GlobalMethod.showAlert(MainActivity.this, "Please enter user name");
        } else if (edtPass.getText().toString().isEmpty()) {
            GlobalMethod.showAlert(MainActivity.this, "Please enter password");
        } else {

            callSignInService();
        }
    }

    private void callSignInService(){
        try {
            if (ConnectivityDetector.isConnectingToInternet(MainActivity.this)) {
                JSONObject jsonObjectInput = new JSONObject();

                jsonObjectInput.put(WebField.LOGIN_USER.REQUEST_USERNAME, edtUsername.getText().toString());

                jsonObjectInput.put(
                        WebField.LOGIN_USER.REQUEST_PASSWORD,
                        edtPass.getText().toString());

                String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                Log.e("REGI ID ", "sadfa   " + regId);
                jsonObjectInput.put(WebField.LOGIN_USER.REQUEST_DEVICE_ID,
                        android_id);
                jsonObjectInput.put(WebField.LOGIN_USER.REQUEST_DEVICE_TYPE,
                        "0");
                jsonObjectInput.put(
                        WebField.LOGIN_USER.REQUEST_USER_TYPE,
                        1);
                String mode = "LoginUser";
                Applog.e("Json Req", "" + jsonObjectInput);
                new GetJsonWithCallBack(MainActivity.this, jsonObjectInput,
                        1, mode, new OnUpdateListener() {

                    @Override
                    public void onUpdateComplete(JSONObject jsonObject,
                                                 boolean isSuccess) {
                        if (isSuccess) {
                            try {

                                SessionManager.setLoginUser(MainActivity.this, true);

                                SessionManager.setLoginUser(MainActivity.this, true);

                                if (jsonObject != null) {
                                    if (jsonObject.has("userDetail")) {
                                        JSONObject jsonUserDetails = jsonObject.getJSONObject("userDetail");

                                        UserData userData = new UserData();
                                        userData.setUserName(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_USER_NAME));
                                        userData.setFirstname(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_FIRST_NAME));
                                        userData.setLastname(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_LAST_NAME));
                                        userData.setUserEmail(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_EMAIL_ID));
                                        userData.setAddress(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_ADDRESS));
                                        userData.setMobile(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_MOBILE));
                                        userData.setGender(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_GENDER));
                                        userData.setImgUrl(jsonUserDetails.getString(WebField.LOGIN_USER.RESPONSE_PROFILE_IMAGE));

                                        SessionManager.saveUserData(MainActivity.this, userData);


                                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);

                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Password is not Correct
                            if (jsonObject != null) {
                                try {

                                    if (jsonObject.getString("message").equalsIgnoreCase("Invalid user name or password")) {
                                        GlobalMethod.showAlert(MainActivity.this, "Invalid user name or password");
                                    } else if (!jsonObject.getString("message").equalsIgnoreCase("")) {
                                        GlobalMethod.showAlert(MainActivity.this, "Invalid credentials");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).execute();
            } else {
                AlertDialogUtility.showInternetAlert(MainActivity.this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGCMPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences,
        // but
        // how you store the regID in your app is up to you.
        return getApplicationContext().getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }



    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(MainActivity.this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, MainActivity.this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                //finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case MotionEvent.ACTION_UP:
                edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
        return true;
    }
}

