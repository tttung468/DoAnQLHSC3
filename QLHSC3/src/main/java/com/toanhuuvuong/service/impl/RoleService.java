package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.RoleDAO;
import com.toanhuuvuong.model.Role;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IRoleService;

public class RoleService extends GenericService<Role> implements IRoleService
{ 
	private RoleDAO roleDAO = new RoleDAO();
	
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
		return roleDAO;
	}
	@Override
	public Role findByCode(String code)
	{
		Role filter = new Role();
		filter.setCode(code);
		
		Pageable<Role> pageable = new PageRequest<Role>(null, null, null, null, filter);
		
		List<Role> list = roleDAO.find(pageable);
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
	@Override
	public Role findByName(String name)
	{
		return null;
	}
}
