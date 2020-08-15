package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Student;

public interface IStudentDAO
{
	public Student findByCode(String code);
}