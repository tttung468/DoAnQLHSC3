package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.IGradeDAO;
import com.toanhuuvuong.model.Grade;

public class GradeDAO extends AbstractHibernateDAO<Grade> implements IGradeDAO {
	public GradeDAO() {
		setClazz(Grade.class);
	}

	@Override
	public Grade findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Grade findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
