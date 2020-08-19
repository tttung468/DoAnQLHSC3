package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.StudentOfClassDAO;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
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
	@Override
	public StudentOfClass findByStudent(Student student, Semester semester, SchoolYear schoolYear, SchoolClass schoolClass)
	{
		StudentOfClass filter = new StudentOfClass();
		filter.setStudent(student);
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<StudentOfClass> pageable = new PageRequest<StudentOfClass>(null, null, null, null, filter);
		
		List<StudentOfClass> studentsOfClass = studentOfClassDAO.find(pageable);
		
		return (studentsOfClass != null && !studentsOfClass.isEmpty()) ? studentsOfClass.get(0) : null;
	}
	@Override
	public StudentOfClass findByStudent(Student student, Semester semester, SchoolYear schoolYear)
	{
		StudentOfClass filter = new StudentOfClass();
		filter.setStudent(student);
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		
		Pageable<StudentOfClass> pageable = new PageRequest<StudentOfClass>(null, null, null, null, filter);
		
		List<StudentOfClass> studentsOfClass = studentOfClassDAO.find(pageable);
		
		return (studentsOfClass != null && !studentsOfClass.isEmpty()) ? studentsOfClass.get(0) : null;
	}
	@Override
	public Integer countByStudentOfSchoolClass(Semester semester, SchoolYear schoolYear, SchoolClass schoolClass) 
	{
		StudentOfClass filter = new StudentOfClass();
		filter.setSemester(semester);
		filter.setSchoolYear(schoolYear);
		filter.setSchoolClass(schoolClass);
		
		Pageable<StudentOfClass> pageable = new PageRequest<StudentOfClass>(null, null, null, null, filter);
		
		List<StudentOfClass> studentsOfClass = studentOfClassDAO.find(pageable);
		
		return (studentsOfClass != null) ? studentsOfClass.size() : null;
	}
}
