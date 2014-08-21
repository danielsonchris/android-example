package com.danielson.android_example;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

	private final static String TAG = "PlaceholderFragment";
	
	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		
		Button btnNextFrag = (Button)rootView.findViewById(R.id.button_detail_fragment);
		
		btnNextFrag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Log.d(TAG, "onclicked...");
				
				Fragment fragment = (Fragment)new DetailFragment();
				
				FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
				fragmentTransaction.replace(R.id.container, fragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				fragmentTransaction.commit();

			}
		});
		
		return rootView;
	}
}
