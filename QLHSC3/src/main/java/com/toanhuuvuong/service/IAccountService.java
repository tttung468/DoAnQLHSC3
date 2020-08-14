package com.toanhuuvuong.service;

import java.util.List;
import java.util.Map;

import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.pagination.Pageable;

public interface IAccountService 
{
	public AccountModel findOne(Long id);
	public AccountModel findByUsername(String username);
	public List<AccountModel> findByRoleId(Long roleId);
	public List<AccountModel> findByRoleCode(String roleCode);
	public List<AccountModel> findAll();
	public AccountModel updateOne(AccountModel model, AccountModel editor);
	public void delete(Long[] deletedIds);
	public AccountModel insertOne(AccountModel model, AccountModel creator);
	public List<AccountModel> find(Pageable<AccountModel> pageable);
	public Integer count(Pageable<AccountModel> pageable);
	public Map<String, String> validate(AccountModel model);
	public boolean isUserRole(AccountModel model);
}
