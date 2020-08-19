package com.toanhuuvuong.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DateTimeUtils 
{
	public static Timestamp parseTimestamp(String dateTime)
	{
		try 
		{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date parsedDate = dateFormat.parse(dateTime);
		    Timestamp timestamp = new Timestamp(parsedDate.getTime());
		    
		    return timestamp;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public static LocalDate parseDate(String dateTime)
	{
		try 
		{
			String date = getDateFrom(dateTime);
		    
		    return LocalDate.parse(date);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public static LocalTime parseTime(String dateTime)
	{
		try 
		{		    
			String time = getTimeFrom(dateTime);
			
		    return LocalTime.parse(time);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public static String getDateFrom(String dateTime)
	{
		if(dateTime == null)
			return null;
		
		int beginIndex = 0;
		int lastIndex = dateTime.lastIndexOf(' ');
		if(lastIndex < 0)
			return null;
					
		return dateTime.substring(beginIndex, lastIndex);
	}
	public static String getTimeFrom(String dateTime)
	{
		if(dateTime == null)
			return null;
		
		int beginIndex = dateTime.lastIndexOf(' ') + 1;
		int lastIndex = dateTime.length();
		if(lastIndex <= 0)
			return null;
					
		return dateTime.substring(beginIndex, lastIndex);
	}
}
