package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Conduct;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;

public interface IConductService 
{
	public Conduct findByStudent(Student student, Semester semester, 
			SchoolYear schoolYear, SchoolClass schoolClass);
}
