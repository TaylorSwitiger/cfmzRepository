package com.baizhi.cmfz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConvertUtil {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static java.util.Date toUtilDate(String date){
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static java.sql.Date toSqlDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
	
	public static String toString(java.util.Date date){
		return sdf.format(date);
	}
}
