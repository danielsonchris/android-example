package com.danielson.android_example.libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpHelper {

	private static final String TAG = "HttpHelper";
	
	public static String doGetRequest(String uri) {
		String ret = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(new HttpGet(uri));
			InputStream inputStream = response.getEntity().getContent();
			//Convert the input stream to a String
			ret = convertInputStreamToString(inputStream);
			
		} catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
		return ret;
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		StringBuilder sb = new StringBuilder();
		
		while ((line = reader.readLine()) != null)
			sb.append(line);
		
		return sb.toString();
	}
	
}
