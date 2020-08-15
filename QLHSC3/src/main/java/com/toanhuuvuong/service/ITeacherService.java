package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Teacher;

public interface ITeacherService 
{
	public Teacher findByCode(String code);
	public Teacher findBySubjectCode(String code);
	public Teacher findByAccountUsername(String username);
}
