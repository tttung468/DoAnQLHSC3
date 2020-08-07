package com.toanhuuvuong.dao.impl;

import java.util.List;

import com.toanhuuvuong.dao.IAccountDAO;
import com.toanhuuvuong.mapper.AccountMapper;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class AccountDAO extends GenericDAO<AccountModel> implements IAccountDAO 
{
	@Override
	public AccountModel findOne(Long id) 
	{
		StringBuilder sql = new StringBuilder("SELECT * FROM Account");
		sql.append(" WHERE id = ?");
		List<AccountModel> model = super.find(sql.toString(), new AccountMapper(), id);
		return (model.isEmpty() ? null : model.get(0));
	}
	@Override
	public AccountModel findByUsername(String username) 
	{
		StringBuilder sql = new StringBuilder("SELECT * FROM Account");
		sql.append(" WHERE username = ?");
		List<AccountModel> model = super.find(sql.toString(), new AccountMapper(), username);
		return (model.isEmpty() ? null : model.get(0));
	}
	@Override
	public List<AccountModel> findByRoleId(Long roleId) 
	{
		StringBuilder sql = new StringBuilder("SELECT * FROM Account");
		sql.append(" WHERE roleid = ?");
		return super.find(sql.toString(), new AccountMapper(), roleId);
	}
	@Override
	public List<AccountModel> findByRoleCode(String roleCode) 
	{
		StringBuilder sql = new StringBuilder("SELECT A.* FROM Account AS A");
		sql.append(" INNER JOIN Role AS R ON A.roleid = R.id");
		sql.append(" WHERE R.code = ?");
		return super.find(sql.toString(), new AccountMapper(), roleCode);
	}
	@Override
	public List<AccountModel> findAll()
	{
		StringBuilder sql = new StringBuilder("SELECT * FROM Account");
		return super.find(sql.toString(), new AccountMapper());
	}
	@Override
	public void update(AccountModel model) 
	{
		StringBuilder sql = new StringBuilder("UPDATE Account SET username = ?, password = ?, roleid = ?,");
		sql.append(" isdeleted = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");
		super.update(sql.toString(), model.getUsername(), model.getPassword(), model.getRoleId(),
				model.getIsDeleted(), model.getCreatedDate(), model.getCreatedBy(), model.getModifiedDate(), model.getModifiedBy(),
				model.getId());
	}
	@Override
	public void delete(Long id) 
	{
		String sql = "DELETE FROM Account WHERE id = ?";
		super.update(sql, id);
	}
	@Override
	public Long insertOne(AccountModel model)
	{
		StringBuilder sql = new StringBuilder("INSERT INTO Account(username, password, roleid,");
		sql.append(" isdeleted, createddate, createdby)"); 
		sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
		return super.insert(sql.toString(), model.getUsername(), model.getPassword(), model.getRoleId(), 
				model.getIsDeleted(), model.getCreatedDate(), model.getCreatedBy());
	}
	@Override
	public List<AccountModel> find(Pageable<AccountModel> pageable) 
	{
		Integer offset = pageable.getOffset();
		Integer limit = pageable.getLimit();
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		AccountModel filterModel = pageable.getFilterModel();
		
		StringBuilder sql = new StringBuilder("SELECT A.* FROM Account AS A");
		sql.append(" INNER JOIN Role AS R ON A.roleid = R.id");
		
		if(filterModel != null || searchKey != null)
			sql.append(" WHERE");
		if(filterModel != null)
		{ 	
			if(filterModel.getUsername() != null)
				sql.append(" AND");
			if(filterModel.getUsername() != null)
				sql.append(" A.username = ?");
			if(filterModel.getIsDeleted() != null &&
				filterModel.getUsername() != null)
				sql.append(" AND");
			if(filterModel.getIsDeleted() != null)
				sql.append(" A.isdeleted = ?");
			if(filterModel.getRole().getName() != null &&
				(filterModel.getUsername() != null ||
				filterModel.getIsDeleted() != null))
				sql.append(" AND");
			if(filterModel.getRole().getName() != null)
				sql.append(" R.name = ?");
			if(searchKey != null &&
				(filterModel.getUsername() != null ||
				filterModel.getIsDeleted() != null ||
				filterModel.getRole().getName() != null))
				sql.append(" AND");
		}
		if(searchKey != null)
		{
			sql.append(" (A.username LIKE '%" + searchKey + "%'");
			sql.append(" OR R.name LIKE '%" + searchKey + "%')");
		}
		if(sorter != null && 
			sorter.getSortBy() != null &&
			sorter.getSortName() != null)
			sql.append(" ORDER BY " + sorter.getSortBy() + " " + sorter.getSortName());
		if(offset != null &&
			limit != null)
			sql.append(" LIMIT " + offset + ", " + limit);
		
		return (filterModel == null) ? super.find(sql.toString(), new AccountMapper()) 
									: super.find(sql.toString(), new AccountMapper(), 
										filterModel.getUsername(), 
										filterModel.getIsDeleted(), filterModel.getRole().getName());
	}
	@Override
	public Integer count(Pageable<AccountModel> pageable) 
	{
		Integer offset = pageable.getOffset();
		Integer limit = pageable.getLimit();
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		AccountModel filterModel = pageable.getFilterModel();
		
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count FROM Account AS A");
		sql.append(" INNER JOIN Role AS R ON A.roleid = R.id");
		
		if(filterModel != null || searchKey != null)
			sql.append(" WHERE");
		if(filterModel != null)
		{
			if(filterModel.getUsername() != null)
				sql.append(" AND");
			if(filterModel.getUsername() != null)
				sql.append(" A.username = ?");
			if(filterModel.getIsDeleted() != null &&
				filterModel.getUsername() != null)
				sql.append(" AND");
			if(filterModel.getIsDeleted() != null)
				sql.append(" A.isdeleted = ?");
			if(filterModel.getRole().getName() != null &&
				(filterModel.getUsername() != null ||
				filterModel.getIsDeleted() != null))
				sql.append(" AND");
			if(filterModel.getRole().getName() != null)
				sql.append(" R.name = ?");
			if(searchKey != null &&
				(filterModel.getUsername() != null ||
				filterModel.getIsDeleted() != null ||
				filterModel.getRole().getName() != null))
				sql.append(" AND");
		}
		if(searchKey != null)
		{
			sql.append(" (A.username LIKE '%" + searchKey + "%'");
			sql.append(" OR R.name LIKE '%" + searchKey + "%')");
		}
		if(sorter != null && 
			sorter.getSortBy() != null &&
			sorter.getSortName() != null)
			sql.append(" ORDER BY " + sorter.getSortBy() + " " + sorter.getSortName());
		if(offset != null &&
			limit != null)
			sql.append(" LIMIT " + offset + ", " + limit);
		
		List<AccountModel> model = (filterModel == null) ? super.find(sql.toString(), new AccountMapper()) 
														: super.find(sql.toString(), new AccountMapper(), 
																filterModel.getUsername(), 
																filterModel.getIsDeleted(), filterModel.getRole().getName());
		
		return (model.isEmpty() ? null : model.get(0).getCount());
	}
}
