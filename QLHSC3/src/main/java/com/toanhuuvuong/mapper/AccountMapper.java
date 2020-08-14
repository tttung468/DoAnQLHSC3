package com.toanhuuvuong.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.toanhuuvuong.model.AccountModel;

public class AccountMapper extends GenericMapper implements IRowMapper<AccountModel>
{
	@Override
	public AccountModel mapRow(ResultSet resultSet) 
	{
		AccountModel model = new AccountModel();
		
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
			
			if(hasColumn(resultSet, "username"))
				model.setUsername(resultSet.getString("username"));
			if(hasColumn(resultSet, "password"))
				model.setPassword(resultSet.getString("password"));
			if(hasColumn(resultSet, "roleid"))
				model.setRoleId(resultSet.getLong("roleid"));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return model;
	}
}
