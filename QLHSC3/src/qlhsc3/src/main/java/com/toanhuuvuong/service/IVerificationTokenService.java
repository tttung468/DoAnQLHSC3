package com.toanhuuvuong.service;

import java.util.List;
import java.util.Map;

import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.VerificationTokenModel;

public interface IVerificationTokenService 
{
	public VerificationTokenModel findOne(Long id);
	public VerificationTokenModel findByCode(String code);
	public List<VerificationTokenModel> findByAccountId(Long accountId);
	public List<VerificationTokenModel> findAll();
	public VerificationTokenModel updateOne(VerificationTokenModel model, AccountModel editor);
	public void delete(Long[] deletedIds);
	public VerificationTokenModel insertOne(VerificationTokenModel model, AccountModel creator);
	public Map<String, String> validate(VerificationTokenModel model);
}
