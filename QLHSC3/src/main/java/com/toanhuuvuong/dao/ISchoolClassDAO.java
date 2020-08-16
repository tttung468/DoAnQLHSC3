package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.model.SchoolClass;

public interface ISchoolClassDAO
{
	public SchoolClass findByCode(String code);
	public SchoolClass findByName(String name);
	public List<SchoolClass> findByGradeCode(String code);
}