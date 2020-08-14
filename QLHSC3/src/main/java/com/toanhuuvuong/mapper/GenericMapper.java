package com.toanhuuvuong.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericMapper 
{
	protected boolean hasColumn(ResultSet resultSet, String label)
	{
		try 
		{
			resultSet.findColumn(label);
			return true;
		} 
		catch (SQLException e){}
		
		return false;
	}
}
