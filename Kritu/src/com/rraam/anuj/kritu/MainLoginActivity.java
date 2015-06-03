package com.rraam.anuj.kritu;

import java.net.URI;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainLoginActivity extends ActionBarActivity implements LocationListener{
    private EditText ediText_email,ediText_password;
    private Button button_login;
    private Context mContext;
    boolean canGetLocation = false;
    Location location;
    double latitude;
    double longitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    protected LocationManager locationManager;
    private String user_name,password,MainUrl = "http://10.20.200.226/Kritu/index.php?opn=login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        mContext = MainLoginActivity.this;
        this.getLocation();
        ediText_email=(EditText) findViewById(R.id.editText_login_email);
        ediText_password=(EditText) findViewById(R.id.editText_login_password);
        button_login=(Button) findViewById(R.id.button);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name=ediText_email.getText().toString();
                password=ediText_password.getText().toString();
                if (user_name.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Username or Password is missing.", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        String web = MainUrl + "&user="+ user_name + "&pass=" + password + "&lat=" 
                        		+ latitude + "&long=" + longitude;
                        new LoadStuff().execute(web);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
	public Location getLocation() {
	    try {
	        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
	        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	        if (!isGPSEnabled && !isNetworkEnabled) {
	        } else {
	            this.canGetLocation = true;
	            if (isNetworkEnabled) {
	                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, MainLoginActivity.this);
	                if (locationManager != null) {
	                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                    if (location != null) {
	                        latitude = location.getLatitude();
	                        longitude = location.getLongitude();
	                    }
	                }
	            }
	            if (isGPSEnabled) {
	                if (location == null) {
	                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, MainLoginActivity.this);
	                    if (locationManager != null) {
	                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                        if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                        }
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        Log.i("ola", e.toString());
	    }
	    return location;
	}
	public double getLatitude(){
	    if(location != null){
	        latitude = location.getLatitude();
	    }
	    return latitude;
	}
	public double getLongitude(){
	    if(location != null){
	        longitude = location.getLongitude();
	    }
	    return longitude;
	}
	public boolean canGetLocation() {
	    return this.canGetLocation;
	}

    class LoadStuff extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... aurl) {
            try {
                GetHttpData tex = new GetHttpData();
                URI u = new URI(aurl[0]);
                return tex.getData(u).toString();
            }catch (Exception er) {
               return "error";
            }
        }        
        @Override
        protected void onPostExecute(String data) {
            if (data != null) {
                if (data.equals("0")) {
                    Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Intent intent = new Intent(MainLoginActivity.this,TabView.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Link", MainUrl);
                        bundle.putString("JSON", data);
                        bundle.putDouble("lat", latitude);
                        bundle.putDouble("long", longitude);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } catch (Exception er) {
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Some Problem Occurred", Toast.LENGTH_LONG).show();
            }
        }
    }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
