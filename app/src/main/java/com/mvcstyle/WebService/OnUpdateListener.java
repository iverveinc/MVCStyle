package com.mvcstyle.WebService;

import org.json.JSONObject;

public interface OnUpdateListener {
	void onUpdateComplete(JSONObject jsonObject, boolean isSuccess);
	
}
