package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Observation;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Student;

public interface IObservationService 
{
	public Observation findByStudent(Student student, SchoolYear schoolYear, SchoolClass schoolClass);
}
