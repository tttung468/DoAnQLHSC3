package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Subject;

public interface ISubjectService 
{
	public Subject findByCode(String code);
	public Subject findByName(String name);
}
