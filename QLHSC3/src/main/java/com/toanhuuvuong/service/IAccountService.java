package com.toanhuuvuong.service;

import java.util.List;

import com.toanhuuvuong.model.Account;

public interface IAccountService 
{
	public Account findByUsername(String username);
	public List<Account> findByRoleCode(String code);
}
