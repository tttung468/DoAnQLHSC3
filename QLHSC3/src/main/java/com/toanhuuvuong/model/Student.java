package com.toanhuuvuong.model;

import java.util.Date;

public class Student extends Generic implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Ethnic ethnic;
	private Nationality nationality;
	private Religion religion;
	private String code;
	private String name;
	private String phone;
	private String identifyCard;
	private String email;
	private String avatarpath;
	private String gender;
	private Date birth;
	private String address;
	private String status;

	public Student() {}

	public Student(Ethnic ethnic, Nationality nationality, Religion religion, String code, String name, String phone,
			String identifyCard, String email, String avatarpath, String gender, Date birth, String address,
			String status, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate,
			String modifiedBy) 
	{
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.ethnic = ethnic;
		this.nationality = nationality;
		this.religion = religion;
		this.code = code;
		this.name = name;
		this.phone = phone;
		this.identifyCard = identifyCard;
		this.email = email;
		this.avatarpath = avatarpath;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
		this.status = status;
	}
	public Ethnic getEthnic() 
	{
		return this.ethnic;
	}
	public void setEthnic(Ethnic ethnic)
	{
		this.ethnic = ethnic;
	}
	public Nationality getNationality() 
	{
		return this.nationality;
	}
	public void setNationality(Nationality nationality) 
	{
		this.nationality = nationality;
	}
	public Religion getReligion()
	{
		return this.religion;
	}
	public void setReligion(Religion religion) 
	{
		this.religion = religion;
	}
	public String getCode() 
	{
		return this.code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPhone() 
	{
		return this.phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public String getIdentifyCard() 
	{
		return this.identifyCard;
	}
	public void setIdentifyCard(String identifyCard) 
	{
		this.identifyCard = identifyCard;
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getAvatarpath() 
	{
		return this.avatarpath;
	}
	public void setAvatarpath(String avatarpath) 
	{
		this.avatarpath = avatarpath;
	}
	public String getGender() 
	{
		return this.gender;
	}
	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	public Date getBirth() 
	{
		return this.birth;
	}
	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	public String getAddress() 
	{
		return this.address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
}