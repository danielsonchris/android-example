package com.danielson.android_example;

import java.lang.ref.WeakReference;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.danielson.android_example.libs.GeneralAsyncTask;
import com.danielson.android_example.libs.HttpHelper;

public class ExampleAsyncRestCall extends GeneralAsyncTask<String> {

	private final static String TAG = "ExampleAsyncRestCall";
	
	private WeakReference<Context> context;
	
	public ExampleAsyncRestCall(WeakReference<Context> context)
	{
		this.context = context;
	}
	
	@Override
	protected String doSafelyInBackground(Void... params) throws Exception {
		return HttpHelper.doGetRequest("http://hmkcode.appspot.com/rest/controller/get.json");
	}

	@Override
	protected void onCompletedWithException(Exception ex) {
		Context ctx = context.get();
		if (ctx != null)
			Toast.makeText(context.get(), ex.getMessage(), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCompletedWithResult(String result) {
		//Take the final string value here and apply it to our RESTful Parser
		Context ctx = context.get();
		if (result == null && ctx != null) {
			Toast.makeText(ctx, "No data was returned from the RESTful API", Toast.LENGTH_SHORT).show();
			return;
		}
		
		try {
			//Parse the RESTful call here.
			JSONTokener token = new JSONTokener(result);
		
			JSONObject jo = (JSONObject)token.nextValue();
			
			if (jo == null) 
				Toast.makeText(ctx, "Unable to parse RESTful data, JSONObject was null", Toast.LENGTH_SHORT).show();
		
			JSONArray ja = jo.getJSONArray("articleList");
			
			if (ja == null) return;
			
			int lenJA = ja.length();
			for (int i=0; i < lenJA; i++) {
				JSONObject tmp = ja.getJSONObject(i);
				
				String sTmp = tmp.getString("title");
				if (sTmp != null)
					Log.d(TAG, "Title=" + sTmp.trim());
				sTmp = tmp.getString("url");
				if (sTmp != null) 
					Log.d(TAG, "URL=" + sTmp.trim());
			}
			
		} catch (Exception ex) {
			Toast.makeText(ctx, "Exception parsing RESTful Data " + ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
}
