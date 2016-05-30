package com.mvcstyle.WebService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.mvcstyle.AppUtils.AlertDialogUtility;
import com.mvcstyle.AppUtils.ConnectivityDetector;

import org.json.JSONObject;



public class GetJsonWithCallBack extends
        AsyncTask<String, JSONObject, JSONObject> {

    private OnUpdateListener onUpdateListener;
    private Context context;
    // private ProgressDialog progressDialog;
    private JSONObject jsonObject;
    private int intDialogShow = 0;
    String strApiMode;
    private AlertDialog progressDialog;

    public GetJsonWithCallBack(Context context, JSONObject jsonObject,
                               int intDialogShow, String strApiMode, OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
        this.jsonObject = jsonObject;
        this.strApiMode = strApiMode;
        this.intDialogShow = intDialogShow;
        this.context = context;
    }


//    public GetJsonWithCallBack(Context context,
//                               int intDialogShow, String strApiMode, OnUpdateListener onUpdateListener) {
//        this.onUpdateListener = onUpdateListener;
//        this.strApiMode = strApiMode;
//        this.intDialogShow = intDialogShow;
//        this.context = context;
//    }

    @Override
    protected void onPreExecute() {
        if (!ConnectivityDetector.isConnectingToInternet(context)) {
            AlertDialogUtility.showInternetAlert(context);
            return;
        }
        super.onPreExecute();

        // progressDialog = new SpotsDialog(context, R.style.Custom);
        //intDialogShow == 1 for Get Method intDialogShow == 2 for POST method
        if (intDialogShow == 1 || intDialogShow == 2) {
            progressDialog = new ProgressDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            progressDialog.setMessage("Please Wait...");
            //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
//			progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
        }

    }

    @Override
    protected JSONObject doInBackground(String... param) {
        JSONObject getJsonObject = null;
        try {
            JSONParser jsonParser = new JSONParser();
            getJsonObject = jsonParser.getJSONFromUrl(WebField.BASE_IP + strApiMode,
                    jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getJsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonResult) {
        super.onPostExecute(jsonResult);
        try {
            if (jsonResult != null) {
                if (jsonResult.getString("status").equals("1")) {

                onUpdateListener.onUpdateComplete(jsonResult, true);


                } else {
                onUpdateListener.onUpdateComplete(jsonResult, false);

                	}
            } else {
                onUpdateListener.onUpdateComplete(jsonResult, false);

            }
            if (intDialogShow == 1 || intDialogShow == 2) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            onUpdateListener.onUpdateComplete(jsonResult, false);
            e.printStackTrace();
        }
    }
}