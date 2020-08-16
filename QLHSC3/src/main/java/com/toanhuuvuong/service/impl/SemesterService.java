package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.SemesterDAO;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.service.ISemesterService;

public class SemesterService extends GenericService<Semester> implements ISemesterService
{ 
	private SemesterDAO semesterDAO = new SemesterDAO();
	
	@Override
	public Map<String, String> validate(Semester model)
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
	public AbstractHibernateDAO<Semester> getDAO() 
	{
		return semesterDAO;
	}
	@Override
	public Semester findByCode(String code)
	{
		return null;
	}
	@Override
	public Semester findByName(String name)
	{
		return null;
	}
}
