package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.ObservationDAO;
import com.toanhuuvuong.model.Observation;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IObservationService;

public class ObservationService extends GenericService<Observation> implements IObservationService
{ 
	private ObservationDAO observationDAO = new ObservationDAO();
	
	@Override
	public Map<String, String> validate(Observation model)
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
	public AbstractHibernateDAO<Observation> getDAO() 
	{
		return observationDAO;
	}
	@Override
	public Observation findByStudent(Student student, SchoolYear schoolYear, SchoolClass schoolClass)
	{
		Observation filter = new Observation();
		filter.setStudent(student);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<Observation> pageable = new PageRequest<Observation>(null, null, null, null, filter);
		
		List<Observation> observations = observationDAO.find(pageable);
		
		return (observations != null && !observations.isEmpty()) ? observations.get(0) : null;
	}
	@Override
	public Observation findByStudent(Student student, SchoolYear schoolYear) 
	{
		Observation filter = new Observation();
		filter.setStudent(student);
		filter.setSchoolYear(schoolYear);
		
		Pageable<Observation> pageable = new PageRequest<Observation>(null, null, null, null, filter);
		
		List<Observation> observations = observationDAO.find(pageable);
		
		return (observations != null && !observations.isEmpty()) ? observations.get(0) : null;
	}
}
