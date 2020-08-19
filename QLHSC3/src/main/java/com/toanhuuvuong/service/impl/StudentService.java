package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.StudentDAO;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IStudentService;

public class StudentService extends GenericService<Student> implements IStudentService
{ 
	private StudentDAO studentDAO = new StudentDAO();
	
	@Override
	public Map<String, String> validate(Student model)
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
	public AbstractHibernateDAO<Student> getDAO() 
	{
		return studentDAO;
	}
	@Override
	public Student findByCode(String code)
	{
		Student filter = new Student();
		filter.setCode(code);
		
		Pageable<Student> pageable = new PageRequest<Student>(null, null, null, null, filter);
		List<Student> list = studentDAO.find(pageable);
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
}
