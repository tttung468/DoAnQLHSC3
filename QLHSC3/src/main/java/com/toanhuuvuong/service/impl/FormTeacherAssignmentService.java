package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.FormTeacherAssignmentDAO;
import com.toanhuuvuong.model.FormTeacherAssignment;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IFormTeacherAssignmentService;

public class FormTeacherAssignmentService extends GenericService<FormTeacherAssignment> implements IFormTeacherAssignmentService
{ 
	private FormTeacherAssignmentDAO formTeacherAssignmentDAO = new FormTeacherAssignmentDAO();
	
	@Override
	public Map<String, String> validate(FormTeacherAssignment model)
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
	public AbstractHibernateDAO<FormTeacherAssignment> getDAO() 
	{
		return formTeacherAssignmentDAO;
	}
	@Override
	public FormTeacherAssignment findBySchoolClass(SchoolYear schoolYear, SchoolClass schoolClass) 
	{
		FormTeacherAssignment filter = new FormTeacherAssignment();
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<FormTeacherAssignment> pageable = new PageRequest<FormTeacherAssignment>(null, null, null, null, filter);
		
		List<FormTeacherAssignment> list = formTeacherAssignmentDAO.find(pageable);
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
}
