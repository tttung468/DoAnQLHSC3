package com.toanhuuvuong.model;

import java.util.Date;

public class Officer extends Generic 
{
	protected Account account;
	protected String code;
	protected String name;
	protected String phone;
	protected String avatarpath;
	protected String gender;
	protected Date birth;
	protected String address;
	protected Integer salary;

	public Officer() {}
	public Officer(Account account, String code, String name, String phone, String avatarpath, 
			String gender, Date birth, String address, Integer salary, 
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy)
	{
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.account = account;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.avatarpath = avatarpath;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.salary = salary;
	}
	public Account getAccount() 
	{
		return account;
	}
	public void setAccount(Account account) 
	{
		this.account = account;
	}
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
	public String getAvatarpath() 
	{
		return avatarpath;
	}
	public void setAvatarpath(String avatarpath) 
	{
		this.avatarpath = avatarpath;
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
}
