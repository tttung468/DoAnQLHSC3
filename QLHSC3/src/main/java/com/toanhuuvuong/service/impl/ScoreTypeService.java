package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.ScoreTypeDAO;
import com.toanhuuvuong.model.ScoreType;
import com.toanhuuvuong.service.IScoreTypeService;

public class ScoreTypeService extends GenericService<ScoreType> implements IScoreTypeService
{ 
	private ScoreTypeDAO scoreTypeDAO = new ScoreTypeDAO();
	
	@Override
	public Map<String, String> validate(ScoreType model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		
		if(model == null)
		{
			map.put("messageCode", "code_existed");
			map.put("alert", "danger");
		}
		else
		{
			map.put("messageCode", "insert_success");
			map.put("alert", "success");
		}
	
		return map;
	}
	@Override
	public AbstractHibernateDAO<ScoreType> getDAO() 
	{
		return scoreTypeDAO;
	}
	@Override
	public ScoreType findByCode(String code)
	{
		return null;
	}
	@Override
	public ScoreType findByName(String name)
	{
		return null;
	}
}
