package com.danielson.android_example.libs;

import android.os.AsyncTask;

public abstract class GeneralAsyncTask<B> extends AsyncTask<Void, Void, GeneralAsyncTask.Result<B>> {

	static class Result<T>
	{
		public Exception exc;
		public T result;
	}
	
	protected abstract B doSafelyInBackground(Void... params) throws Exception;
	
	@Override
	protected Result<B> doInBackground(Void... params) {
		Result<B> result = new Result<B>();
		try {
			result.result = doSafelyInBackground(params);
		} catch (Exception ex) {
			result.exc = ex;
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(Result<B> result) {
		if (result.exc != null)
			onCompletedWithException(result.exc);
		else 
			onCompletedWithResult(result.result);
	}
	
	@Override
	protected void onCancelled(Result<B> result) {
		onCancel(result);
	}
	
	protected void onCancel(Result<B> result) {
	}
	
	protected void onCompletedWithException(Exception ex) {
	}
	
	protected void onCompletedWithResult(B result) {
	}
}
