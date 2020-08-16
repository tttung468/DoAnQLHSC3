package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Semester;

public interface ISemesterService 
{
	public Semester findByCode(String code);
	public Semester findByName(String name);
}
