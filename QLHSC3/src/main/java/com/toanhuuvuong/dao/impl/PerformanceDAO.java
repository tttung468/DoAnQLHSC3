package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.IPerformanceDAO;
import com.toanhuuvuong.model.Performance;

public class PerformanceDAO extends AbstractHibernateDAO<Performance> implements IPerformanceDAO {
	public PerformanceDAO() {
		setClazz(Performance.class);
	}

	@Override
	public Performance findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Performance findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
