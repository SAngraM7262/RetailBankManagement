package com.nationalbank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static java.sql.Date conToDate(String str,String format) throws ParseException 
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = formatter.parse(str);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
	
		return sqlDate;
			
	}

}
