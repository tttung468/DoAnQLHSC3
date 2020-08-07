package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.model.RoleModel;
import com.toanhuuvuong.pagination.Pageable;

public interface IRoleDAO
{
	public RoleModel findOne(Long id);
	public RoleModel findByCode(String code);
	public List<RoleModel> findByLowerPriority(Integer priority);
	public List<RoleModel> findAll();
	public void update(RoleModel model);
	public void delete(Long id);
	public Long insertOne(RoleModel model);
	public List<RoleModel> find(Pageable<RoleModel> pageable);
	public Integer count(Pageable<RoleModel> pageable);
}