package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.HRStaffDAO;
import com.toanhuuvuong.model.HRStaff;
import com.toanhuuvuong.service.IHRStaffService;

public class HRStaffService extends GenericService<HRStaff> implements IHRStaffService
{
	private HRStaffDAO hrStaffDAO = new HRStaffDAO();
	
	@Override
	public Map<String, String> validate(HRStaff model)
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
	public AbstractHibernateDAO<HRStaff> getDAO() 
	{
		return hrStaffDAO;
	}
	@Override
	public HRStaff findByCode(String code)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HRStaff findByAccountUsername(String username) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
