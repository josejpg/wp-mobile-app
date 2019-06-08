package com.iessanvincente.weddingplanning.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.iessanvincente.weddingplanning.R;

import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment {

	private DatePickerDialog.OnDateSetListener listener;
	private String day;
	private String month;
	private String year;

	public static DatePickerFragment newInstance( DatePickerDialog.OnDateSetListener listener ) {

		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setListener( listener );
		return fragment;
	}

	public void setDate( String date ) {
		if ( date != null && date.equals( "" ) ) {
			String[] splitDate = date.split( "/" );
			setDay( splitDate[ 0 ] );
			setMonth( splitDate[ 1 ] );
			setYear( splitDate[ 2 ] );
		}
	}

	private void setDay( String day ) {
		this.day = day;
	}

	private void setMonth( String month ) {
		this.month = month;
	}

	private void setYear( String year ) {
		this.day = year;
	}

	private void setListener( DatePickerDialog.OnDateSetListener listener ) {
		this.listener = listener;
	}

	@Override
	@NonNull
	public Dialog onCreateDialog( Bundle savedInstanceState ) {
		Locale.setDefault(  new Locale("es", "ES") );
		// Use the current date as the default date in the picker
		final Calendar calendar = Calendar.getInstance();
		int year = calendar.get( ( this.year != null && !this.year.equals( "" ) ) ? Integer.parseInt( this.year ) : Calendar.YEAR );
		int month = calendar.get( ( this.month != null && !this.month.equals( "" ) ) ? Integer.parseInt( this.month ) : Calendar.MONTH );
		int day = calendar.get( ( this.day != null && !this.day.equals( "" ) ) ? Integer.parseInt( this.day ) : Calendar.DAY_OF_MONTH );

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog( getActivity(), R.style.AppTheme_Light_Datepicker, listener, year, month, day );
	}
}
