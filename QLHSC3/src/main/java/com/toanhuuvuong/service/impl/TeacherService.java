package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.TeacherDAO;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Teacher;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.ITeacherService;

public class TeacherService extends GenericService<Teacher> implements ITeacherService
{ 
	private TeacherDAO teacherDAO = new TeacherDAO();
	private AccountService accountService = new AccountService();
	
	@Override
	public Map<String, String> validate(Teacher model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		
		if(model != null && findByCode(model.getCode()) != null)
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
		Teacher filter = new Teacher();
		filter.setCode(code);
		
		Pageable<Teacher> pageable = new PageRequest<Teacher>(null, null, null, null, filter);
		List<Teacher> list = teacherDAO.find(pageable);
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
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
		Teacher filter = new Teacher();
		Account account = accountService.findByUsername(username);
		if(account != null)
		{
			filter.setAccount(account);
		
			Pageable<Teacher> pageable = new PageRequest<Teacher>(null, null, null, null, filter);
			List<Teacher> list = teacherDAO.find(pageable);
			
			return (list != null && !list.isEmpty()) ? list.get(0) : null;
		}
		return null;
	}
}
