package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.SubjectDAO;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.service.ISubjectService;

public class SubjectService extends GenericService<Subject> implements ISubjectService
{ 
	private SubjectDAO subjectDAO = new SubjectDAO();
	
	@Override
	public Map<String, String> validate(Subject model)
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
	public AbstractHibernateDAO<Subject> getDAO() 
	{
		return subjectDAO;
	}
	@Override
	public Subject findByCode(String code)
	{
		return null;
	}
	@Override
	public Subject findByName(String name)
	{
		return null;
	}
}
