package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.SchoolYear;

public interface ISchoolYearDAO
{
	public SchoolYear findByCode(String code);
}