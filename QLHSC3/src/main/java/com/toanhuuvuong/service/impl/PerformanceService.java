package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.PerformanceDAO;
import com.toanhuuvuong.model.Performance;
import com.toanhuuvuong.service.IPerformanceService;

public class PerformanceService extends GenericService<Performance> implements IPerformanceService
{ 
	private PerformanceDAO performanceDAO = new PerformanceDAO();
	
	@Override
	public Map<String, String> validate(Performance model)
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
	public AbstractHibernateDAO<Performance> getDAO() 
	{
		return performanceDAO;
	}
	@Override
	public Performance findByCode(String code)
	{
		return null;
	}
	@Override
	public Performance findByName(String name)
	{
		return null;
	}
	@Override
	public Performance generateFromAvg(Float avg) 
	{
		List<Performance> performances = performanceDAO.findAll();
		
		for(Performance performance : performances)
		{
			if(avg >= performance.getLowerBound() && avg <= performance.getUpperBound())
				return performance;
		}
		
		return null;
	}
}
