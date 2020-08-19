package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.NationalityDAO;
import com.toanhuuvuong.model.Nationality;
import com.toanhuuvuong.service.INationalityService;

public class NationalityService extends GenericService<Nationality> implements INationalityService
{ 
	private NationalityDAO nationalityDAO = new NationalityDAO();
	
	@Override
	public Map<String, String> validate(Nationality model)
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
	public AbstractHibernateDAO<Nationality> getDAO() 
	{
		return nationalityDAO;
	}
	@Override
	public Nationality findByCode(String code)
	{
		return null;
	}
	@Override
	public Nationality findByName(String name)
	{
		return null;
	}
}
