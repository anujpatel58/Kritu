package com.rraam.anuj.kritu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class OtherOption extends ActionBarActivity {
	double latitude;
	double longitude;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_option);
		Intent i=getIntent();
		latitude=i.getExtras().getDouble("lat");
		longitude=i.getExtras().getDouble("long");	
	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

	ImageButton mShare = (ImageButton) findViewById(R.id.mShare);
	mShare.setOnClickListener(new OnClickListener() {	    
	    @Override
	    public void onClick(View v) {
	    	Intent sendIntent = new Intent();
	        sendIntent.setAction(Intent.ACTION_SEND);
	        sendIntent.putExtra(Intent.EXTRA_TEXT, "Tracking Location \n"+"Latitude= " + latitude + "\nLongitude =" + longitude);
	        sendIntent.setType("text/plain");
	        startActivity(sendIntent);
	    }
	});
	ImageButton mSms = (ImageButton) findViewById(R.id.mSms);
	mSms.setOnClickListener(new OnClickListener() {	    
	    @Override
	    public void onClick(View v) {
	        final String num="8904656565";
	        alertbox.setCancelable(true);
	        alertbox.setTitle("Kritu");
	        alertbox.setInverseBackgroundForced(true);
	        alertbox.setMessage("Do You Want To Call to Emergency No. with Tracking Location?");
	        alertbox.setPositiveButton("Send", new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface arg0, int arg1) {
                        SmsManager sms= SmsManager.getDefault();
                        sms.sendTextMessage(num,null,"Help Me.\nMy Tracking Location \n"+"Latitude= " + latitude + "\nLongitude =" + longitude , null, null);
                        //finish();
	                    }
	                });
	        alertbox.setNegativeButton("Don't Send", new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface arg0, int arg1) {
	                    }
	                });
	        alertbox.show();
	    }
	});
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.other_option, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
