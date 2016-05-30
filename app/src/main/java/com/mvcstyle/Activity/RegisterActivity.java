package com.mvcstyle.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;


public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText edtFirstname, edtLastname, edtMobile, edtUsername, edtEmailSignUp, edtPassSignUp, edtAddress;
    private TextView txtGender, tvSubmit, tvFemale, tvMale;
    private Button btnSubmit;
    static private RelativeLayout rlMale, rlFemale;
    private ImageView ivProfilePic, ivBack;
    private RadioGroup radioSexGroup;
    private RadioButton rb;
    private String strProPicBase64 = "";
    private Uri mImageUri;
    private Bitmap bitmap = null;
    public static String regId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.sel_text));
        }

        getLatLong();
        getIds();
        setTypeFace();
//        getLatLong();
        setListeners();

    }

    private void getIds() {
        try {
            edtFirstname = (EditText) findViewById(R.id.edtFirstname);
            edtLastname = (EditText) findViewById(R.id.edtLastname);
            edtMobile = (EditText) findViewById(R.id.edtMobile);
            edtUsername = (EditText) findViewById(R.id.edtUsername);
            edtEmailSignUp = (EditText) findViewById(R.id.edtEmailSignUp);
            edtPassSignUp = (EditText) findViewById(R.id.edtPassSignUp);
//            edtConfPassSignUp = (EditText) findViewById(R.id.edtConfPassSignUp);
            edtAddress = (EditText) findViewById(R.id.edtAddress);
            ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
            txtGender = (TextView) findViewById(R.id.txtGender);
            btnSubmit = (Button) findViewById(R.id.btnSubmit);
            ivBack = (ImageView)findViewById(R.id.ivBack);
            rb = (RadioButton) findViewById(R.id.radioMale);
            radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setTypeFace() {
        try {
            edtFirstname.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            edtLastname.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            edtMobile.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            edtUsername.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            edtEmailSignUp.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            edtPassSignUp.setTypeface(Fonts.robotRegular(RegisterActivity.this));
//            edtConfPassSignUp.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            edtAddress.setTypeface(Fonts.robotRegular(RegisterActivity.this));

            txtGender.setTypeface(Fonts.robotRegular(RegisterActivity.this));
            btnSubmit.setTypeface(Fonts.robotRegular(RegisterActivity.this));

            InputMethodManager imm = (InputMethodManager) RegisterActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {

        btnSubmit.setOnClickListener(this);
        ivProfilePic.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        radioSexGroup.performClick();
        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioMale:
                        rb = (RadioButton) findViewById(checkedId);
                        break;
                    case R.id.radioFemale:
                        rb = (RadioButton) findViewById(checkedId);
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSubmit:
               checkValues();
                break;

            case R.id.ivProfilePic:
                showDialog();
                break;

            case R.id.ivBack:
                Intent intentReg = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intentReg);
                break;

        }

    }

    private void showDialog() {
        Applog.e("pick image", "");
        final String[] items = new String[]{"Choose existing", "Take photo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
                R.layout.custom_layout, items);

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                if (item == 0) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,
                            "Select Picture"), 99);
                    // startActivityForResult(intent, 99);
                } else {
                    camera();
                }
            }
        });

        final AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void camera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 0);
    }

    private void checkValues() {

        try {
            Log.e("Lati ::::::: ", "" + SessionManager.getlongitude(RegisterActivity.this) + " " + SessionManager.getlattitude(RegisterActivity.this));
            String strFirstname = edtFirstname.getText().toString().trim();
            String strLastname = edtLastname.getText().toString().trim();
            String strMobile = edtMobile.getText().toString().trim();
            String strUsername = edtUsername.getText().toString().trim();
            String strEmail = edtEmailSignUp.getText().toString().trim();
            String strPassword = edtPassSignUp.getText().toString().trim();
//            String strConfPassword = edtConfPassSignUp.getText().toString().trim();
            String strAddress = edtAddress.getText().toString().trim();

            if (strFirstname.isEmpty()) {
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter first name");
                //showCallbacks("Enter First Name");
                edtFirstname.requestFocus();
            } else if (strLastname.isEmpty()) {
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter last name");
                //showCallbacks("Enter Last Name");
                edtLastname.requestFocus();
            } else if (strMobile.isEmpty()) {
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter mobile number");
                //  showCallbacks("Enter Mobile");
                edtMobile.requestFocus();
            } else if (strMobile.length() < 10) {
//                showCallbacks("Password must contains atleast 8 characters");
                GlobalMethod.showAlert(RegisterActivity.this,
                        "Mobile number should be of minimum 10 digits");
                edtMobile.requestFocus();
            } else if (strUsername.isEmpty()) {
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter user name");
                //  showCallbacks("Enter Username");
                edtUsername.requestFocus();
            } else if (strEmail.isEmpty()) {
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter email id");
                // showCallbacks("Enter Email");
                edtEmailSignUp.requestFocus();
            } else if (!GlobalMethod.isValidEmail(strEmail)) {
                GlobalMethod.showAlert(RegisterActivity.this, "Email id is not valid");
                // showCallbacks("Email is not valid");
                edtEmailSignUp.requestFocus();
            } else if (strPassword.isEmpty()) {
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter password");
//                showCallbacks("Enter Confirm Email");
                edtPassSignUp.requestFocus();
            } else if (strPassword.length() < 6) {
//                showCallbacks("Password must contains atleast 8 characters");
                GlobalMethod.showAlert(RegisterActivity.this,
                        "Password should be of minimum 6 characters");
                edtPassSignUp.requestFocus();
            }
            else if (strAddress.isEmpty()) {
                //showCallbacks("Enter Password");
                GlobalMethod.showAlert(RegisterActivity.this, "Please enter address");
                edtAddress.requestFocus();
            } else {
                callSignUpService();
            }
        } catch (Exception e) {
                e.printStackTrace(); }

    }

    private void getLatLong() {
        try {
            Applog.e("LATTTTTTTT", "" + SessionManager.getlattitude(getApplicationContext()) + "  " + SessionManager.getlongitude(getApplicationContext()));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    private void callSignUpService() {
        try {
            if (ConnectivityDetector.isConnectingToInternet(RegisterActivity.this)) {
                JSONObject jsonObjectInput = new JSONObject();


                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_USER_NAME,
                        edtUsername.getText().toString());
                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_FIRST_NAME,
                        edtFirstname.getText().toString());
                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_LAST_NAME,
                        edtLastname.getText().toString());
                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_EMAIL,
                        edtEmailSignUp.getText().toString().trim());
                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_ADDRESS,
                        edtAddress.getText().toString());
                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_PASSWORD,
                        edtPassSignUp.getText().toString());
                jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_MOBILE,
                        edtMobile.getText().toString());

                if (rb.getText().toString().equalsIgnoreCase("Male")) {
                    jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_GENDER,
                            0);
                } else {
                    jsonObjectInput.put(WebField.REGISTER_USER.REQUEST_GENDER,
                            1);
                }

                jsonObjectInput.put(
                        WebField.REGISTER_USER.REQUEST_LONGITUDE,
                        SessionManager.getlongitude(RegisterActivity.this));
                jsonObjectInput.put(
                        WebField.REGISTER_USER.REQUEST_LATITUDE,
                        SessionManager.getlattitude(RegisterActivity.this));


                if (strProPicBase64 == null || strProPicBase64.isEmpty()) {


                    Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.signup_profile);
                    strProPicBase64 = getEncoded64ImageStringFromBitmap(largeIcon);
                    Log.e("Null Profile", "Null Profile");
                }

                jsonObjectInput.put(
                        WebField.REGISTER_USER.REQUEST_PROFILE_PIC,
                        strProPicBase64);

                String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);

                jsonObjectInput.put(
                        WebField.REGISTER_USER.REQUEST_DEVICE_ID,
                        android_id);
                jsonObjectInput.put(
                        WebField.REGISTER_USER.REQUEST_DEVICE_TYPE,
                        "0");
                jsonObjectInput.put(
                        WebField.REGISTER_USER.REQUEST_USER_TYPE,
                        1);
                String mode = "RegisterUser";
                Applog.e("Json Req", "" + jsonObjectInput);
                new GetJsonWithCallBack(RegisterActivity.this, jsonObjectInput,
                        1, mode, new OnUpdateListener() {
                    @Override
                    public void onUpdateComplete(JSONObject jsonObject,
                                                 boolean isSuccess) {
                        if (isSuccess) {
                            try {
                                if (jsonObject != null) {
                                    if (jsonObject.has("userDetail")) {
                                        JSONObject jsonUserDetails = jsonObject.getJSONObject("userDetail");

                                        UserData userData = new UserData();
                                        userData.setUserName(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_USER_NAME));
                                        userData.setFirstname(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_FIRST_NAME));
                                        userData.setLastname(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_LAST_NAME));
                                        userData.setUserEmail(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_EMAIL_ID));
                                        userData.setAddress(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_ADDRESS));
                                        userData.setMobile(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_MOBILE_NUMBER));
                                        userData.setGender(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_GENDER));
                                        userData.setImgUrl(jsonUserDetails.getString(WebField.REGISTER_USER.RESPONSE_PROFILE_IMAGE));


                                        Log.i("prfl pic", "profile_pic======" + WebField.REGISTER_USER.RESPONSE_PROFILE_IMAGE);
                                        SessionManager.saveUserData(RegisterActivity.this, userData);
                                        Log.e("","========== "+userData );

                                        Applog.e("Sign Up ", "" + edtUsername.getText() + " " + edtPassSignUp.getText());

                                        SessionManager.saveUserDetail(RegisterActivity.this, edtUsername.getText().toString().trim(), edtPassSignUp.getText().toString().trim(), true);


                                        Intent i = new Intent(RegisterActivity.this, ProfileActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);

                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (jsonObject != null) {
                                try {
                                    String Status = jsonObject.getString("status");

                                    Log.e("Status", "Status  ::" + Status);

                                    String message = jsonObject.getString("message");

                                    Log.e("message", "Status  ::" + message);

                                    if(message.equalsIgnoreCase("User already Exists")){
                                        GlobalMethod.showAlert(RegisterActivity.this, "User name already exists");

                                    }else {
                                        GlobalMethod.showAlert(RegisterActivity.this, jsonObject.getString("message"));
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).execute();
            } else {
                AlertDialogUtility.showInternetAlert(RegisterActivity.this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imageString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imageString;
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                RegisterActivity.this.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RegisterActivity.this.RESULT_OK) {
            if (requestCode == 99) {
                mImageUri = data.getData();
                try {
                    bitmap = getBitmapFromUri(mImageUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ivProfilePic.setImageBitmap(bitmap);

                strProPicBase64 = encodeTobase64(bitmap);

            } else if (requestCode == 0) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ivProfilePic.setImageBitmap(photo);
                strProPicBase64 = encodeTobase64(photo);
            }
        }
    }


    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK++++++++++", imageEncoded);
        return imageEncoded;
    }

}
