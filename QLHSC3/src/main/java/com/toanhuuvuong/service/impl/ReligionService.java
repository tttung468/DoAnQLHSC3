package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.ReligionDAO;
import com.toanhuuvuong.model.Religion;
import com.toanhuuvuong.service.IReligionService;

public class ReligionService extends GenericService<Religion> implements IReligionService
{ 
	private ReligionDAO religionDAO = new ReligionDAO();
	
	@Override
	public Map<String, String> validate(Religion model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		
		if(model == null)
		{
			map.put("messageCode", "code_existed");
			map.put("alert", "danger");
		}
		else
		{
			map.put("messageCode", "insert_success");
			map.put("alert", "success");
		}
	
		return map;
	}
	@Override
	public AbstractHibernateDAO<Religion> getDAO() 
	{
		return religionDAO;
	}
	@Override
	public Religion findByCode(String code)
	{
		return null;
	}
	@Override
	public Religion findByName(String name)
	{
		return null;
	}
}
