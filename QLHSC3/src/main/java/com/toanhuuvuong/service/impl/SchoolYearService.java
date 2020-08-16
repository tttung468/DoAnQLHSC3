package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.SchoolYearDAO;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.service.ISchoolYearService;

public class SchoolYearService extends GenericService<SchoolYear> implements ISchoolYearService
{ 
	private SchoolYearDAO schoolYearDAO = new SchoolYearDAO();
	
	@Override
	public Map<String, String> validate(SchoolYear model)
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
	public AbstractHibernateDAO<SchoolYear> getDAO() 
	{
		return schoolYearDAO;
	}
	@Override
	public SchoolYear findByCode(String code)
	{
		return null;
	}
}
