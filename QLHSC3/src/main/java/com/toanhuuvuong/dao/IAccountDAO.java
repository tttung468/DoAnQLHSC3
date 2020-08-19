package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.model.Account;

public interface IAccountDAO
{
	public Account findByUsername(String username);
	public List<Account> findByRoleCode(String code);
}