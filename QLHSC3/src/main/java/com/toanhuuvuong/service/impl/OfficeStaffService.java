package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.OfficeStaffDAO;
import com.toanhuuvuong.model.HRStaff;
import com.toanhuuvuong.model.OfficeStaff;
import com.toanhuuvuong.service.IOfficeStaffService;

public class OfficeStaffService extends GenericService<OfficeStaff> implements IOfficeStaffService
{
	private OfficeStaffDAO officeStaffDAO = new OfficeStaffDAO();
	
	@Override
	public Map<String, String> validate(OfficeStaff model)
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
	public AbstractHibernateDAO<OfficeStaff> getDAO() 
	{
		return officeStaffDAO;
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
