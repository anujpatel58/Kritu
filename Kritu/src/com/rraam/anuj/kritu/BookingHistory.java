package com.rraam.anuj.kritu;

import java.net.URI;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BookingHistory extends ActionBarActivity {
	JSONArray jArray;
	JSONObject jObj;
	String user_name;
	String MainUrl="http://10.20.200.226/Kritu/index.php?opn=bookings_done";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking_history);
		Intent i=getIntent();
		String jsonString=i.getExtras().getString("JSON");
        try {
			jObj= new JSONObject(jsonString);
			user_name=jObj.getJSONObject("user_data").getString("user_id");
            String web = MainUrl + "&user="+ user_name;
            new LoadStuff().execute(web);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        
		
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
            if (data != null) {/*
            	try {
					jArray= new JSONArray(data);
					LinearLayout layVer=(LinearLayout) findViewById(R.id.layout_vertical);
					for(int i=0;i<jArray.length();i++)
					{						
						JSONObject jO=jArray.getJSONObject(i);
				        LinearLayout layHor =new LinearLayout(getApplicationContext());
				        TextView date_logo=new TextView(getApplicationContext());
				        TextView drop_logo=new TextView(getApplicationContext());
				        TextView pickup_logo=new TextView(getApplicationContext());
				        TextView date_location=new TextView(getApplicationContext());
				        TextView pickup_location=new TextView(getApplicationContext());
				        TextView drop_location=new TextView(getApplicationContext());
				        RatingBar ratingBar=new RatingBar(getApplicationContext());
				        layHor.addView(date_logo);
				        layHor.addView(pickup_logo);
				        layHor.addView(drop_logo);
				        layHor.addView(date_location);
				        layHor.addView(pickup_location);
				        layHor.addView(drop_location);
				        layHor.addView(ratingBar);
				        {
				        	Booking_id: "1",
				        	Vehicle_No: "KA AE 1111",
				        	Driver_id: "DR1",
				        	User_id: "Us1",
				        	PickUp_Lat: "12.9667",
				        	PickUp_Long: "77.5667",
				        	Drop_Lat: "12.9562",
				        	Drop_Long: "77.7019",
				        	PickUp_Time: "10:30",
				        	PickUp_Date: "8/08/2015",
				        	Booking_time: "10:00",
				        	Booking_Date: "8/08/2015"
				        	},

				        layHor.addView(layVer);
				        date_logo.setText(jO.getString("PickUp_Date"));
				        drop_logo.setText(jO.getString("Drop_Lat"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
          */} else {
                Toast.makeText(getApplicationContext(), "Some Problem Occurred", Toast.LENGTH_LONG).show();
            }
        }
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.booking_history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
