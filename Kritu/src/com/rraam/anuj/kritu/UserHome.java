package com.rraam.anuj.kritu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UserHome extends Activity {
	// Google Map
	private GoogleMap googleMap;
	double latitude;
	double longitude;
	JSONArray jArray;
	JSONObject jObj;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_home);		
		Intent i=getIntent();
		String jsonString=i.getExtras().getString("JSON");
		latitude=i.getExtras().getDouble("lat");
		longitude=i.getExtras().getDouble("long");
		try {
			JSONObject json=new JSONObject(jsonString);
			jArray=json.getJSONArray("vehicles");
			// TODO : 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Display dis = getWindowManager().getDefaultDisplay();
		LinearLayout ll0 = new LinearLayout(getApplicationContext());
		ll0.setOrientation(LinearLayout.HORIZONTAL);
		ll0.setBackgroundColor(Color.DKGRAY);
		Button btn = new Button(getApplicationContext());
		btn.setText("Done");
		btn.setGravity(Gravity.CENTER);
		btn.setWidth(dis.getWidth());
		btn.setTextColor(Color.WHITE);
		ll0.addView(btn);

		try {
			initilizeMap();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error- " + e,
					Toast.LENGTH_SHORT).show();
		}
	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			LatLng vehicle ;
			for(int j=0;j<jArray.length();j++){
				try{
					jObj=jArray.getJSONObject(j);
					vehicle = new LatLng(Double.parseDouble(jObj.getString("tracking_lat")), Double.parseDouble(jObj.getString("tracking_long")));
					googleMap.addMarker(new MarkerOptions().position(vehicle) .title("5 min, 4 Star"));
				} catch (JSONException e) {
					Log.i("ola",e.toString());
				}
			}
			CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(21).build();
			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			googleMap.getUiSettings().setZoomControlsEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setRotateGesturesEnabled(true);
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
			googleMap.setMyLocationEnabled(true);
			
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
