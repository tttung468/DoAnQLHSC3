package com.toanhuuvuong.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.toanhuuvuong.model.RoleModel;

public class RoleMapper extends GenericMapper implements IRowMapper<RoleModel>
{
	@Override
	public RoleModel mapRow(ResultSet resultSet) 
	{
		RoleModel model = new RoleModel();
		
		try
		{
			if(hasColumn(resultSet, "id"))
				model.setId(resultSet.getLong("id"));
			if(hasColumn(resultSet, "isdeleted"))
				model.setIsDeleted(resultSet.getBoolean("isdeleted"));
			if(hasColumn(resultSet, "createddate"))
				model.setCreatedDate(resultSet.getTimestamp("createddate"));
			if(hasColumn(resultSet, "createdby"))
				model.setCreatedBy(resultSet.getString("createdby"));
			if(hasColumn(resultSet, "modifieddate"))
				model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			if(hasColumn(resultSet, "modifiedby"))
				model.setModifiedBy(resultSet.getString("modifiedby"));
			if(hasColumn(resultSet, "count"))
				model.setCount(resultSet.getInt("count"));
			
			if(hasColumn(resultSet, "code"))
				model.setCode(resultSet.getString("code"));
			if(hasColumn(resultSet, "name"))
				model.setName(resultSet.getString("name"));
			if(hasColumn(resultSet, "priority"))
				model.setPriority(resultSet.getInt("priority"));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return model;
	}
}
