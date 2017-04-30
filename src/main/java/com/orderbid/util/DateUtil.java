package com.orderbid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date getStringToDate(String dateStr) {
		Date date = null;
		//03/23/2016 12:00 AM
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		try {

			date= formatter.parse(dateStr);
			System.out.println(date);
			System.out.println(formatter.format(date));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
