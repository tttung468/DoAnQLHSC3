package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Nationality;

public interface INationalityDAO
{
	public Nationality findByCode(String code);
	public Nationality findByName(String name);
}