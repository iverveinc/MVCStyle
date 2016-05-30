package com.mvcstyle.WebService;


import com.mvcstyle.AppUtils.Applog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;


public class JSONParser {
    InputStream is;
    JSONObject jObj;
    String json;

    public JSONParser() {
        is = null;
        jObj = null;
    }

    public JSONObject getJSONFromUrl(String url_, JSONObject jsonObject) {
        try {
            URLConnection urlConn;
            DataOutputStream printout;

            URL url = new URL(url_);
            urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setConnectTimeout(30000);
            urlConn.setReadTimeout(30000);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();

            Applog.e("Request : ", jsonObject + "");
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.writeBytes(jsonObject.toString());
            printout.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            printout.close();
            reader.close();
            json = sb.toString();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Applog.e("Response : ", jObj + "");
        return jObj;
    }
}


