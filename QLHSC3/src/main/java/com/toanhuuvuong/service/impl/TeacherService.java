package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.TeacherDAO;
import com.toanhuuvuong.model.Teacher;
import com.toanhuuvuong.service.ITeacherService;

public class TeacherService extends GenericService<Teacher> implements ITeacherService
{ 
	private TeacherDAO teacherDAO = new TeacherDAO();
	
	@Override
	public Map<String, String> validate(Teacher model)
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
	public AbstractHibernateDAO<Teacher> getDAO() 
	{
		return teacherDAO;
	}
	@Override
	public Teacher findByCode(String code) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Teacher findBySubjectCode(String code) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Teacher findByAccountUsername(String username) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
