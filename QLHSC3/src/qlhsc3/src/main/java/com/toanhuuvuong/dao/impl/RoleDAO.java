package com.toanhuuvuong.dao.impl;

import java.util.List;

import com.toanhuuvuong.dao.IRoleDAO;
import com.toanhuuvuong.mapper.RoleMapper;
import com.toanhuuvuong.model.RoleModel;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.sort.Sorter;

public class RoleDAO extends GenericDAO<RoleModel> implements IRoleDAO 
{
	@Override
	public RoleModel findOne(Long id) 
	{
		String sql = "SELECT * FROM Role WHERE id = ?";
		List<RoleModel> model = super.find(sql, new RoleMapper(), id);
		return (model.isEmpty() ? null : model.get(0));
	}
	@Override
	public RoleModel findByCode(String code)
	{
		String sql = "SELECT * FROM Role WHERE code = ?";
		List<RoleModel> model = super.find(sql, new RoleMapper(), code);
		return (model.isEmpty() ? null : model.get(0));
	}
	@Override
	public List<RoleModel> findByLowerPriority(Integer priority)
	{
		String sql = "SELECT * FROM Role WHERE priority >= ?";
		return super.find(sql, new RoleMapper(), priority);
	}
	@Override
	public List<RoleModel> findAll()
	{
		String sql = "SELECT * FROM Role";
		return super.find(sql, new RoleMapper());
	}
	@Override
	public void update(RoleModel model) 
	{
		StringBuilder sql = new StringBuilder("UPDATE Role SET code = ?, name = ?, priority = ?,");
		sql.append(" isdeleted = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");
		super.update(sql.toString(), model.getCode(), model.getName(), model.getPriority(),
				model.getIsDeleted(), model.getCreatedDate(), model.getCreatedBy(), model.getModifiedDate(), model.getModifiedBy(),
				model.getId());
	}
	@Override
	public void delete(Long id) 
	{
		String sql = "DELETE FROM Role WHERE id = ?";
		super.update(sql, id);
	}
	@Override
	public Long insertOne(RoleModel model)
	{
		StringBuilder sql = new StringBuilder("INSERT INTO Role(code, name, priority,");
		sql.append(" isdeleted, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
		return super.insert(sql.toString(), model.getCode(), model.getName(), model.getPriority(),
				model.getIsDeleted(), model.getCreatedDate(), model.getCreatedBy());
	}
	@Override
	public List<RoleModel> find(Pageable<RoleModel> pageable) 
	{
		Integer offset = pageable.getOffset();
		Integer limit = pageable.getLimit();
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		RoleModel filterModel = pageable.getFilterModel();
		
		StringBuilder sql = new StringBuilder("SELECT * FROM Role");
		
		if(filterModel != null || searchKey != null)
			sql.append(" WHERE");
		if(filterModel != null)
		{
			if(filterModel.getCode() != null)
				sql.append(" code = ?");
			if(filterModel.getCode() != null && 
				filterModel.getName() != null)
				sql.append(" AND");
			if(filterModel.getName() != null)
				sql.append(" name = ?");
			if(filterModel.getPriority() != null &&
				(filterModel.getCode() != null || 
				filterModel.getName() != null))
				sql.append(" AND");
			if(filterModel.getPriority() != null)
				sql.append(" priority = ?");
			if(filterModel.getIsDeleted() != null &&
				(filterModel.getCode() != null || 
				filterModel.getName() != null ||
				filterModel.getPriority() != null))
				sql.append(" AND");
			if(filterModel.getIsDeleted() != null)
				sql.append(" isdeleted = ?");
			if(searchKey != null &&
				(filterModel.getCode() != null || 
				filterModel.getName() != null ||
				filterModel.getPriority() != null ||
				filterModel.getIsDeleted() != null))
				sql.append(" AND");
		}
		if(searchKey != null)
		{
			sql.append(" code LIKE '%" + searchKey + "%'");
			sql.append(" OR name LIKE '%" + searchKey + "%'");
			sql.append(" OR priority LIKE '%" + searchKey + "%'");
		}
		if(sorter != null && 
			sorter.getSortBy() != null &&
			sorter.getSortName() != null)
			sql.append(" ORDER BY " + sorter.getSortBy() + " " + sorter.getSortName());
		if(offset != null &&
			limit != null)
			sql.append(" LIMIT " + offset + ", " + limit);
		
		return (filterModel == null) ? super.find(sql.toString(), new RoleMapper()) 
									: super.find(sql.toString(), new RoleMapper(), 
										filterModel.getCode(), filterModel.getName(), 
										filterModel.getPriority(), filterModel.getIsDeleted());
	}
	@Override
	public Integer count(Pageable<RoleModel> pageable) 
	{
		Integer offset = pageable.getOffset();
		Integer limit = pageable.getLimit();
		Sorter sorter = pageable.getSorter();
		String searchKey = pageable.getSearchKey();
		RoleModel filterModel = pageable.getFilterModel();
		
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count FROM Role");
		
		if(filterModel != null || searchKey != null)
			sql.append(" WHERE");
		if(filterModel != null)
		{
			if(filterModel.getCode() != null)
				sql.append(" code = ?");
			if(filterModel.getCode() != null && 
				filterModel.getName() != null)
				sql.append(" AND");
			if(filterModel.getName() != null)
				sql.append(" name = ?");
			if(filterModel.getPriority() != null &&
				(filterModel.getCode() != null || 
				filterModel.getName() != null))
				sql.append(" AND");
			if(filterModel.getPriority() != null)
				sql.append(" priority = ?");
			if(filterModel.getIsDeleted() != null &&
				(filterModel.getCode() != null || 
				filterModel.getName() != null ||
				filterModel.getPriority() != null))
				sql.append(" AND");
			if(filterModel.getIsDeleted() != null)
				sql.append(" isdeleted = ?");
			if(searchKey != null &&
				(filterModel.getCode() != null || 
				filterModel.getName() != null ||
				filterModel.getPriority() != null ||
				filterModel.getIsDeleted() != null))
				sql.append(" AND");
		}
		if(searchKey != null)
		{
			sql.append(" code LIKE '%" + searchKey + "%'");
			sql.append(" OR name LIKE '%" + searchKey + "%'");
			sql.append(" OR priority LIKE '%" + searchKey + "%'");
		}
		if(sorter != null && 
			sorter.getSortBy() != null &&
			sorter.getSortName() != null)
			sql.append(" ORDER BY " + sorter.getSortBy() + " " + sorter.getSortName());
		if(offset != null &&
			limit != null)
			sql.append(" LIMIT " + offset + ", " + limit);
		
		List<RoleModel> model = (filterModel == null) ? super.find(sql.toString(), new RoleMapper()) 
													: super.find(sql.toString(), new RoleMapper(), 
														filterModel.getCode(), filterModel.getName(), 
														filterModel.getPriority(), filterModel.getIsDeleted());
		return (model.isEmpty() ? null : model.get(0).getCount());
	}
}
