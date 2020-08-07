package com.toanhuuvuong.model;

import java.sql.Timestamp;

public class VerificationTokenModel extends GenericModel<VerificationTokenModel>
{
	private String code;
	private Timestamp expiryDate;
	private Long accountId;
	private AccountModel account;
	
	public String getCode()
	{
		return code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public Timestamp getExpiryDate() 
	{
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) 
	{
		this.expiryDate = expiryDate;
	}
	public Long getAccountId() 
	{
		return accountId;
	}
	public void setAccountId(Long accountId) 
	{
		this.accountId = accountId;
	}
	public AccountModel getAccount() 
	{
		return account;
	}
	public void setAccount(AccountModel account) 
	{
		this.account = account;
	}	
}
