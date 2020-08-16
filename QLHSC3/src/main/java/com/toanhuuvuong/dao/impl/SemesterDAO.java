package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.ISemesterDAO;
import com.toanhuuvuong.model.Semester;

public class SemesterDAO extends AbstractHibernateDAO<Semester> implements ISemesterDAO {
	public SemesterDAO() {
		setClazz(Semester.class);
	}

	@Override
	public Semester findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Semester findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}