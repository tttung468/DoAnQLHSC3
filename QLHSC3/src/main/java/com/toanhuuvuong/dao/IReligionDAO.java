package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Religion;

public interface IReligionDAO
{
	public Religion findByCode(String code);
	public Religion findByName(String name);
}