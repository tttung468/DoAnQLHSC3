package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.HRStaffDAO;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.HRStaff;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IHRStaffService;

public class HRStaffService extends GenericService<HRStaff> implements IHRStaffService
{
	private HRStaffDAO hrStaffDAO = new HRStaffDAO();
	private AccountService accountService = new AccountService();
	
	@Override
	public Map<String, String> validate(HRStaff model)
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
	public AbstractHibernateDAO<HRStaff> getDAO() 
	{
		return hrStaffDAO;
	}
	@Override
	public HRStaff findByCode(String code)
	{
		HRStaff filter = new HRStaff();
		filter.setCode(code);
		
		Pageable<HRStaff> pageable = new PageRequest<HRStaff>(null, null, null, null, filter);
		List<HRStaff> list = hrStaffDAO.find(pageable);
		
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}
	@Override
	public HRStaff findByAccountUsername(String username) 
	{
		HRStaff filter = new HRStaff();
		Account account = accountService.findByUsername(username);
		if(account != null)
		{
			filter.setAccount(account);
		
			Pageable<HRStaff> pageable = new PageRequest<HRStaff>(null, null, null, null, filter);
			List<HRStaff> list = hrStaffDAO.find(pageable);
			
			return (list != null && !list.isEmpty()) ? list.get(0) : null;
		}
		return null;
	}
}
