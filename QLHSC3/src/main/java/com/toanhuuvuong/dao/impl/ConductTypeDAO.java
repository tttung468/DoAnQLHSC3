package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.IConductTypeDAO;
import com.toanhuuvuong.model.ConductType;

public class ConductTypeDAO extends AbstractHibernateDAO<ConductType> implements IConductTypeDAO {
	public ConductTypeDAO() {
		setClazz(ConductType.class);
	}

	@Override
	public ConductType findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConductType findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
