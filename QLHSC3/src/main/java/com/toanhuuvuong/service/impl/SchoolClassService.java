package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.SchoolClassDAO;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.service.ISchoolClassService;

public class SchoolClassService extends GenericService<SchoolClass> implements ISchoolClassService
{ 
	private SchoolClassDAO schoolClassDAO = new SchoolClassDAO();
	
	@Override
	public Map<String, String> validate(SchoolClass model)
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
	public AbstractHibernateDAO<SchoolClass> getDAO() 
	{
		return schoolClassDAO;
	}
	@Override
	public SchoolClass findByCode(String code) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SchoolClass findByName(String name) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SchoolClass> findByGradeCode(String code)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
