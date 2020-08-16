package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.GradeDAO;
import com.toanhuuvuong.model.Grade;
import com.toanhuuvuong.service.IGradeService;

public class GradeService extends GenericService<Grade> implements IGradeService
{ 
	private GradeDAO gradeDAO = new GradeDAO();
	
	@Override
	public Map<String, String> validate(Grade model)
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
	public AbstractHibernateDAO<Grade> getDAO() 
	{
		return gradeDAO;
	}
	@Override
	public Grade findByCode(String code)
	{
		return null;
	}
	@Override
	public Grade findByName(String name)
	{
		return null;
	}
}
