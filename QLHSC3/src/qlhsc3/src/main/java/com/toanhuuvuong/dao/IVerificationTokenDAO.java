package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.model.VerificationTokenModel;

public interface IVerificationTokenDAO
{
	public VerificationTokenModel findOne(Long id);
	public VerificationTokenModel findByCode(String code);
	public List<VerificationTokenModel> findByAccountId(Long accountId);
	public List<VerificationTokenModel> findAll();
	public void update(VerificationTokenModel model); 
	public void delete(Long id);
	public Long insertOne(VerificationTokenModel model);
}
