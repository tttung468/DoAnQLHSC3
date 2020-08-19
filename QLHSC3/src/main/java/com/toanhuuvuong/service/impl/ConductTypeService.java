package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.ConductTypeDAO;
import com.toanhuuvuong.model.ConductType;
import com.toanhuuvuong.service.IConductTypeService;

public class ConductTypeService extends GenericService<ConductType> implements IConductTypeService
{ 
	private ConductTypeDAO conductTypeDAO = new ConductTypeDAO();
	
	@Override
	public Map<String, String> validate(ConductType model)
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
	public AbstractHibernateDAO<ConductType> getDAO() 
	{
		return conductTypeDAO;
	}
	@Override
	public ConductType findByCode(String code)
	{
		return null;
	}
	@Override
	public ConductType findByName(String name)
	{
		return null;
	}
}
