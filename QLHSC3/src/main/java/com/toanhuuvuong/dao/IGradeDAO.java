package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Grade;

public interface IGradeDAO
{
	public Grade findByCode(String code);
	public Grade findByName(String name);
}