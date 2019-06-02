package com.iessanvincente.weddingplanning.utils;

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

}
