package com.toanhuuvuong.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.dao.impl.AbstractHibernateDAO;
import com.toanhuuvuong.dao.impl.AccountDAO;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.service.IAccountService;

public class AccountService extends GenericService<Account> implements IAccountService
{ 
	private AccountDAO accountDAO = new AccountDAO();
	
	@Override
	public Map<String, String> validate(Account model)
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
	public AbstractHibernateDAO<Account> getDAO() 
	{
		return accountDAO;
	}
	@Override
	public Account findByUsername(String username)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Account> findByRoleCode(String code)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
