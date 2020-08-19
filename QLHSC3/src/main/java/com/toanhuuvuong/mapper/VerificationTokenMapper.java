package com.toanhuuvuong.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.toanhuuvuong.model.VerificationTokenModel;

public class VerificationTokenMapper extends GenericMapper implements IRowMapper<VerificationTokenModel>
{
	@Override
	public VerificationTokenModel mapRow(ResultSet resultSet) 
	{
		VerificationTokenModel model = new VerificationTokenModel();
		
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
			if(hasColumn(resultSet, "expirydate"))
				model.setExpiryDate(resultSet.getTimestamp("expirydate"));
			if(hasColumn(resultSet, "accountid"))
				model.setAccountId(resultSet.getLong("accountid"));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return model;
	}	
}