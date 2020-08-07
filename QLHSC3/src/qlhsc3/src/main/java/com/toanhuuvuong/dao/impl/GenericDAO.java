package com.toanhuuvuong.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.toanhuuvuong.dao.IGenericDAO;
import com.toanhuuvuong.mapper.IRowMapper;

public class GenericDAO<T> implements IGenericDAO<T>
{
	private ResourceBundle bundle = ResourceBundle.getBundle("db");
	
	private Connection getConnection() 
	{
		Connection conn = null;
		try 
		{
			Class.forName(bundle.getString("drivername"));
			String url = bundle.getString("url");
			String username = bundle.getString("username");
			String password = bundle.getString("password");
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	private void setParameters(PreparedStatement statement, Object... parameters)
	{
		try 
		{
			if(statement.getParameterMetaData().getParameterCount() == 0)
				return;
			
			int length = parameters.length;
			int j = 0;
			for(int i = 0; i < length; i++)
			{
				Object parameter = parameters[i];
				j++;
				if(parameter instanceof Long)
					statement.setLong(j, (Long)parameter);
				else if(parameter instanceof Float)
					statement.setFloat(j, (Float)parameter);
				else if(parameter instanceof String)
					statement.setString(j, (String)parameter);
				else if(parameter instanceof Date)
					statement.setDate(j, (Date)parameter);
				else if(parameter instanceof java.util.Date)
					statement.setTimestamp(j, (Timestamp)parameter);
				else if(parameter instanceof Boolean)
					statement.setBoolean(j, (Boolean)parameter);
				else if(parameter instanceof Integer)
					statement.setInt(j, (Integer)parameter);
				else if(parameter == null)
				{
					j--;
					continue;
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public List<T> find(String sql, IRowMapper<T> rowMapper, Object... parameters)
	{
		List<T> results = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
				results.add(rowMapper.mapRow(resultSet));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(conn != null)
					conn.close();
				if(statement != null)
					statement.close();
				if(resultSet != null)
					resultSet.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		return results;
	}
	@Override
	public void update(String sql, Object... parameters) 
	{
		Connection conn = null;
		PreparedStatement statement = null;
		
		try
		{
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			statement.executeUpdate();
			conn.commit();
		} 
		catch (SQLException e) 
		{
			try 
			{
				if(conn != null)
					conn.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(conn != null)
					conn.close();
				if(statement != null)
					statement.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
	}
	@Override
	public Long insert(String sql, Object... parameters) 
	{
		Long id = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next())
				id = resultSet.getLong(1);
			conn.commit();
		} 
		catch (SQLException e) 
		{
			try 
			{
				if(conn != null)
					conn.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(conn != null)
					conn.close();
				if(statement != null)
					statement.close();
				if(resultSet != null)
					resultSet.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
		return id;
	}
}
