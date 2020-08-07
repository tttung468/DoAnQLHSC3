package com.toanhuuvuong.dao.impl;

import java.util.List;

import com.toanhuuvuong.dao.IVerificationTokenDAO;
import com.toanhuuvuong.mapper.VerificationTokenMapper;
import com.toanhuuvuong.model.VerificationTokenModel;

public class VerificationTokenDAO extends GenericDAO<VerificationTokenModel> implements IVerificationTokenDAO 
{
	@Override
	public VerificationTokenModel findOne(Long id) 
	{
		String sql = "SELECT * FROM VerificationToken WHERE id = ?";
		List<VerificationTokenModel> model = super.find(sql, new VerificationTokenMapper(), id);
		return (model.isEmpty() ? null : model.get(0));
	}
	@Override
	public VerificationTokenModel findByCode(String code)
	{
		String sql = "SELECT * FROM VerificationToken WHERE code = ?";
		List<VerificationTokenModel> model = super.find(sql, new VerificationTokenMapper(), code);
		return (model.isEmpty() ? null : model.get(0));
	}
	@Override
	public List<VerificationTokenModel> findByAccountId(Long accountId)
	{
		String sql = "SELECT * FROM VerificationToken WHERE accountid = ?";
		return super.find(sql, new VerificationTokenMapper(), accountId);
	}
	@Override
	public List<VerificationTokenModel> findAll()
	{
		String sql = "SELECT * FROM VerificationToken";
		return super.find(sql, new VerificationTokenMapper());
	}
	@Override
	public void update(VerificationTokenModel model) 
	{
		StringBuilder sql = new StringBuilder("UPDATE VerificationToken SET code = ?, expirydate = ?, accountid = ?,");
		sql.append(" isdeleted = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");
		super.update(sql.toString(), model.getCode(), model.getExpiryDate(), model.getAccountId(),
				model.getIsDeleted(), model.getCreatedDate(), model.getCreatedBy(), model.getModifiedDate(), model.getModifiedBy(),
				model.getId());
	}
	@Override
	public void delete(Long id) 
	{
		String sql = "DELETE FROM VerificationToken WHERE id = ?";
		super.update(sql, id);
	}
	@Override
	public Long insertOne(VerificationTokenModel model)
	{
		StringBuilder sql = new StringBuilder("INSERT INTO VerificationToken(code, expirydate, accountid,");
		sql.append(" isdeleted, createddate, createdby, modifieddate, modifiedby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		return super.insert(sql.toString(), model.getCode(), model.getExpiryDate(), model.getAccountId(), 
				model.getIsDeleted(), model.getCreatedDate(), model.getCreatedBy(), model.getModifiedDate(), model.getModifiedBy());
	}
}