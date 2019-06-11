package com.iessanvincente.weddingplanning.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.iessanvincente.weddingplanning.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author Jose J. Pardines
 */
public class DatePickerFragment extends DialogFragment {

	private static Long defaultDatetime;
	private DatePickerDialog.OnDateSetListener listener;

	public static DatePickerFragment newInstance( DatePickerDialog.OnDateSetListener listener, Long defaultDatetime ) {

		DatePickerFragment fragment = new DatePickerFragment();
		setDefaultDatetime( defaultDatetime );
		fragment.setListener( listener );
		return fragment;
	}

	private static void setDefaultDatetime( Long datetime ) {
		defaultDatetime = datetime;
	}

	private void setListener( DatePickerDialog.OnDateSetListener listener ) {
		this.listener = listener;
	}

	@Override
	@NonNull
	public Dialog onCreateDialog( Bundle savedInstanceState ) {
		Locale.setDefault( new Locale( "es", "ES" ) );
		// Use the current date as the default date in the picker
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( defaultDatetime * 1000 );
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH );
		int day = calendar.get( Calendar.DAY_OF_MONTH );

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog( getActivity(), R.style.AppTheme_Light_Datepicker, listener, year, month, day );
	}
}
