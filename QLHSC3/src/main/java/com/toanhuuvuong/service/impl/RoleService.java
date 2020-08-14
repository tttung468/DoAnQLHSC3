package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.Map;

import com.toanhuuvuong.dao.IRoleDAO;
import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.RoleDAO;
import com.toanhuuvuong.model.Role;
import com.toanhuuvuong.service.IRoleService;

public class RoleService extends GenericService<Role> implements IRoleService
{ 
	private IRoleDAO roleDAO = new RoleDAO();
	
	@Override
	public Map<String, String> validate(Role model)
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
	public AbstractHibernateDAO<Role> getDAO() 
	{
		return (RoleDAO)roleDAO;
	}
}
