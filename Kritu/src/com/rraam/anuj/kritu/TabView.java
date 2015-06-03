package com.rraam.anuj.kritu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TabView extends TabActivity {

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.tabview);
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		TabSpec firstTabSpec = tabHost.newTabSpec("map");
		TabSpec SecondTabSpec = tabHost.newTabSpec("opt");
		TabSpec ThirdTabSpec = tabHost.newTabSpec("sch");
		//TabSpec FourthTabSpec = tabHost.newTabSpec("his");
		Intent i = getIntent();
		try {
			firstTabSpec.setIndicator("Map").setContent(new Intent(this, UserHome.class).putExtras(i.getExtras()));
			SecondTabSpec.setIndicator("Option").setContent(new Intent(this, OtherOption.class).putExtras(i.getExtras()));
			ThirdTabSpec.setIndicator("Sch. Booking").setContent(new Intent(this, CabScheduling.class).putExtras(i.getExtras()));
			//FourthTabSpec.setIndicator("History").setContent(new Intent(this, BookingHistory.class).putExtras(i.getExtras()));

			tabHost.addTab(firstTabSpec);
			tabHost.addTab(SecondTabSpec);
			tabHost.addTab(ThirdTabSpec);
			//tabHost.addTab(FourthTabSpec);
			tabHost.setCurrentTab(0);
			tabHost.getTabWidget().getChildAt(0).getLayoutParams().width = (int) getWindowManager().getDefaultDisplay().getWidth() / 4 * 1;
			tabHost.getTabWidget().getChildAt(1).getLayoutParams().width = (int) getWindowManager().getDefaultDisplay().getWidth() / 4 * 1;
			tabHost.getTabWidget().getChildAt(2).getLayoutParams().width = (int) getWindowManager().getDefaultDisplay().getWidth() / 4 * 1;
			//tabHost.getTabWidget().getChildAt(3).getLayoutParams().width = (int) getWindowManager().getDefaultDisplay().getWidth() / 4 * 1;
		} catch (Exception er) {
			Log.d("Error", er.toString());
			Toast.makeText(getApplicationContext(),
					"Some Problem- " + er.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
