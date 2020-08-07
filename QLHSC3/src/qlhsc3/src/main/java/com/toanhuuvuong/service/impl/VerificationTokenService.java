package com.toanhuuvuong.service.impl;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.dao.IAccountDAO;
import com.toanhuuvuong.dao.IVerificationTokenDAO;
import com.toanhuuvuong.dao.impl.AccountDAO;
import com.toanhuuvuong.dao.impl.VerificationTokenDAO;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.VerificationTokenModel;
import com.toanhuuvuong.service.IVerificationTokenService;

public class VerificationTokenService extends GenericService<VerificationTokenModel> implements IVerificationTokenService
{
	private static final long EXPIRATION = 24 * 60 * 60 * 1000;
	 
	private IVerificationTokenDAO verificationTokenDAO = new VerificationTokenDAO();
	 
	private IAccountDAO accountDAO = new AccountDAO();
	
	@Override
	protected VerificationTokenModel complete(VerificationTokenModel model) 
	{
		model.setAccount(accountDAO.findOne(model.getAccountId()));
		return model;
	}
	@Override
	public VerificationTokenModel findOne(Long id) 
	{
		return complete(verificationTokenDAO.findOne(id));
	}
	@Override
	public VerificationTokenModel findByCode(String code) 
	{
		return complete(verificationTokenDAO.findByCode(code));
	}
	@Override
	public List<VerificationTokenModel> findByAccountId(Long accountId)
	{
		return complete(verificationTokenDAO.findByAccountId(accountId));
	}
	@Override
	public List<VerificationTokenModel> findAll()
	{
		return complete(verificationTokenDAO.findAll());
	}
	@Override
	public VerificationTokenModel updateOne(VerificationTokenModel model, AccountModel editor) 
	{
		VerificationTokenModel oldModel = verificationTokenDAO.findOne(model.getId());
		model.setCreatedDate(oldModel.getCreatedDate());
		model.setCreatedBy(oldModel.getCreatedBy());
		model.setExpiryDate(oldModel.getExpiryDate());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		model.setModifiedBy((editor != null ? editor.getUsername() : SystemConstant.UNKNOWN));
		
		verificationTokenDAO.update(model);
		
		return model;
	}
	@Override
	public void delete(Long[] deletedIds) 
	{
		for(Long id : deletedIds)
			verificationTokenDAO.delete(id);
	}
	@Override
	public VerificationTokenModel insertOne(VerificationTokenModel model, AccountModel creator) 
	{
		model.setIsDeleted(false);
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		model.setCreatedBy((creator != null ? creator.getUsername() : SystemConstant.UNKNOWN));
		model.setExpiryDate(new Timestamp(System.currentTimeMillis() + EXPIRATION));
		
		Long id = verificationTokenDAO.insertOne(model);
		model.setId(id);
		
		return model;
	}
	@Override
	public Map<String, String> validate(VerificationTokenModel model)
	{
		Map<String, String> map = new Hashtable<String, String>();
		if(verificationTokenDAO.findByCode(model.getCode()) != null)
		{
			map.put("messageCode", "code_existed");
			map.put("alert", "danger");
		}
		else
		{
			map.put("messageCode", "insert_success");
			map.put("alert", "success");
		}
	
		return map;
	}
}
