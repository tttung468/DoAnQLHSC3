package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Religion;

public interface IReligionService 
{
	public Religion findByCode(String code);
	public Religion findByName(String name);
}
