package com.vecika.parking.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Vedran on 23.09.2017..
 */

public class UtilsMethods {

	public static boolean checkIfItsSummer(long date) throws ParseException {
		Date beginningOfSummer = new SimpleDateFormat("dd.MM.yyyy").parse("21.06." + Calendar.YEAR);
		Date endOdSummer = new SimpleDateFormat("dd:MM.yyyy").parse("23.09." + Calendar.YEAR);
		long begin = beginningOfSummer.getTime();
		long end = endOdSummer.getTime();

		return date < end && date > begin;

	}


	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


	public static Date parseDate(String dateToParse){
		try {
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));
			return simpleDateFormat.parse(dateToParse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String longToString(long dateInLong){
		return new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss", Locale.getDefault()).format(dateInLong);
	}
}
