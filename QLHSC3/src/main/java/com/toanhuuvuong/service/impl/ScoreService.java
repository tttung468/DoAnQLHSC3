package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.ScoreDAO;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.service.IScoreService;

public class ScoreService extends GenericService<Score> implements IScoreService
{ 
	private ScoreDAO scoreDAO = new ScoreDAO();
	
	@Override
	public Map<String, String> validate(Score model)
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
	public AbstractHibernateDAO<Score> getDAO() 
	{
		return scoreDAO;
	}
}
