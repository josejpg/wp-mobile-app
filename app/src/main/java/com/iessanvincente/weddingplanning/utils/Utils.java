package com.iessanvincente.weddingplanning.utils;

import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.iessanvincente.weddingplanning.R;
import com.iessanvincente.weddingplanning.fragment.DatePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

	/**
	 * Get a epoch and transform into a string datetime
	 *
	 * @param time epoch
	 * @return string with formated datetime
	 */
	public static String getDateTimeAsString( Long time ) {
		if ( time == null ) {
			time = Instant.now().getEpochSecond();
		}
		TimeZone tz = TimeZone.getDefault();//get your local time zone.
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm", Locale.ENGLISH );
		sdf.setTimeZone( tz );//set time zone.
		return sdf.format( time * 1000 );
	}

	/**
	 * Get a epoch and transform into a string date
	 *
	 * @param time epoch
	 * @return string with formated date
	 */
	public static String getDateAsString( Long time ) {
		if ( time == null ) {
			time = Instant.now().getEpochSecond();
		}
		TimeZone tz = TimeZone.getDefault();//get your local time zone.
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy", Locale.ENGLISH );
		sdf.setTimeZone( tz );//set time zone.
		return sdf.format( time * 1000 );
	}

	/**
	 * Get a string datetime and transform into a epoch
	 *
	 * @param timestamp datetime dd/MM/yyyy hh:mm
	 * @return string with formated date
	 */
	public static Long getTimeFromDateTime( String timestamp ) {
		if ( timestamp == null ) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );
			Date date = sdf.parse( timestamp );
			return ( date.getTime() / 1000 );
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Get a string date/datetime and transform into a timestamp
	 *
	 * @param timestamp date dd/MM/yyyy
	 * @return string with formated date
	 */
	public static Long getTimeFromDate( String timestamp ) {
		if ( timestamp == null ) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
			Date date = sdf.parse( timestamp );
			return ( date.getTime() / 1000 );
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Show a dialog with a date picker
	 *
	 * @param editTextTarget  element target
	 * @param fragmentManager context
	 */
	public static void showDatePickerDialog( EditText editTextTarget, FragmentManager fragmentManager, String defaultDate ) {
		DatePickerFragment newFragment = DatePickerFragment.newInstance( ( datePicker, year, month, day ) -> {
			// +1 because january is zero
			final String selectedDate = padNumber( day ) + " / " + padNumber( month + 1 ) + " / " + year;
			editTextTarget.setText( selectedDate );
		} );
		newFragment.setDate( defaultDate );
		newFragment.show( fragmentManager, "datePicker" );
	}

	/**
	 * Format number to 2 digist
	 *
	 * @param number to transform
	 * @return string with format number
	 */
	private static String padNumber( Integer number ) {
		return ( number <= 9 ) ? ( "0" + number ) : String.valueOf( number );
	}
}
