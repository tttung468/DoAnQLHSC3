package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Teacher;

public interface ITeacherDAO
{
	public Teacher findByCode(String code);
	public Teacher findBySubjectCode(String code);
	public Teacher findByAccountUsername(String username);
}