package com.rraam.anuj.kritu;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class CabScheduling extends Activity {
	Button btnChangeDate;
	int year;
	int month;
	int day;
	int hour;
	int minute;
	static final int TIME_DIALOG_ID = 999;
	static final int DATE_DIALOG_ID = 998;
	EditText ed1, ed2;
	ImageView im1, im2, im3, im4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cab_scheduling);
		ed1 = (EditText) findViewById(R.id.date);
		ed2 = (EditText) findViewById(R.id.time);
		im1 = (ImageView) findViewById(R.id.dateim);
		im2 = (ImageView) findViewById(R.id.timeim);
		im3 = (ImageView) findViewById(R.id.mapim1);
		im4 = (ImageView) findViewById(R.id.mapim2);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		BitmapDrawable bmap = (BitmapDrawable) this.getResources().getDrawable(
				R.drawable.main);
		float bmapWidth = bmap.getBitmap().getWidth();
		float bmapHeight = bmap.getBitmap().getHeight();
		float wRatio = width / bmapWidth;
		float hRatio = height / bmapHeight;
		float ratioMultiplier = wRatio;
		if (hRatio < wRatio) {
			ratioMultiplier = hRatio;
		}
		int newBmapWidth = (int) (bmapWidth * ratioMultiplier);
		int newBmapHeight = (int) (bmapHeight * ratioMultiplier);
		ImageView iView = (ImageView) findViewById(R.id.iv_background);
		iView.setLayoutParams(new LinearLayout.LayoutParams(newBmapWidth,
				newBmapHeight));
		im3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {}
		});
		im4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {}
		});
		ed1.setEnabled(false);
		setCurrentDateOnView(ed1);
		im1.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
		ed2.setEnabled(false);
		setCurrentTimeOnView(ed2);
		im2.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});
	}

	public void setCurrentDateOnView(EditText ed) {
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		ed.setText(new StringBuilder().append(month + 1).append("/")
				.append(day).append("/").append(year).append(" "));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);

		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			ed1.setText(new StringBuilder().append(month + 1).append("/")
					.append(day).append("/").append(year).append(" "));
		}
	};

	public void setCurrentTimeOnView(EditText ed) {

		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		ed.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));

	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			ed2.setText(new StringBuilder().append(pad(hour)).append(":")
					.append(pad(minute)));

		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}