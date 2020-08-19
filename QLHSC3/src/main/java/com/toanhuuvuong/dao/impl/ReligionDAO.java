package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.IReligionDAO;
import com.toanhuuvuong.model.Religion;

public class ReligionDAO extends AbstractHibernateDAO<Religion> implements IReligionDAO {
	public ReligionDAO() {
		setClazz(Religion.class);
	}

	@Override
	public Religion findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Religion findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
