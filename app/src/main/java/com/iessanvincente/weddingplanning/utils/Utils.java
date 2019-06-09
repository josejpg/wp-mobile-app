package com.iessanvincente.weddingplanning.utils;

import android.widget.EditText;

import androidx.fragment.app.FragmentManager;

import com.iessanvincente.weddingplanning.fragment.DatePickerFragment;
import com.iessanvincente.weddingplanning.fragment.TimePickerFragment;

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
	public static void showDatePickerDialog( EditText editTextTarget, FragmentManager fragmentManager, Long defaultDate, Boolean isDateTimePicker ) {
		DatePickerFragment newFragment = DatePickerFragment.newInstance( ( datePicker, year, month, day ) -> {
			final String selectedDate = padNumber( day ) + "/" + padNumber( month + 1 ) + "/" + year;

			if ( !isDateTimePicker ) {
				editTextTarget.setText( selectedDate );
			} else {
				showTimePickerDialog( editTextTarget, fragmentManager, defaultDate, selectedDate );
			}
		}, defaultDate );
		newFragment.show( fragmentManager, "datePicker" );
	}

	/**
	 * Show a dialog with a date picker
	 *
	 * @param editTextTarget  element target
	 * @param fragmentManager context
	 */
	public static void showTimePickerDialog( EditText editTextTarget, FragmentManager fragmentManager, Long defaultDate, String selectedDate ) {
		TimePickerFragment newFragment = TimePickerFragment.newInstance( ( datePicker, hour, minutes ) -> {
			// +1 because january is zero
			final String selectedTime = padNumber( hour ) + ":" + padNumber( minutes );
			if ( selectedDate == null || selectedDate.equals( "" ) ) {
				editTextTarget.setText( selectedTime );
			} else {
				editTextTarget.setText( ( selectedDate + " " + selectedTime ) );
			}

		}, defaultDate );
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
