package com.toanhuuvuong.utils;

import java.util.HashMap;
import java.util.Map;

public class SessionUtils 
{
	private static SessionUtils instance = null;
	private Map<String, Object> map;
	
	private SessionUtils() 
	{
		map = new HashMap<String, Object>();
	}
	public static SessionUtils getInstance()
	{
		if(instance == null)
			instance = new SessionUtils();
		return instance;
	}
	public void putValue(String key, Object value)
	{
		map.put(key, value);
	}
	public Object getValue(String key)
	{
		return map.get(key);
	}
	public void removeValue(String key)
	{
		map.remove(key);
	}
}
