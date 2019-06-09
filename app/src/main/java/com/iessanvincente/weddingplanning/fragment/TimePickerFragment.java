package com.iessanvincente.weddingplanning.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.iessanvincente.weddingplanning.R;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerFragment extends DialogFragment {

	private TimePickerDialog.OnTimeSetListener listener;
	private static Long defaultDatetime;

	public static TimePickerFragment newInstance( TimePickerDialog.OnTimeSetListener listener, Long defaultDatetime ) {

		TimePickerFragment fragment = new TimePickerFragment();
		setDefaultDatetime( defaultDatetime );
		fragment.setListener( listener );
		return fragment;
	}

	private static void setDefaultDatetime( Long datetime ){
		defaultDatetime = datetime;
	}

	private void setListener( TimePickerDialog.OnTimeSetListener listener ) {
		this.listener = listener;
	}

	@Override
	@NonNull
	public Dialog onCreateDialog( Bundle savedInstanceState ) {
		Locale.setDefault( new Locale( "es", "ES" ) );
		// Use the current date as the default date in the picker
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( defaultDatetime * 1000 );
		int hour = calendar.get( Calendar.HOUR_OF_DAY );
		int minutes = calendar.get( Calendar.MINUTE );
		// Create a new instance of DatePickerDialog and return it
		return new TimePickerDialog( getActivity(), R.style.AppTheme_Light_Datepicker, listener, hour, minutes, true );
	}
}
