package com.toanhuuvuong.dao.impl;

import com.toanhuuvuong.dao.IScoreTypeDAO;
import com.toanhuuvuong.model.ScoreType;

public class ScoreTypeDAO extends AbstractHibernateDAO<ScoreType> implements IScoreTypeDAO {
	public ScoreTypeDAO(){
        setClazz(ScoreType.class);
    }

	@Override
	public ScoreType findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreType findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}