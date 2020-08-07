package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.pagination.Pageable;

public interface IAccountDAO
{
	public AccountModel findOne(Long id);
	public AccountModel findByUsername(String username);
	public List<AccountModel> findByRoleId(Long roleId);
	public List<AccountModel> findByRoleCode(String roleCode); 
	public List<AccountModel> findAll(); 
	public void update(AccountModel model);
	public void delete(Long id);
	public Long insertOne(AccountModel model);
	public List<AccountModel> find(Pageable<AccountModel> pageable);
	public Integer count(Pageable<AccountModel> pageable);
}