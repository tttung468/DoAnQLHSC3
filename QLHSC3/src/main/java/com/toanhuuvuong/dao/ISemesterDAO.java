package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Semester;

public interface ISemesterDAO
{
	public Semester findByCode(String code);
	public Semester findByName(String name);
}