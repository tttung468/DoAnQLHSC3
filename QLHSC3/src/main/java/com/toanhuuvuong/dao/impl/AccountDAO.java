package com.toanhuuvuong.dao.impl;

import java.util.List;

import com.toanhuuvuong.dao.IAccountDAO;
import com.toanhuuvuong.model.Account;

public class AccountDAO extends AbstractHibernateDAO<Account> implements IAccountDAO
{
	public AccountDAO() 
	{
		setClazz(Account.class);
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
