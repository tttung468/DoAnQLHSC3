package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Nationality;

public interface INationalityService 
{
	public Nationality findByCode(String code);
	public Nationality findByName(String name);
}
