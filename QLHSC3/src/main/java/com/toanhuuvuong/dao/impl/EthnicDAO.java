package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.IEthnicDAO;
import com.toanhuuvuong.model.Ethnic;

public class EthnicDAO extends AbstractHibernateDAO<Ethnic> implements IEthnicDAO {
	public EthnicDAO() {
		setClazz(Ethnic.class);
	}

	@Override
	public Ethnic findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Ethnic findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
