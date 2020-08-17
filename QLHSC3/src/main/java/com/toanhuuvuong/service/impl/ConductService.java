package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.ConductDAO;
import com.toanhuuvuong.model.Conduct;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IConductService;

public class ConductService extends GenericService<Conduct> implements IConductService
{ 
	private ConductDAO conductDAO = new ConductDAO();
	
	@Override
	public Map<String, String> validate(Conduct model)
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
	public AbstractHibernateDAO<Conduct> getDAO() 
	{
		return conductDAO;
	}
	@Override
	public Conduct findByStudent(Student student, Semester semester, 
			SchoolYear schoolYear, SchoolClass schoolClass)
	{
		Conduct filter = new Conduct();
		filter.setStudent(student);
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<Conduct> pageable = new PageRequest<Conduct>(null, null, null, null, filter);
		
		List<Conduct> conducts = conductDAO.find(pageable);
		
		return (conducts != null && !conducts.isEmpty()) ? conducts.get(0) : null;
	}
}
