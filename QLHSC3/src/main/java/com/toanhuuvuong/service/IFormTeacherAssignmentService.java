package com.toanhuuvuong.service;

import com.toanhuuvuong.model.FormTeacherAssignment;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;

public interface IFormTeacherAssignmentService
{
	public FormTeacherAssignment findBySchoolClass(SchoolYear schoolYear, SchoolClass schoolClass);

}