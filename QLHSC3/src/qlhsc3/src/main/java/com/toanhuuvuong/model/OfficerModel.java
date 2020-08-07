package com.toanhuuvuong.model;

import java.sql.Date;

public class OfficerModel extends GenericModel<OfficerModel>
{
	private String code;
	private String name;
	private String phone;
	private String avatarPath;
	private String gender;
	private Date birth;
	private String address;
	private Integer salary;
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
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAvatarPath() 
	{
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) 
	{
		this.avatarPath = avatarPath;
	}
	public String getGender() 
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public Date getBirth() 
	{
		return birth;
	}
	public void setBirth(Date birth) 
	{
		this.birth = birth;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Integer getSalary() 
	{
		return salary;
	}
	public void setSalary(Integer salary) 
	{
		this.salary = salary;
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
