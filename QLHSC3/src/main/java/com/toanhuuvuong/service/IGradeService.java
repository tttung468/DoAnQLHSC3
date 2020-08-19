package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Grade;

public interface IGradeService 
{
	public Grade findByCode(String code);
	public Grade findByName(String name);
}
