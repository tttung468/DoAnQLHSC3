package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.StudentOfClassDAO;
import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.service.IStudentOfClassService;

public class StudentOfClassService extends GenericService<StudentOfClass> implements IStudentOfClassService
{ 
	private StudentOfClassDAO studentOfClassDAO = new StudentOfClassDAO();
	
	@Override
	public Map<String, String> validate(StudentOfClass model)
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
	public AbstractHibernateDAO<StudentOfClass> getDAO() 
	{
		return studentOfClassDAO;
	}
}
