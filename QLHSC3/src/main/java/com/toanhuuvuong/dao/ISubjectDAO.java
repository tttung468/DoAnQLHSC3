package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Subject;

public interface ISubjectDAO
{
	public Subject findByCode(String code);
	public Subject findByName(String name);
}