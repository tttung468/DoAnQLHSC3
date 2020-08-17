package com.toanhuuvuong.service;

import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.StudentOfClass;

public interface IStudentOfClassService 
{
	public StudentOfClass findByStudent(Student student, Semester semester, SchoolYear schoolYear, SchoolClass schoolClass);
	public Integer countByStudentOfSchoolClass(Semester semester, SchoolYear schoolYear, SchoolClass schoolClass);
}
