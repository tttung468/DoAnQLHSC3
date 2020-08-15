package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.INationalityDAO;
import com.toanhuuvuong.model.Nationality;

public class NationalityDAO extends AbstractHibernateDAO<Nationality> implements INationalityDAO {
	public NationalityDAO() {
		setClazz(Nationality.class);
	}

	@Override
	public Nationality findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Nationality findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
