package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.OfficeStaffDAO;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.OfficeStaff;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IOfficeStaffService;

public class OfficeStaffService extends GenericService<OfficeStaff> implements IOfficeStaffService
{
	private OfficeStaffDAO officeStaffDAO = new OfficeStaffDAO();
	private AccountService accountService = new AccountService();
	
	@Override
	public Map<String, String> validate(OfficeStaff model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		
		if(model != null && findByCode(model.getCode()) != null)
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
	public OfficeStaff findByCode(String code)
	{
		OfficeStaff filter = new OfficeStaff();
		filter.setCode(code);
		
		Pageable<OfficeStaff> pageable = new PageRequest<OfficeStaff>(null, null, null, null, filter);
		List<OfficeStaff> list = officeStaffDAO.find(pageable);
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
	@Override
	public OfficeStaff findByAccountUsername(String username) 
	{
		OfficeStaff filter = new OfficeStaff();
		Account account = accountService.findByUsername(username);
		if(account != null)
		{
			filter.setAccount(account);
		
			Pageable<OfficeStaff> pageable = new PageRequest<OfficeStaff>(null, null, null, null, filter);
			List<OfficeStaff> list = officeStaffDAO.find(pageable);
			
			return (list != null && !list.isEmpty()) ? list.get(0) : null;
		}
		return null;
	}
}
