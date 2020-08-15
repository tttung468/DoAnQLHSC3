package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.StudentDAO;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.service.IStudentService;

public class StudentService extends GenericService<Student> implements IStudentService
{ 
	private StudentDAO studentDAO = new StudentDAO();
	
	@Override
	public Map<String, String> validate(Student model)
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
	public AbstractHibernateDAO<Student> getDAO() 
	{
		return studentDAO;
	}
	@Override
	public Student findByCode(String code)
	{
		return null;
	}
}
