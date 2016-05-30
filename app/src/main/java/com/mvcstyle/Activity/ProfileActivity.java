package com.mvcstyle.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
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

import org.json.JSONObject;

import java.util.List;
import java.util.UUID;


public class ProfileActivity extends Activity implements View.OnClickListener {

    private TextView tvHeader, tvHdFName, tvFName, tvHdLName, tvLName, tvHdMobile, tvMobile, tvHdUName, tvUName, tvHdEmail, tvEmail, tvHdGender, tvGender, tvHdAddress, tvAddress;
    private ImageView ivBack, ivProfilePic;
    private Button btnlogout;

    int idd = 0;
    private SharedPreferences prefs;
    private UserData userData;
    ProgressDialog mProgressDialog;
    String strMobile;
    String status;
    String title = "";
    int id;
    TextView latLongTV;
    double latitude = 0;
    double longitude = 0;
    Geocoder geocode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.sel_text));
        }
        geocode = new Geocoder(this);

        getId();
        setListeners();
        setTypeface();
        callService();

    }

    private void setTypeface() {
        try {
            tvHeader.setTypeface(Fonts.robotMedium(getApplicationContext()));

            tvHdFName.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvFName.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvHdLName.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvLName.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvHdMobile.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvMobile.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvHdUName.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvUName.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvHdEmail.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvEmail.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvHdGender.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvGender.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvHdAddress.setTypeface(Fonts.robotRegular(getApplicationContext()));
            tvAddress.setTypeface(Fonts.robotRegular(getApplicationContext()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        try {
            btnlogout.setOnClickListener(this);
            tvAddress.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getId() {
        try {
            tvHeader = (TextView) findViewById(R.id.tvHeader);
            btnlogout = (Button) findViewById(R.id.btnlogout);
            tvHdFName = (TextView) findViewById(R.id.tvHdFName);
            tvFName = (TextView) findViewById(R.id.tvFName);
            tvHdLName = (TextView) findViewById(R.id.tvHdLName);
            tvLName = (TextView) findViewById(R.id.tvLName);
            tvHdMobile = (TextView) findViewById(R.id.tvHdMobile);
            tvMobile = (TextView) findViewById(R.id.tvMobile);
            tvHdUName = (TextView) findViewById(R.id.tvHdUName);
            tvUName = (TextView) findViewById(R.id.tvUName);
            tvHdEmail = (TextView) findViewById(R.id.tvHdEmail);
            tvEmail = (TextView) findViewById(R.id.tvEmail);
            tvHdGender = (TextView) findViewById(R.id.tvHdGender);
            tvGender = (TextView) findViewById(R.id.tvGender);
            tvHdAddress = (TextView) findViewById(R.id.tvHdAddress);
            tvAddress = (TextView) findViewById(R.id.tvAddress);
//            ivBack = (ImageView) findViewById(R.id.ivBack);
            ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnlogout:
                showCallbacks("Are you sure you want to Logout?");
                break;

            case R.id.tvAddress:
                String addressName = tvAddress.getText().toString();
                String UserName = tvUName.getText().toString();

                Intent intentAddress = new Intent(ProfileActivity.this, MapActivity.class);
                intentAddress.putExtra("latitude", latitude);
                intentAddress.putExtra("longitude", longitude);
                intentAddress.putExtra("addressName", addressName);
                intentAddress.putExtra("UserName", UserName);
                Log.e("====addressName====","========" +addressName);
                Log.e("====latttt====","========" +latitude);
                Log.e("====lonnn====","========" +longitude);
                startActivity(intentAddress);
                break;
        }

    }


    private void showCallbacks(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this)
                .setTitle("MVC Style").setMessage(message);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (SessionManager.isLoginUser(getApplicationContext())) {

                    SessionManager.logOutUser(getApplicationContext(), false);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("OUTUSER", 1);

                    startActivity(intent);
                    finish();

                } else {

                    finish();


                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void callService() {
        try {
            if (ConnectivityDetector.isConnectingToInternet(getApplicationContext())) {
                JSONObject jsonObjectInput = new JSONObject();

                jsonObjectInput.put(WebField.GET_USER_DETAILS.REQUEST_MOBILE_NUMBER, SessionManager.getUserData(getApplication()).getMobile());//SessionManager.getUserData(getApplication()).getMobile()
                jsonObjectInput.put(WebField.GET_USER_DETAILS.REQUEST_USER_TYPE, 1);
                Applog.e("Request Mobile no", "::::::::" + SessionManager.getUserData(getApplication()).getMobile());
                String mode = "GetUserDetails";

                new GetJsonWithCallBack(ProfileActivity.this, jsonObjectInput,
                        1, mode, new OnUpdateListener() {
                    @Override
                    public void onUpdateComplete(JSONObject jsonObject,
                                                 boolean isSuccess) {
                        if (isSuccess) {
                            try {
                                if (jsonObject != null) {
                                    if (jsonObject.has("userDetail")) {
                                        JSONObject jobjProfessionalDetails = jsonObject.getJSONObject("userDetail");

                                        Log.i("jrryUserDetails", "================" + jobjProfessionalDetails);
                                        tvFName.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_FIRST_NAME));
                                        tvLName.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_LAST_NAME));
                                        tvMobile.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_MOBILE_NUMBER));
                                        tvUName.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_USER_NAME));
                                        tvEmail.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_EMAIL_ID));

                                        if (jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_GENDER).equalsIgnoreCase("0")) {
                                            tvGender.setText(R.string.txt_male);
                                        } else if (jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_GENDER).equalsIgnoreCase("1")) {
                                            tvGender.setText(R.string.txt_female);
                                        }

                                        if (tvAddress.toString().isEmpty()) {
                                            tvAddress.setText(R.string.txt_na);
                                        } else {
                                            tvAddress.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_ADDRESS));

                                        }
                                        String url = jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_PROFILE_IMAGE);

                                        if (url != null) {
                                            Glide.clear(ivProfilePic);
                                            Glide.with(getApplicationContext())
                                                    .load(url)
                                                    .centerCrop()
                                                    .signature(new StringSignature(UUID.randomUUID().toString()))
                                                    .crossFade().skipMemoryCache(true)
                                                    .into(ivProfilePic);


                                            geocodeAddress(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_ADDRESS), geocode);
                                            Log.e("====StrAdd======", "==========" + String.valueOf(tvAddress));

                                        } else {
                                            ivProfilePic.setImageResource(R.drawable.signup_profile);
                                        }
                                        tvHeader.setText(jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_USER_NAME));
                                        Applog.e("ivProfilePic", "<<<<<<<<<<<<" + jobjProfessionalDetails.getString(WebField.GET_USER_DETAILS.RESPONSE_PROFILE_IMAGE));


//                                        String address = "tvAddress";
//                                        geocodeAddress(address, geocode);

                                        // SessionManager.saveProfessionalData(getApplicationContext(), proData);
                                    } else {
                                        GlobalMethod.showAlert(getApplicationContext(), "User detail are empty");
                                    }

                                }
//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                        }
                    }
                }

                ).execute();
            } else {
                AlertDialogUtility.showInternetAlert(getApplicationContext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void geocodeAddress(String addressStr, Geocoder gc) {

        Address address = null;
        List<Address> addressList = null;
        try {
            if (!TextUtils.isEmpty(addressStr)) {
                Log.e("====addStr======", "==========" + addressStr);
                addressList = gc.getFromLocationName(addressStr, 5);
                Log.e("=====adList=====", "==========" + addressList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != addressList && addressList.size() > 0) {
            address = addressList.get(0);
        }


        if (null != address && address.hasLatitude()
                && address.hasLongitude()) {

            latitude = address.getLatitude();
            longitude = address.getLongitude();
            Log.e("=====la=====", "==========" + latitude);
            Log.e("====lo======", "==========" + longitude);
        }
    }


}
