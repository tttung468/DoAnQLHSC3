package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.EthnicDAO;
import com.toanhuuvuong.model.Ethnic;
import com.toanhuuvuong.service.IEthnicService;

public class EthnicService extends GenericService<Ethnic> implements IEthnicService
{ 
	private EthnicDAO ethnicDAO = new EthnicDAO();
	
	@Override
	public Map<String, String> validate(Ethnic model)
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
	public AbstractHibernateDAO<Ethnic> getDAO() 
	{
		return ethnicDAO;
	}
	@Override
	public Ethnic findByCode(String code)
	{
		return null;
	}
	@Override
	public Ethnic findByName(String name)
	{
		return null;
	}
}
