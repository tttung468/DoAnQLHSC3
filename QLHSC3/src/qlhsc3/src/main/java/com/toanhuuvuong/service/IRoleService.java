package com.toanhuuvuong.service;

import java.util.List;
import java.util.Map;

import com.toanhuuvuong.model.RoleModel;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.pagination.Pageable;

public interface IRoleService 
{
	public RoleModel findOne(Long id);
	public RoleModel findByCode(String code);
	public List<RoleModel> findByLowerPriority(Integer priority);
	public List<RoleModel> findAll();
	public RoleModel updateOne(RoleModel model, AccountModel editor);
	public void delete(Long[] deletedIds);
	public RoleModel insertOne(RoleModel model, AccountModel creator);
	public List<RoleModel> find(Pageable<RoleModel> pageable);
	public Integer count(Pageable<RoleModel> pageable);
	public Map<String, String> validate(RoleModel model);
}
